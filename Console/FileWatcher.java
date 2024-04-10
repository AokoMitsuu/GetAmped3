import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileWatcher {
    private File file;
    private Set<String> linesPrinted;
    private boolean displayExistingLines;

    public FileWatcher(String filePath, boolean displayExistingLines) {
        this.file = new File(filePath);
        this.linesPrinted = new HashSet<String>();
        this.displayExistingLines = displayExistingLines;
    }

    private String applyColor(String line) {
        // Remplacez les balises personnalisées par les codes ANSI correspondants
        line = line.replaceAll("<green>", "\u001B[32m");
        line = line.replaceAll("</green>", "\u001B[0m");

        line = line.replaceAll("<red>", "\u001B[31m");
        line = line.replaceAll("</red>", "\u001B[0m");

        line = line.replaceAll("<yellow>", "\u001B[33m");
        line = line.replaceAll("</yellow>", "\u001B[0m");

        line = line.replaceAll("<white>", "\u001B[37m");
        line = line.replaceAll("</white>", "\u001B[0m");

        return line;
    }

    public void watchFile() throws InterruptedException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            if (displayExistingLines) {
                while ((line = reader.readLine()) != null) {
                    System.out.println(applyColor(line));
                    linesPrinted.add(line);
                }
            } else {
                while ((line = reader.readLine()) != null) {
                    linesPrinted.add(line);
                }
            }

            reader.close();

            while (true) {
                reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null) {
                    if (!linesPrinted.contains(line)) {
                        System.out.println(applyColor(line));
                        linesPrinted.add(line);
                    }
                }
                reader.close();
                Thread.sleep(500);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.out.println("Usage: java FileWatcher <filePath> <displayExistingLines>");
            return;
        }
        FileWatcher watcher = new FileWatcher(args[0], Boolean.parseBoolean(args[1]));
        watcher.watchFile();
    }
}
