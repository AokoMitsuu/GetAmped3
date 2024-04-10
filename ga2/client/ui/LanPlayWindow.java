package ga2.client.ui;

import ga2.client.GetAmped2;
import ga2.client.LookAndFeel;
import ga2.cybergarage.soap.SOAP;
import ga2.data.StageConstants;
import ga2.data.UserInfo;
import ga2.data.UserShortcuts.AvatorShortcut;
import ga2.data.net.AmpedException;
import ga2.data.net.ChannelInfo;
import ga2.data.net.GameRoomInfo;
import ga2.laf.amped2.AmpedDialog;
import ga2.laf.amped2.CharaPreviewPanel;
import ga2.laf.amped2.PageSelect2;
import ga2.laf.amped2.ShutterManager;
import ga2.server.ChannelServer;
import ga2.server.ChannelServerProcess;
import ga2.server.data.ChannelServerInfo;
import ga2.server.data.DatabaseSetting;
import ga2.server.data.ServerSetting;
import ga2.setting.GameSetting;
import ga2.stage.StageConnector;
import ga2.stage.StageServer;
import ga2.stage.StageServerRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import com.nr.tool.LocalStorage;
import kotori.kwt.Component;
import kotori.kwt.Container;
import kotori.kwt.ContainerData;
import kotori.kwt.KWTManager;
import kotori.kwt.KWTStruct;
import kotori.kwt.ScrollPane;
import kotori.kwt.TextFigure;
import kotori.kwt.TextInput;
import kotori.kwt.Window;
import kotori.kwt.event.ActionEvent;
import kotori.kwt.event.ActionListener;
import kotori.kwt.event.MouseAdaptor;
import kotori.kwt.event.MouseEvent;
import kotori.net.ConnectionEngine;
import kotori.net.DefaultTranseiver;
import kotori.net.TCPConnection;
import kotori.net.UDPConnection;
import kotori.util.Inttable;

public class LanPlayWindow extends Window {
    public static int PortBase = 8000;

    static UDPConnection lanbroadcast;
    static int lanbroadcastport;
    static ArrayList<String> lanpcaddr = new ArrayList<String>();
    static int[] npcid;
    static int npcidx;
    static ConnectionEngine tcpconengine = new ConnectionEngine("LANTEST.TCP");

    UDPConnection broadcast;
    Container c_techlist;
    ChannelServer ch;
    ContainerData ch_itemcd;
    float ch_itemh;
    Container ch_listc;
    Container ch_refresh;
    ScrollPane ch_scroll;
    TextInput charaname;
    ChannelServerProcess chproc;
    ChannelInfo ci;
    Container close;
    boolean isSearchLanChannel;
    boolean isBroadcastLan;
    AmpedException lastex;
    Container makeroom;
    Thread myroombroadcastthread;
    GameRoomInfo myroominfo;
    Container[] npcface;
    PageSelect2 page;
    ContainerData player_itemcd;
    float player_itemh;
    Container player_listc;
    Container player_refresh;
    ScrollPane player_scroll;
    CharaPreviewPanel preview;
    boolean roomselect;
    StageServer ss;
    StageServerRunner ssr;
    Container status;
    final int NPC_PAGENUM = 40;
    KWTStruct srt = LookAndFeel.getLAF().getKWTStruct("kwtdocs/lanplay.kwt");

    public LanPlayWindow() {
        this.isSearchLanChannel = false;
        this.isBroadcastLan = false;

        setStruct(this.srt);
        this.bnd.set(this.srt.bnd);

        this.preview = (CharaPreviewPanel) findNamedComponent("preview", this);
        this.preview.setVisible(false);

        this.charaname = (TextInput) findNamedComponent("charaname", this);
        this.charaname.setMaxLen(16);

        this.componentAt(8).setVisible(false);

        this.makeroom = (Container) findNamedComponent("makeroom", this);
        this.makeroom.addActionListener(this);

        this.close = (Container) findNamedComponent("close", this);
        this.close.addActionListener(this);

        this.page = (PageSelect2) findNamedComponent("page", this);
        this.page.setVisible(false);

        this.ch_refresh = (Container) findNamedComponent("ch_refresh", this);
        this.ch_refresh.setVisible(false);

        this.player_refresh = (Container) findNamedComponent("player_refresh", this);
        this.player_refresh.setVisible(false);

        this.ch_scroll = (ScrollPane) findNamedComponent("ch_scroll", this);
        this.ch_listc = new Container();
        this.ch_scroll.setContent(this.ch_listc);
        this.ch_itemcd = this.srt.getLibrary().getPanel("e:ch_listitem");
        this.ch_itemh = this.ch_itemcd.bnd.h;

        this.player_scroll = (ScrollPane) findNamedComponent("player_scroll", this);
        this.player_listc = new Container();
        this.player_scroll.setContent(this.player_listc);
        this.player_itemcd = this.srt.getLibrary().getPanel("e:player_listitem");
        this.player_itemh = this.player_itemcd.bnd.h;

        this.c_techlist = (Container) findNamedComponent("c_techlist", this);
        this.c_techlist.setVisible(false);

        this.status = (Container) findNamedComponent("status", this);
        this.status.setVisible(false);
        setPlayer();

        if (lanbroadcast == null) {
            lanbroadcast = new UDPConnection(GetAmped2.getAmped2().getLanConnectionEngine());
            lanbroadcast.setTranseiver(new LANBroadcastTranseiver());
            for (int i2 = 0; i2 < 10; i2++) {
                int port = 40000 + i2;
                try {
                    lanbroadcast.openBroadcast(new InetSocketAddress(port));
                    lanbroadcastport = port;
                    lanpcaddr.add(String.valueOf(InetAddress.getLocalHost().getHostAddress()) + SOAP.DELIM
                            + lanbroadcastport);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            lanbroadcast.setTranseiver(new LANBroadcastTranseiver());
        }

        GetAmped2.getAmped2().lanchinfo = new ArrayList<String>();
        this.isSearchLanChannel = true;
        this.isBroadcastLan = true;
        searchLanChannelproc();
        setModal(KWTManager.PS4_CONTROL);
    }

    void setPlayer() {
        this.charaname.setText(LocalStorage.getInstance().get("nickname"));
    }

    void close() {
        LocalStorage.getInstance().set("nickname", this.charaname.getText());
        this.isSearchLanChannel = false;
        end();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == this.close) {
            this.isSearchLanChannel = false;
            this.isBroadcastLan = false;
            playSound(1005);
            close();
            System.exit(0);
        } else if (src == this.makeroom) {
            this.roomselect = true;
            lanPlay();
        } else if (src == this.ch_refresh) {
            refreshChannelList();
        } else if (src == this.player_refresh) {
            refreshPlayerList();
        }
    }

    class LANBroadcastTranseiver extends DefaultTranseiver {
        LANBroadcastTranseiver() {
        }

        @Override
        public void received(Object src, int ch, Object o) {
            String[] addr = (String[]) o;
            for (int i = 0; i < addr.length; i++) {
                if (!LanPlayWindow.lanpcaddr.contains(addr[i])) {
                    LanPlayWindow.lanpcaddr.add(addr[i]);
                }
            }
            LanPlayWindow.this.buildChannelList();
            LanPlayWindow.this.buildPlayerList();
        }
    }

    void buildChannelList() {
        synchronized (KWTManager.PAINT_LOCK) {
            this.ch_listc.clear();
            int n = 0;
            GetAmped2 ga22 = GetAmped2.getAmped2();
            if (ga22.lanchinfo.size() > 0) {
                for (int i = 0; i < ga22.lanchinfo.size(); i++) {
                    String addr = (String) ga22.lanchinfo.get(i);
                    if (!ga22.nglanchinfo.contains(addr)) {
                        ChannelListItem li = createChannelListItem(addr, i);
                        li.cnt.setPosition(0.0f, 2.0f + (n * this.ch_itemh));
                        li.cnt.updateMatrix();
                        this.ch_listc.add(li.cnt);
                        li.cnt.getEntry().setName("room" + n);
                        n++;
                    }
                }
            }
            this.ch_listc.bnd.w = this.ch_itemcd.bnd.w;
            this.ch_listc.bnd.h = 4.0f + (this.ch_itemh * n);
            this.ch_listc.revalidate();
        }
    }

    void buildPlayerList() {
        synchronized (KWTManager.PAINT_LOCK) {
            this.player_listc.clear();
            int n = 0;
            GetAmped2 ga22 = GetAmped2.getAmped2();
            if (lanpcaddr.size() > 0) {
                for (int i = 0; i < lanpcaddr.size(); i++) {
                    String addr = (String) lanpcaddr.get(i);
                    if (!ga22.lanchinfo.contains(addr)) {
                        PlayerListItem li = createPlayerListItem(addr, i);
                        li.cnt.setPosition(0.0f, 2.0f + (n * this.player_itemh));
                        li.cnt.updateMatrix();
                        this.player_listc.add(li.cnt);
                        li.cnt.getEntry().setName("room" + n);
                        n++;
                    }
                }
            }
            this.player_listc.bnd.w = this.player_itemcd.bnd.w;
            this.player_listc.bnd.h = 4.0f + (this.player_itemh * n);
            this.player_listc.revalidate();
        }
    }

    void refreshChannelList() {
        synchronized (GetAmped2.getAmped2().lanchinfo) {
            GetAmped2.getAmped2().lanchinfo = new ArrayList<String>();
        }
        synchronized (lanpcaddr) {
            lanpcaddr = new ArrayList<String>();
            try {
                lanpcaddr.add(
                        String.valueOf(InetAddress.getLocalHost().getHostAddress()) + SOAP.DELIM + lanbroadcastport);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        buildChannelList();
    }

    void refreshPlayerList() {
        synchronized (GetAmped2.getAmped2().lanchinfo) {
            GetAmped2.getAmped2().lanchinfo = new ArrayList<String>();
        }
        synchronized (lanpcaddr) {
            lanpcaddr = new ArrayList<String>();
            try {
                lanpcaddr.add(
                        String.valueOf(InetAddress.getLocalHost().getHostAddress()) + SOAP.DELIM + lanbroadcastport);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        buildPlayerList();
    }

    ChannelListItem createChannelListItem(String _addr, int idx) {
        ChannelListItem li = new ChannelListItem(this.ch_itemcd);
        li.setInfo(_addr, idx);
        return li;
    }

    PlayerListItem createPlayerListItem(String _addr, int idx) {
        PlayerListItem li = new PlayerListItem(this.player_itemcd);
        li.setInfo(_addr, idx);
        return li;
    }

    void lanPlay() {
        GetAmped2 ga22 = GetAmped2.getAmped2();
        LoginProgressDialog dialog = new LoginProgressDialog();
        dialog.setText("$lanplay.label.makingroom");
        dialog.cancel.setEnabled(false);
        Thread t = roommakeproc(dialog);
        AmpedDialog.showAmpedDialog(dialog, getKWTManager());
        try {
            t.join(60000L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ChannelInfo ci = new ChannelInfo();
        ci.addr = new InetSocketAddress("localhost", PortBase);
        ga22.setUserID("_lanplayAoko", StageConstants.clienttitle);
        try {
            ga22.login(ci);
            ga22.setChannels((ChannelInfo[]) ga22.command("get_channels", null));
            ChannelInfo chinfo = ga22.autoSelectChannel();
            if (chinfo != null) {
                ga22.enter(chinfo);
            }

            UserInfo ui = new UserInfo().set(GameSetting.getSetting().getNPCInfo(0));
            if (!ui.name.equals(this.charaname.getText())) {
                ga22.lannpcdefname = new Inttable();
                ga22.lannpcdefname.put(0, ui.name);
                if (this.charaname.getText().startsWith("GM_")) {
                    ui.name = this.charaname.getText().replace("GM_", "");
                    ui.adminflag = (byte) 1;
                } else {
                    ui.name = this.charaname.getText();
                    ui.adminflag = (byte) 6;
                }

            }
            ui.score = 50000000;
            ga22.command("chararemake", ui);
            StageConnector con = ga22.getStageConnector();
            if (con != null) {
                con.updateUserData(ga22.getUserData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        WorldMapScene.exitrunnable = new Runnable() {
            public void run() {
                LanPlayWindow.this._returntitle();
            }
        };
        close();
    }

    class ReturnTitle implements ActionListener {
        ReturnTitle() {
        }

        public void actionPerformed(ActionEvent e) {
            GetAmped2 ga22 = GetAmped2.getAmped2();
            StageConnector c = ga22.getStageConnector();
            if (c != null && e.getSource() == c.getContainer()) {
                LanPlayWindow.this._returntitle();
            }
        }
    }

    class BroadcastTranseiver extends DefaultTranseiver {
        BroadcastTranseiver() {
        }

        @Override
        public void received(Object src, int ch, Object o) {
        }
    }

    void _returntitle() {
        GetAmped2 ga22 = GetAmped2.getAmped2();
        try {
            if (this.ssr != null) {
                this.ssr.close();
            }
            this.ssr = null;
            this.ss = null;
            ga22.exitGameRoom();
            ga22.logout();
            if (this.myroominfo != null) {
                this.myroominfo.state = -1;
                this.broadcast.write(this.myroominfo, new InetSocketAddress("255.255.255.255", 30393));
                this.myroominfo = null;
            }
            ShutterManager.getManager().open();
            if (this.chproc != null) {
                this.chproc.processLanShutdown();
            }
            this.isSearchLanChannel = false;
            this.isBroadcastLan = false;
            Thread.sleep(300L);
            CompanyLogoScene logo = new CompanyLogoScene();
            logo.start(ga22);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void logout() {
        GetAmped2 ga22 = GetAmped2.getAmped2();
        try {
            ShutterManager.getManager().prepare(0);
            ShutterManager.getManager().closeAndWait();
            ga22.exitGameRoom();
            ga22.logout();
            ShutterManager.getManager().open();
            System.out.println("channel disconnected");
            TitleScene ts = new TitleScene();
            ts.start(ga22, true);
        } catch (Exception ee) {
            ee.printStackTrace();
            ga22.errorLogout(ee);
        }
    }

    ServerSetting lanServerSetting() {
        ServerSetting sset = new ServerSetting();
        sset.portbase = PortBase;
        sset.adminmasterport = PortBase + 501;
        sset.adminslaveport = PortBase + 502;
        sset.udpport = 0;
        sset.ch = new ChannelServerInfo[1];
        sset.ch[0] = new ChannelServerInfo();
        sset.ch[0].localaddr = null;
        sset.ch[0].globaladdr = null;
        ChannelInfo ci = new ChannelInfo();
        ci.chid = 0;
        ci.chgroup = 0;
        ci.chname = GameSetting.getSetting().getLocalText("$lanplay.channel.name");
        sset.ch[0].ch = new ChannelInfo[] { ci };
        sset.userdb = new DatabaseSetting[1];
        sset.userdb[0] = new DatabaseSetting();
        sset.userdb[0].type = 0;
        sset.userdb[0].host = null;
        sset.userdb[0].port = 777;
        sset.userdb[0].path = "server/userdb";
        sset.skindb = new DatabaseSetting[1];
        sset.skindb[0] = new DatabaseSetting();
        sset.skindb[0].type = 0;
        sset.skindb[0].host = null;
        sset.skindb[0].port = 778;
        sset.skindb[0].path = "server/skindb";
        sset.guilddb = new DatabaseSetting();
        sset.guilddb.type = 0;
        sset.guilddb.host = null;
        sset.guilddb.port = 779;
        sset.guilddb.path = "server/guilddb";
        sset.rankingdb = new DatabaseSetting();
        sset.rankingdb.type = 0;
        sset.rankingdb.host = null;
        sset.rankingdb.port = 780;
        sset.rankingdb.path = "server/ranking";
        sset.myroompicdb = new DatabaseSetting();
        sset.myroompicdb.type = 0;
        sset.myroompicdb.host = null;
        sset.myroompicdb.port = 783;
        sset.myroompicdb.path = "server/myroompicdb";
        sset.userautomake = true;
        sset.lanserver = true;
        return sset;
    }

    boolean loginRoom(String host) {
        return lanlogin(new String[] { host });
    }

    boolean lanlogin(String[] addr) {
        GetAmped2 ga22 = GetAmped2.getAmped2();
        GameSetting.getSetting();
        LoginProgressDialog dialog = new LoginProgressDialog();
        Thread t = loginproc(dialog, addr);
        AmpedDialog.showAmpedDialog(dialog, getKWTManager());
        if (dialog.canceled) {
            t.interrupt();
            ga22.errorLogout();
            return false;
        }
        try {
            t.join(10000L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (ga22.getConnectionState() == 0) {
            AmpedDialog.showErrorDialog(null, this.lastex, getKWTManager());
            return false;
        }
        try {
            InetSocketAddress la = (InetSocketAddress) ga22.getConnection().getLocalSocketAddress();
            System.out.println("channel connected : " + this.ci.chid + " " + this.ci.addr + "  localaddr("
                    + la.getAddress() + SOAP.DELIM + la.getPort());
            ga22.setChannels((ChannelInfo[]) ga22.command("get_channels", null));
            ChannelInfo chinfo = ga22.autoSelectChannel();
            if (chinfo != null) {
                ga22.enter(chinfo);
            }

            UserInfo ui = new UserInfo().set(GameSetting.getSetting().getNPCInfo(0));
            if (!ui.name.equals(this.charaname.getText())) {
                ga22.lannpcdefname = new Inttable();
                ga22.lannpcdefname.put(0, ui.name);
                ui.name = this.charaname.getText();
            }
            ui.adminflag = (byte) 6;
            ui.score = 60000000;
            ga22.command("chararemake", ui);
            StageConnector con = ga22.getStageConnector();
            if (con != null)
                con.updateUserData(ga22.getUserData());
            WorldMapScene.exitrunnable = new Runnable() {
                public void run() {
                    LanPlayWindow.this._returntitle();
                }
            };
            this.isBroadcastLan = false;
            close();
            return true;
        } catch (Exception ex2) {
            AmpedDialog.showErrorDialog(null, ex2, getKWTManager());
            ex2.printStackTrace();
            System.out.println("channel connection failed......");
            ga22.logout();
            _returntitle();
            return false;
        }
    }

    Thread loginproc(final LoginProgressDialog d, final String[] ip) {
        Thread t = new Thread() {
            @Override
            public void run() {
                GetAmped2 ga22 = GetAmped2.getAmped2();
                try {
                    Thread.sleep(700L);
                } catch (Exception e) {
                }
                ga22.setUserID("_lanplayAoko", StageConstants.clienttitle);
                LanPlayWindow.this.ci = new ChannelInfo();
                LanPlayWindow.this.ci.chid = -1;
                int i = (int) (Math.random() * ip.length);
                LanPlayWindow.this.lastex = null;
                int l = 0;
                while (true) {
                    if (l >= ip.length) {
                        break;
                    }
                    int port = PortBase;
                    String ipstr = ip[(i + l) % ip.length];
                    if (ipstr != null) {
                        if (ipstr.indexOf(58) >= 0) {
                            int c = ipstr.indexOf(58);
                            port = Integer.parseInt(ipstr.substring(c + 1));
                            ipstr = ipstr.substring(0, c);
                        }
                        LanPlayWindow.this.ci.addr = new InetSocketAddress(ipstr, port);
                        try {
                            System.out.println("login to " + LanPlayWindow.this.ci.addr);
                            ga22.login(LanPlayWindow.this.ci, d);
                            break;
                        } catch (AmpedException e2) {
                            if (e2.getErrorID() == 32) {
                                LanPlayWindow.this.lastex = e2;
                                System.out.println("login failed... " + e2 + "  retry:" + (l + 2) + "/" + ip.length);
                            } else {
                                LanPlayWindow.this.lastex = e2;
                                System.out.println("login failed... " + e2 + "  no retry.");
                                break;
                            }
                        }
                    }
                    l++;
                }
                d.close();
            }
        };
        t.start();
        return t;
    }

    Thread roommakeproc(final LoginProgressDialog d) {
        Thread t = new Thread() {
            @Override
            public void run() {
                GetAmped2 ga22 = GetAmped2.getAmped2();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                try {
                    ServerSetting sset = LanPlayWindow.this.lanServerSetting();
                    ServerSetting.setSetting(sset);
                    LanPlayWindow.this.chproc = new ChannelServerProcess();
                    LanPlayWindow.this.chproc.initLanPlay();
                    LanPlayWindow.this.ch = LanPlayWindow.this.chproc.getChannel(0);
                    LanPlayWindow.this.broadcast = new UDPConnection(ga22.getConnectionEngine());
                    LanPlayWindow.this.broadcast.setTranseiver(new BroadcastTranseiver());
                    LanPlayWindow.this.broadcast.openBroadcast(new InetSocketAddress(30393));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                d.completed("$lanplay.complete.makingroom");
                // ga22.setLanPlay(true);
                try {
                    Thread.sleep(500);
                } catch (Exception e3) {
                }
                d.close();
            }
        };
        t.start();
        return t;
    }

    void searchLanChannelproc() {
        Thread t = new Thread() {
            @Override
            public void run() { // broadcastlan
                while (LanPlayWindow.this.isBroadcastLan) {
                    try {
                        try {
                            String[] myaddr = new String[lanpcaddr.size()];
                            for (int i = 0; i < lanpcaddr.size(); i++) {
                                myaddr[i] = lanpcaddr.get(i);
                            }
                            for (int i2 = 0; i2 < 10; i2++) {
                                int port = 40000 + i2;
                                lanbroadcast.write(myaddr, new InetSocketAddress("255.255.255.255", port));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        };
        t.start();

        Thread t2 = new Thread() {
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                while (LanPlayWindow.this.isSearchLanChannel) {
                    try {
                        ArrayList<String> _lanList = new ArrayList<String>(lanpcaddr);
                        GetAmped2 ga22 = GetAmped2.getAmped2();
                        for (int i = 0; i < _lanList.size(); i++) {
                            String addr = _lanList.get(i);
                            String pcip = addr.substring(0, addr.indexOf(SOAP.DELIM));
                            ChannelInfo chinfo = new ChannelInfo();
                            chinfo.chid = -1;
                            chinfo.addr = new InetSocketAddress(pcip, PortBase);
                            String address = pcip + ":" + PortBase;
                            synchronized (ga22.lanchinfo) {
                                try {
                                    TCPConnection c = TCPConnection.open(chinfo.addr, LanPlayWindow.tcpconengine);
                                    c.lanconnecttest();
                                    if (!ga22.lanchinfo.contains(address)) {
                                        ga22.lanchinfo.add(address);
                                    }
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                    if (ga22.lanchinfo.contains(address)) {
                                        ga22.lanchinfo.remove(address);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        };
        t2.start();
    }

    public class ChannelListItem extends MouseAdaptor implements ActionListener {
        TextFigure addr;
        Container cnt;
        TextFigure index;

        ChannelListItem(ContainerData cd) {
            this.cnt = cd.createContainer(LanPlayWindow.this.srt.getLibrary(),
                    new ListItemContainer(LanPlayWindow.this, null));
            this.cnt.bnd.set(cd.bnd);
            this.addr = (TextFigure) LanPlayWindow.findNamedFigure("addr", this.cnt);
            this.index = (TextFigure) LanPlayWindow.findNamedFigure("index", this.cnt);
            this.cnt.addMouseListener(this);
            this.cnt.setCursor(KWTManager.getLibrary().getCursor("finger"));
        }

        void setInfo(String _addr, int idx) {
            this.addr.setText(_addr);
            this.index.setText(new StringBuilder().append(idx).toString());
            this.addr.reduceStrWidth();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            this.cnt.performMethod("mouseenter");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            this.cnt.performMethod("mouseexit");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            LanPlayWindow.this.roomselect = true;
            String host = this.addr.getText().substring(0, this.addr.getText().indexOf(SOAP.DELIM));
            boolean loginok = LanPlayWindow.this.loginRoom(host);
            if (!loginok) {
                LanPlayWindow.this.roomselect = false;
            }
        }

        public void actionPerformed(ActionEvent e) {
        }
    }

    public class PlayerListItem {
        TextFigure addr;
        Container cnt;
        TextFigure index;

        PlayerListItem(ContainerData cd) {
            this.cnt = cd.createContainer(LanPlayWindow.this.srt.getLibrary());
            this.cnt.bnd.set(cd.bnd);
            this.addr = (TextFigure) LanPlayWindow.findNamedFigure("addr", this.cnt);
            this.index = (TextFigure) LanPlayWindow.findNamedFigure("index", this.cnt);
        }

        void setInfo(String _addr, int idx) {
            this.addr.setText(_addr);
            this.index.setText(new StringBuilder().append(idx).toString());
            this.addr.reduceStrWidth();
        }
    }

    private class ListItemContainer extends Container {
        ListItemContainer(LanPlayWindow lanPlayWindow, ListItemContainer listItemContainer) {
            this();
        }

        private ListItemContainer() {
            setControlPosOffset(2, 2);
        }

        @Override
        public boolean isControllerTarget() {
            return true;
        }
    }

    @Override
    public boolean isControllerTarget(Component child) {
        for (int i = 0; i < this.npcface.length; i++) {
            if (child == this.npcface[i] && this.npcface[i].isVisible()) {
                return true;
            }
        }
        return child == this.c_techlist;
    }

    @Override
    public Component getCurrentControlComponent(int check) {
        return (check == 4100 || check == 4101) ? this.page : super.getCurrentControlComponent(check);
    }
}