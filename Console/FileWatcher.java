import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileWatcher {
    private File file;
    private Set<String> linesPrinted;
    private boolean displayExistingLines;
    private boolean displayCallStack;

    public FileWatcher(String filePath, boolean displayExistingLines, boolean displayCallStack) {
        this.file = new File(filePath);
        this.linesPrinted = new HashSet<String>();
        this.displayExistingLines = displayExistingLines;
        this.displayCallStack = displayCallStack;
    }

    private String applyColor(String line) {
        // Remplacez les balises personnalisées par les codes ANSI correspondants pour
        // les couleurs
        line = line.replaceAll("<green>", "\u001B[32m").replaceAll("</green>", "\u001B[0m");
        line = line.replaceAll("<red>", "\u001B[31m").replaceAll("</red>", "\u001B[0m");
        line = line.replaceAll("<yellow>", "\u001B[33m").replaceAll("</yellow>", "\u001B[0m");
        line = line.replaceAll("<white>", "\u001B[37m").replaceAll("</white>", "\u001B[0m");

        line = line.replaceAll("<endLine>", "\n");
        // Gestion de la balise <callstack>
        if (!displayCallStack) {
            line = line.replaceAll("<callstack>.*?</callstack>", "");
        } else {
            line = line.replaceAll("<callstack>", "").replaceAll("</callstack>", "");
        }

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
        if (args.length != 3) {
            System.out.println("Usage: java FileWatcher <filePath> <displayExistingLines> <displayCallStack>");
            return;
        }
        FileWatcher watcher = new FileWatcher(args[0], Boolean.parseBoolean(args[1]), Boolean.parseBoolean(args[2]));
        watcher.watchFile();
    }
}