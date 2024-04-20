/*    */
package tool;

/*    */
/*    */ import kotori.app.Console;

/*    */
/*    */ public class JRunner
/*    */ {
    /*    */ public static void main(String[] args) throws Exception {
        /* 8 */ if (args.length == 0)
            System.exit(1);
        /* 9 */ String name = args[0];
        /*    */
        /* 11 */ if (name.endsWith(".scm") || name.endsWith(".kmd") ||
        /* 12 */ name.endsWith(".mot") || name.endsWith(".skn")) {
            /* 13 */ name = remdir(name);
            /* 14 */ KWTApp.main(new String[] { "tool.ScriptModelEditor", "-f", name });
            /*    */ }
        /* 16 */ else if (name.endsWith(".agi")) {
            /*    */
            /* 18 */ SimpleAGIViewer v = new SimpleAGIViewer();
            /* 19 */ v.appmain(Console.getConsole(), new String[] { name });
            /*    */ }
        /*    */ }

    /*    */
    /*    */ static String remdir(String s) {
        /* 24 */ s = s.replace(System.getProperty("file.separator").charAt(0), '/');
        /* 25 */ int i = s.indexOf("resource");
        /* 26 */ s = s.substring(i + 8);
        /* 27 */ return s;
        /*    */ }
    /*    */ }

/*
 * Location: C:\Users\aokom\Desktop\GetAmped\lib\classes.jar!\tool\JRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version: 1.1.3
 */