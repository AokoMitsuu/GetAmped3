import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileWatcher {
    private File file;
    private Set<String> linesPrinted;

    public FileWatcher(String filePath, boolean displayExistingLines, boolean displayCallStack) {
        this.file = new File(filePath);
        this.linesPrinted = new HashSet<String>();
    }

    public void watchFile() throws InterruptedException {
        try {
            BufferedReader reader;
            String line;

            while (true) {
                reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null) {
                    if (!linesPrinted.contains(line)) {
                        System.out.println(line);
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