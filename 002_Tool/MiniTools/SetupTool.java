package MiniTools;

import java.io.*;
import ga2.client.ui.LanPlayWindow;
import kotori.io.KxrFile;

public class SetupTool {
    public static void main(String[] args) throws Exception {
        if (args[0].equals("-transfert")) {
            System.out.println("Start transfert");
            for (String string : args) {
                if (string.equals("-transfert"))
                    continue;

                File sourceFile = new File("../001_Game/bin/" + string);
                File destFile = new File("./bin/" + string);

                try {
                    copyFile(sourceFile, destFile);
                    System.out.println("Transfert terminé avec succès!");
                } catch (IOException e) {
                    System.out.println("Erreur lors de la copie du fichier : " + e.getMessage());
                }
            }

        } else if (args[0].equals("-copy")) {
            String[] res = new String[] {
                    "accs", "action", "chara", "efx", "icon", "illust", "item", "kwtamped2",
                    "kwttool", "lang", "myroom", "snd", "special", "stage", "test",
                    "#version.1.kxr", "#version.2.kxr", "#version.3.kxr", "#version.4.kxr",
                    "#version.5.kxr"
            };

            File srcDir = new File("./");
            File destDir = new File("./resource");

            System.out.println("Start Copie");
            for (int i = 0; i < res.length; i++) {
                try {
                    File srcFile = new File(srcDir, res[i]);
                    File destFile = new File(destDir, res[i]);
                    if (!destFile.getParentFile().exists()) {
                        destFile.getParentFile().mkdirs(); // Créer tous les répertoires parents nécessaires
                    }
                    copyDirectory(srcFile, destFile);
                } catch (IOException e) {
                    System.out.println("Erreur lors de la copie: " + e.getMessage());
                }
            }
            System.out.println("Copie terminée avec succès!");

        } else if (args[0].equals("-clean")) {
            String[] res = new String[] {
                    "accs", "action", "chara", "efx", "icon", "illust", "item", "kwtamped2",
                    "kwttool", "lang", "myroom", "snd", "special", "stage", "test",
                    "#version.1.kxr", "#version.2.kxr", "#version.3.kxr", "#version.4.kxr",
                    "#version.5.kxr"
            };

            String parentDirectory = "./";
            System.out.println("Start delete");
            for (int i = 0; i < res.length; i++) {
                File file = new File(parentDirectory, res[i]);
                deleteRecursively(file);
            }
            System.out.println("Delete terminé avec succès!");
        } else {
            System.out.println("Start build kxr");
            KxrFile.main(args);
            System.out.println("Build Kxr terminé avec succès!");
        }
    }

    private static void copyDirectory(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }

            String files[] = src.list();

            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);

                copyDirectory(srcFile, destFile);
            }
        } else {
            FileInputStream in = new FileInputStream(src);
            FileOutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }

    private static void deleteRecursively(File file) {
        if (file.isDirectory()) {
            String[] children = file.list();
            for (String child : children) {
                File childFile = new File(file, child);
                deleteRecursively(childFile);
            }
        }
        if (!file.delete()) {
            System.out.println("Échec de la suppression :: " + file.getAbsolutePath());
        }
    }

    private static void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                System.out.println("Erreur lors de la fermeture des flux : " + e.getMessage());
            }
        }
    }
}

// "bin/3.kxr", "aoao", "myroom", "stage", "#version.3.kxr"