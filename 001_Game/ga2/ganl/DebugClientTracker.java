package ga2.ganl;

import ga2.client.GetAmped2;
import ga2.cybergarage.soap.SOAP;
import ga2.data.UserData;
import ga2.data.UserInfo;
import ga2.stage.StageConnector;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.TimeZone;

public class DebugClientTracker extends Thread {
    private static String ACCOUNT = "UA-31681671-11";
    private static int SLEEP_TIME = 1000;
    // private static DebugClientTracker tracker;
    private GAnlManager gam;
    String ipaddr;
    String macaddr;
    String pcname;
    String userdata;
    String zone;

    private DebugClientTracker() {
        GAnlManager gAnlManager = new GAnlManager(ACCOUNT, true);
        this.gam = gAnlManager;
        gAnlManager.startThread();
        this.userdata = "nodata";
        this.pcname = "nodata";
        this.ipaddr = "nodata";
        this.macaddr = "nodata";
        this.zone = "nodata";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            this.ipaddr = ip.getHostAddress();
            this.pcname = ip.getHostName();
        } catch (Exception e) {
        }
        try {
            this.zone = TimeZone.getDefault().getID();
        } catch (Exception e2) {
        }
        try {
            if (Float.parseFloat(System.getProperty("java.specification.version")) >= 1.6f) {
                Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
                while (nics.hasMoreElements()) {
                    NetworkInterface ni = nics.nextElement();
                    byte[] addrbyte = ni.getHardwareAddress();
                    if (addrbyte != null && addrbyte.length > 0) {
                        String mac = "";
                        for (int i = 0; i < addrbyte.length; i++) {
                            mac = String.valueOf(mac) + String.format("%02x", Byte.valueOf(addrbyte[i]));
                        }
                        if (!mac.equals("") && !mac.equals("00000000000000e0")) {
                            this.macaddr = mac;
                            return;
                        }
                    }
                }
                return;
            }
            this.macaddr = "ver" + System.getProperty("java.version");
        } catch (Exception e3) {
        }
    }

    public static void startTracking() {
        // if (GetAmped2.getAmped2() != null) {
        // DebugClientTracker debugClientTracker = new DebugClientTracker();
        // tracker = debugClientTracker;
        // debugClientTracker.start();
        // }
    }

    public static void endTracking() {
        // tracker.stopThread();
    }

    public void stopThread() {
        this.gam.stopThread();
        interrupt();
    }

    private void tracking() {
        UserData ud;
        if (!this.gam.isRunning()) {
            System.exit(0);
        }
        StageConnector con = GetAmped2.getAmped2().getStageConnector();
        if (con == null || (ud = con.getUserData()) == null) {
            return;
        }
        UserInfo i = ud.info;
        String str = String
                .valueOf(String.valueOf(i.name) + "(" + ((int) i.style) + SOAP.DELIM + ((int) i.head) + ","
                        + ((int) i.face) + "," + ((int) i.body) + "," + ((int) i.deco) + "," + i.skin + ")")
                + "+" + ((int) i.accs);
        if (!str.equals(this.userdata)) {
            this.userdata = str;
            Calendar now = Calendar.getInstance();
            String time = String.valueOf("[") + now.get(10) + SOAP.DELIM;
            this.gam.pageTracking(this.ipaddr.hashCode(),
                    String.valueOf(String.valueOf(String.valueOf(time) + now.get(12) + SOAP.DELIM) + now.get(13) + "]")
                            + " pc:" + this.pcname + " ip:" + this.ipaddr + " mac:" + this.macaddr + " zone:"
                            + this.zone,
                    this.userdata);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                tracking();
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}