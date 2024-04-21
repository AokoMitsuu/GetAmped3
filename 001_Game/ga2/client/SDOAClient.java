package ga2.client;

import ga2.setting.GameSetting;

public class SDOAClient {

    static AppInfo definfo;

    public static class LoginResult {
        public int nErrorCode;
        public int nUserData;
        public String pbstrContent;
        public String pbstrTitle;
        public String szAppendix;
        public String szIdentityState;
        public String szSessionId;
        public String szSndaid;
    }

    public static native void close();

    public static native void decodeGPK(byte[] bArr, int i, int i2);

    public static native void encodeGPK(byte[] bArr, int i, int i2);

    public static native LoginResult getLoginResult();

    public static native void launcherend();

    public static native void launcherstart();

    public static native void logout();

    public static native void modifyAppInfo(int i, String str, String str2, int i2, int i3);

    public static native void open(int i, String str, String str2, int i2, int i3, String str3);

    public static native void openGPK();

    public static native int queryBalance();

    public static native void setGPKDynCode(byte[] bArr);

    public static native void showLoginDialog();

    public static native void showPaymentDialog(String str);

    public static void open(String serverip, String verstr) {
        GameSetting.getSetting().sdoa = false;
        // apptable = new HashMap();
        // GameSetting set = GameSetting.getSetting();
        // for (int i = 0; i < set.sdoaip.list.length; i++) {
        // apptable.put(set.sdoaip.list[i].ip,
        // new AppInfo(109, "GetAmped2", "1.0.0.1", set.sdoaip.list[i].areaid,
        // set.sdoaip.list[i].groupid));
        // }
        // definfo = new AppInfo(109, "GetAmped2", "1.0.0.1", 1, 1);
        // AppInfo a = (AppInfo) apptable.get(serverip);
        // if (a == null) {
        // a = definfo;
        // }
        // open(a.appid, a.appname, a.appvar, a.areaid, a.groupid, verstr);
    }

    public static void modifyAppInfo(String serverip) {
        // AppInfo a = (AppInfo) apptable.get(serverip);
        // if (a == null) {
        // a = definfo;
        // }
        // modifyAppInfo(a.appid, a.appname, a.appvar, a.areaid, a.groupid);
    }

    public static int checkBalance() {
        // int b = queryBalance();
        // System.out.println("sdoa queryBalance : " + b);
        // if (b < 0) {
        // b = 0;
        // }
        // if (b >= 0) {
        // GetAmped2 ga22 = GetAmped2.getAmped2();
        // try {
        // ga22.command("sdoamoney", new int[] { ga22.getUserInfo().useridhash, b });
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // }
        // synchronized (ga22) {
        // ga22.getUserItems().setRealMoney(b, true);
        // ga22.updateUserData(ga22.getUserItems());
        // }
        // }
        // return b;
        return 0;
    }

    public static LoginResult createres(int ec, int ud, String sid, String id, String is, String ap, String ti,
            String cnt) {
        LoginResult r = new LoginResult();
        r.nErrorCode = ec;
        r.nUserData = ud;
        r.szSessionId = sid;
        r.szSndaid = id;
        r.szIdentityState = is;
        r.szAppendix = ap;
        r.pbstrTitle = ti;
        r.pbstrContent = cnt;
        return r;
    }

    public static class AppInfo {
        int appid;
        String appname;
        String appvar;
        int areaid;
        int groupid;

        AppInfo(int id, String n, String v, int aid, int gid) {
            this.appid = id;
            this.appname = n;
            this.appvar = v;
            this.areaid = aid;
            this.groupid = gid;
        }
    }
}