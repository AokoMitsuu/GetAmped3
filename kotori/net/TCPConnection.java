package kotori.net;

import ga2.cybergarage.soap.SOAP;
import ga2.data.StageConstants;
import ga2.data.net.GA2CommandRegistry;
import ga2.setting.GameSetting;
import ga2.stage.RemoteLoginInfo;
import ga2.stage.StageServerPlayerEntry;
import ga2.stage.event.AmpedEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import kotori.gfx.GLConstantsX;
import kotori.util.Clock;
import kotori.util.KotoriUtil;

public class TCPConnection extends NetConnection {
    public static final int INPUT_BUFFER_SIZE = 16384;
    public static final int OUTPUT_BUFFER_SIZE = 16384;
    SocketAddress addr;
    Object attachment;
    ByteBufferOutputStream bbos;
    ByteArrayInputStream bis;
    private boolean connected;
    int curs;
    int datalen;
    boolean debug_code;
    ByteBuffer delaywrite;
    ConnectionEngine engine;
    private final Object inlock;
    SelectionKey key;
    ArrayList<ConnectionListener> listeners;
    int nexttry;
    private final Object outlock;
    int phase;
    private ReadQueue[] queue;
    byte[] readbytes;
    int readch;
    SocketChannel sockchannel;
    Transeiver[] trns;
    int trycnt;
    volatile long trystart;
    int writech;
    static final Transeiver defaulttrns = new TCPConnectionTranseiver();
    static final Hashtable<SocketAddress, WeakReference<TCPConnection>> connections = new Hashtable<SocketAddress, WeakReference<TCPConnection>>();
    private static final int[] checkedCMDID = { 300, -7, 11, -12, 107, 177, 3, -1, 31, 4, 15, 262, -9, 253, -31, 5, -36,
            -37, 41, 181, 47 };
    private static final int[] checkedAmpedEventID = { 81, 82, 103, 80, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
            22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 38, 170, 171, 172, 173, 174, 175 };

    TCPConnection(ConnectionEngine e) {
        this.trns = new Transeiver[10];
        this.writech = 0;
        this.readch = 0;
        this.connected = false;
        this.queue = null;
        this.listeners = new ArrayList<ConnectionListener>();
        this.debug_code = false;
        this.inlock = new Object();
        this.outlock = new Object();
        this.phase = 0;
        this.engine = e;
    }

    public TCPConnection(SocketChannel sc, ConnectionEngine ce) throws IOException {
        this.trns = new Transeiver[10];
        this.writech = 0;
        this.readch = 0;
        this.connected = false;
        this.queue = null;
        this.listeners = new ArrayList<ConnectionListener>();
        this.debug_code = false;
        this.inlock = new Object();
        this.outlock = new Object();
        this.phase = 0;
        this.engine = ce;
        this.sockchannel = sc;
        this.addr = sc.socket().getRemoteSocketAddress();
        initDataIO();
        sc.socket().setReceiveBufferSize(32768);
        sc.socket().setSendBufferSize(49152);
        sc.socket().setTcpNoDelay(true);
        sc.configureBlocking(false);
        this.key = sc.register(this.engine.getSelector(), 1, this);
        this.connected = true;
    }

    public static TCPConnection open(SocketAddress a, ConnectionEngine e) {
        TCPConnection c;
        synchronized (connections) {
            WeakReference<TCPConnection> r = connections.get(a);
            if (r == null || (c = (TCPConnection) r.get()) == null) {
                TCPConnection c2 = new TCPConnection(e);
                c2.addr = a;
                c2.initDataIO();
                connections.put(a, new WeakReference<TCPConnection>(c2));
                return c2;
            }
            return c;
        }
    }

    public static TCPConnection openNewConnection(SocketAddress a, ConnectionEngine e) {
        TCPConnection c = new TCPConnection(e);
        c.addr = a;
        c.initDataIO();
        connections.put(a, new WeakReference<TCPConnection>(c));
        return c;
    }

    public void connect() throws IOException {
        synchronized (this) {
            if (!this.connected) {
                SocketChannel sc = SocketChannel.open();
                sc.connect(this.addr);
                sc.socket().setReceiveBufferSize(49152);
                sc.socket().setSendBufferSize(32768);
                sc.socket().setKeepAlive(true);
                sc.socket().setTcpNoDelay(true);
                this.sockchannel = sc;
                this.connected = true;
                this.engine.registerAndWait(this);
            }
        }
    }

    public void lanconnecttest() throws IOException {
        synchronized (this) {
            if (!this.connected) {
                SocketChannel sc = SocketChannel.open();
                sc.socket().connect(this.addr, 500);
                sc.socket().setReceiveBufferSize(49152);
                sc.socket().setSendBufferSize(32768);
                sc.socket().setKeepAlive(true);
                sc.socket().setTcpNoDelay(true);
                this.sockchannel = sc;
                this.connected = true;
                this.engine.registerAndWait(this);
            }
        }
    }

    public synchronized boolean isConnected() {
        return this.connected;
    }

    @Override
    public void setAttachment(Object o) {
        this.attachment = o;
    }

    @Override
    public Object getAttachment() {
        return this.attachment;
    }

    @Override
    public ConnectionEngine getConnectionEngine() {
        return this.engine;
    }

    public String toString() {
        return this.addr == null ? "TCPConnection [-.-.-.-:-]"
                : "TCPConnection [" + ((InetSocketAddress) this.addr).getAddress().getHostAddress() + SOAP.DELIM
                        + ((InetSocketAddress) this.addr).getPort() + "]";
    }

    public void register(Selector selector) {
        try {
            this.sockchannel.configureBlocking(false);
            this.key = this.sockchannel.register(selector, 1, this);
        } catch (ClosedChannelException cce) {
            cce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void close() {
        synchronized (this) {
            if (this.sockchannel != null) {
                try {
                    this.sockchannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.sockchannel = null;
            if (this.key != null) {
                this.key.attach(null);
                this.key.cancel();
                this.key = null;
            }
            this.connected = false;
        }
        synchronized (connections) {
            connections.remove(this.addr);
        }
        postEvent(0);
    }

    public void closeInternal() {
        synchronized (this) {
            if (this.sockchannel != null) {
                try {
                    this.sockchannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.sockchannel = null;
            if (this.key != null) {
                this.key.attach(null);
                this.key.cancel();
                this.key = null;
            }
            this.connected = false;
        }
        synchronized (connections) {
            connections.remove(this.addr);
        }
        postEvent(0);
    }

    void postEvent(int id) {
        synchronized (this.listeners) {
            if (this.listeners.size() != 0) {
                ConnectionListener[] llist = (ConnectionListener[]) this.listeners
                        .toArray(new ConnectionListener[this.listeners.size()]);
                ConnectionEvent e = new ConnectionEvent(this, id);
                for (int l = llist.length - 1; l >= 0; l--) {
                    try {
                        llist[l].connectionStateChanged(e);
                    } catch (Exception ex) {
                        System.err.println("TCPConnection.postEvent() caused exception");
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void addConnectionListener(ConnectionListener l) {
        synchronized (this.listeners) {
            this.listeners.add(l);
        }
    }

    @Override
    public void removeConnectionListener(ConnectionListener l) {
        synchronized (this.listeners) {
            this.listeners.remove(l);
        }
    }

    @Override
    public synchronized void setTranseiver(Transeiver trns) {
        removeTranseiver(0);
        registerTranseiver(0, trns);
    }

    public synchronized void registerTranseiver(int ch, Transeiver r) {
        if (ch >= this.trns.length) {
            Transeiver[] old = this.trns;
            this.trns = new Transeiver[ch + 1];
            System.arraycopy(old, 0, this.trns, 0, old.length);
        }
        if (this.trns[ch] != null) {
            throw new RuntimeException("TRANSEIVER ALREADY BOUND : " + ch + " " + r + " " + this);
        }
        this.trns[ch] = r;
    }

    public synchronized void removeTranseiver(int ch) {
        if (ch < this.trns.length) {
            this.trns[ch] = null;
        }
    }

    @Override
    public void write(Object o, boolean ensure) throws IOException {
        write(0, o);
    }

    @SuppressWarnings("rawtypes")
    public void write(int ch, Object o) throws IOException {
        Transeiver t;
        SocketChannel sc;
        synchronized (this) {
            if (this.sockchannel == null) {
                throw new IOException("TCPConnection has been closed");
            }
            t = (this.writech >= this.trns.length || this.trns[ch] == null) ? defaulttrns : this.trns[ch];
            sc = this.sockchannel;
        }
        boolean checked = false;
        if (this.debug_code) {
            if (o == null && (t instanceof StageServerPlayerEntry.Remote.RemoteTranseiver)) {
                StageServerPlayerEntry.Remote.RemoteTranseiver rt = (StageServerPlayerEntry.Remote.RemoteTranseiver) t;
                ArrayList sendlist = rt.getSendList();
                Iterator iterator = sendlist.iterator();
                while (iterator.hasNext()) {
                    AmpedEvent ae = (AmpedEvent) iterator.next();
                    GameSetting.getSetting().println("AmpedEvent:" + ae);
                }
            }
            checked = true;
        }
        long nanoTime = System.nanoTime();
        synchronized (this.outlock) {
            this.bbos.reset();
            t.send(o, this, this.writech, this.bbos);
            this.bbos.finish(ch, this.writech);
            this.writech = ch;
            ByteBuffer bb = this.bbos.getByteBuffer();
            int x = 0;
            int len = bb.remaining();
            if (this.delaywrite == null) {
                x = 0 + sc.write(bb);
            } else if (Clock.time() >= this.nexttry - 290) {
                sc.write(this.delaywrite);
                if (this.delaywrite.remaining() == 0) {
                    this.delaywrite = null;
                    x = 0 + sc.write(bb);
                }
            }
            if (x < len) {
                if (this.delaywrite != null) {
                    ByteBuffer nbuf = ByteBuffer.allocate(bb.remaining() + this.delaywrite.remaining());
                    nbuf.limit(bb.remaining() + this.delaywrite.remaining());
                    nbuf.put(this.delaywrite);
                    this.delaywrite = nbuf;
                    this.nexttry = Math.min(this.nexttry, Clock.time() + 300);
                } else {
                    this.delaywrite = ByteBuffer.allocate(bb.remaining());
                    this.delaywrite.limit(bb.remaining());
                    this.nexttry = Clock.time() + 300;
                }
                this.delaywrite.put(bb);
                this.delaywrite.position(0);
                this.engine.registerDelay(this);
            } else {
                this.trycnt = 0;
                this.trystart = System.currentTimeMillis();
            }
        }
        if (this.debug_code) {
            String cmd = null;
            if (o instanceof Terminal.Command) {
                Terminal.Command c = (Terminal.Command) o;
                cmd = GA2CommandRegistry.getCommand(c.cmd);
                if (KotoriUtil.indexOf(checkedCMDID, c.cmd) >= 0) {
                    checked = true;
                }
            }
            if (o instanceof AmpedEvent) {
                AmpedEvent ae2 = (AmpedEvent) o;
                if (KotoriUtil.indexOf(checkedAmpedEventID, ae2.id) >= 0) {
                    checked = true;
                }
            }
            if (o instanceof RemoteLoginInfo) {
                // RemoteLoginInfo remoteLoginInfo = (RemoteLoginInfo) o;
                checked = true;
            }
            long dt = (System.nanoTime() - nanoTime) / 1000;
            if (dt < 100000) {
                GameSetting.getSetting().println(
                        "write dt:" + dt + ", " + (cmd != null ? cmd : StageConstants.clienttitle) + SOAP.DELIM + o);
            } else {
                GameSetting.printStackTrace(
                        "write dt:" + dt + ", " + (cmd != null ? cmd : StageConstants.clienttitle) + SOAP.DELIM + o);
                checked = true;
            }
            if (!checked) {
                if (cmd == null) {
                    cmd = StageConstants.clienttitle;
                }
                GameSetting.printStackTrace(String.valueOf(cmd) + SOAP.DELIM + o);
            }
        }
    }

    public boolean delayWrite() {
        synchronized (this) {
            if (this.connected) {
                SocketChannel sc = this.sockchannel;
                try {
                    synchronized (this.outlock) {
                        if (this.delaywrite == null) {
                            return false;
                        }
                        if (Clock.time() < this.nexttry) {
                            return true;
                        }
                        this.nexttry += 500;
                        int len = this.delaywrite.remaining();
                        int x = sc.write(this.delaywrite);
                        long dt = System.currentTimeMillis() - this.trystart;
                        if (x < len) {
                            int i = this.trycnt + 1;
                            this.trycnt = i;
                            if (i % 10 == 0) {
                                System.err.println("TCP BUFFER BUSY... try:" + this.trycnt + " ip:"
                                        + ((InetSocketAddress) getRemoteSocketAddress()).getAddress().getHostAddress());
                            }
                            if (this.trycnt < 100 || dt <= 10000) {
                                return true;
                            }
                            throw new IOException("TCP BUFFER BUSY...CLOSE!!!!! ip+"
                                    + ((InetSocketAddress) getRemoteSocketAddress()).getAddress().getHostAddress());
                        }
                        GameSetting.getSetting().println("delayWrite OK! dt:" + dt);
                        this.delaywrite = null;
                        return false;
                    }
                } catch (Exception e) {
                    GameSetting.printStackTrace("!close! TCP DELAY WRITE ERROR : " + e);
                    close();
                    return false;
                }
            }
            return false;
        }
    }

    @Override
    public Object read(int timeout) throws IOException, InterruptedException {
        return read(0, timeout);
    }

    public Object read(int ch, int timeout) throws IOException, InterruptedException {
        long nanoTime = System.nanoTime();
        ReadQueue q = getReadQueue(ch, true);
        Object ret = null;
        synchronized (q) {
            for (int l = 0; l < 6; l++) {
                ret = q.obj;
                if (ret != null) {
                    break;
                }
                q.wait(timeout / 6);
            }
        }
        if (this.debug_code) {
            long dt = (System.nanoTime() - nanoTime) / 1000;
            if (dt < 100000) {
                GameSetting.getSetting().println("read ch:" + ch + ", dt:" + dt + ", read object:" + ret);
            } else {
                GameSetting.printStackTrace("read ch:" + ch + ", dt:" + dt + ", read object:" + ret);
            }
        }
        if (ret == null) {
            throw new IOException("read timeout....");
        }
        return ret;
    }

    @Override
    public void setNextTranseiver(Transeiver trns) {
        setNextTranseiver(0, trns);
    }

    public void setNextTranseiver(int ch, Transeiver trns) {
        ReadQueue q = getReadQueue(ch, true);
        synchronized (q) {
            q.nexttrns = trns;
        }
    }

    private synchronized ReadQueue getReadQueue(int ch, boolean create) {
        ReadQueue readQueue;
        if (!create) {
            readQueue = (this.queue == null || this.queue.length <= ch || this.queue[ch] == null) ? null : null;
        }
        if (this.queue == null) {
            this.queue = new ReadQueue[ch + 1];
            this.queue[ch] = new ReadQueue();
        } else if (this.queue.length <= ch) {
            ReadQueue[] old = this.queue;
            this.queue = new ReadQueue[ch + 1];
            System.arraycopy(old, 0, this.queue, 0, old.length);
            this.queue[ch] = new ReadQueue();
        } else if (this.queue[ch] == null) {
            this.queue[ch] = new ReadQueue();
        }
        readQueue = this.queue[ch];
        return readQueue;
    }

    // private synchronized void removeQueue(int ch) {
    // if (this.queue != null && this.queue.length > ch && this.queue[ch] != null) {
    // this.queue[ch] = null;
    // }
    // }

    public void addQueue(int ch, Object o) {
        ReadQueue q = getReadQueue(ch, true);
        if (q != null) {
            synchronized (q) {
                if (q.nexttrns != null) {
                    synchronized (this) {
                        if (q.nexttrns != null) {
                            registerTranseiver(ch, q.nexttrns);
                        }
                        q.nexttrns = null;
                    }
                }
                q.obj = o;
                q.notifyAll();
            }
        }
    }

    public SocketAddress getSocketAddress() {
        return this.addr;
    }

    public SocketAddress getLocalSocketAddress() {
        return this.sockchannel.socket().getLocalSocketAddress();
    }

    public SocketAddress getRemoteSocketAddress() {
        if (this.sockchannel == null || this.sockchannel.socket() == null) {
            return null;
        }
        return this.sockchannel.socket().getRemoteSocketAddress();
    }

    protected void finalize() {
        synchronized (this) {
            if (this.connected) {
                close();
            }
        }
    }

    void initDataIO() {
        synchronized (this.inlock) {
            this.phase = 0;
            this.readbytes = new byte[16384];
            this.bis = new ByteArrayInputStream(this.readbytes);
        }
        synchronized (this.outlock) {
            this.bbos = new ByteBufferOutputStream(ByteBuffer.allocateDirect(GLConstantsX.GL_LIGHT6));
        }
    }

    public void addData(ByteBuffer buf) throws Exception {
        int len = buf.limit();
        while (buf.position() < len) {
            switch (this.phase) {
                case 0:
                    synchronized (this.inlock) {
                        int b = buf.get() & 255;
                        if (b == 0 && this.datalen == 0) {
                            this.phase = 2;
                            break;
                        } else {
                            this.datalen = (this.datalen << 7) | (b & 127);
                            if ((b & 128) == 0) {
                                this.phase = 1;
                                this.curs = 0;
                                this.bis.reset();
                            }
                            break;
                        }
                    }
                case 1:
                    Transeiver tr = null;
                    synchronized (this.inlock) {
                        int s = Math.min(len - buf.position(), this.datalen - this.curs);
                        if (this.curs + s > this.readbytes.length) {
                            byte[] old = this.readbytes;
                            int newsize = Math.max(this.curs + s + 1000, this.readbytes.length + 16384);
                            this.readbytes = new byte[newsize];
                            System.arraycopy(old, 0, this.readbytes, 0, this.curs);
                            this.bis = new ByteArrayInputStream(this.readbytes);
                        }
                        buf.get(this.readbytes, this.curs, s);
                        this.curs += s;
                        if (this.curs >= this.datalen) {
                            this.phase = 0;
                            this.datalen = 0;
                            synchronized (this) {
                                tr = (this.readch >= this.trns.length || this.trns[this.readch] == null) ? defaulttrns
                                        : this.trns[this.readch];
                            }
                        }
                    }
                    if (tr != null) {
                        try {
                            tr.received(this, this.readch, this.bis);
                            break;
                        } catch (Exception e) {
                            GameSetting.printStackTrace("!close! TCPConnection.addData() caused an Exception : " + e
                                    + "  addr:" + ((InetSocketAddress) this.addr).getAddress() + " ");
                            close();
                            throw e;
                        }
                    } else {
                        continue;
                    }
                case 2:
                    synchronized (this.inlock) {
                        this.readch = buf.get() & 255;
                        this.phase = 0;
                        this.datalen = 0;
                    }
                    break;
            }
        }
    }

    static class TCPConnectionTranseiver extends DefaultTranseiver {
        TCPConnectionTranseiver() {
        }

        @Override
        public void received(Object src, int ch, Object o) {
            ((TCPConnection) src).addQueue(ch, o);
        }
    }

    public static class ReadQueue {
        Transeiver nexttrns;
        Object obj;

        ReadQueue() {
        }
    }
}