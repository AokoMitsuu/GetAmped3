import java.io.PrintStream;

import ga2.client.ui.LanPlayWindow;
import kotori.io.KxrFile;

public class Repack {
    public static void main(String[] args) throws Exception {
        System.err.println("start");
        // KxrFile.main(new String[] { "bin/5.kxr", "aoao", "lang", "kwtamped2",
        // "#version.5.kxr" });
        // KxrFile.main(new String[] { "bin/5.kxr", "aoao", "lang", "kwtamped2",
        // "#version.5.kxr" });
        KxrFile.main(new String[] { "bin/3.kxr", "aoao", "myroom", "stage", "#version.3.kxr" });
        // KxrFile.main(new String[] { "bin/5.kxr", "aoao", "lang", "kwtamped2",
        // "#version.5.kxr" });
        // KxrFile.main(new String[] { "bin/5.kxr", "aoao", "lang", "kwtamped2",
        // "#version.5.kxr" });
    }
}