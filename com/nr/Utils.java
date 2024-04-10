package com.nr;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nr.tool.LocalStorage;

import ga2.data.AmpedItem;
import ga2.data.UserData;
import ga2.data.UserItems;
import ga2.data.UserItems.Item;
import ga2.data.UserShortcuts.AvatorShortcut;
import ga2.data.net.AmpedException;
import ga2.setting.GameSetting;

public class Utils {

    public static String[] mergeStringArray(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            c[a.length + i] = b[i];
        }
        return c;
    }

    @SuppressWarnings("resource")
    public static void saveTextToFile(String filePath, String text) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] removeNull(T[] array, Class<T> clazz) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                count++;
            }
        }

        T[] result = (T[]) Array.newInstance(clazz, count);
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                result[index] = array[i];
                index++;
            }
        }

        return result;
    }

    static void getFullItems(int kind, List<Item> itemList, AmpedItem[] ais) {
        long time = System.currentTimeMillis();
        for (int l = 0, len = ais.length; l < len; ++l) {
            AmpedItem ai = ais[l];
            if (ai == null || ai.isPrivately() || ai.getIcon() == null)
                continue;
            itemList.add(new Item((byte) kind, l, 0, 15, time, 0));
        }
    }

    public static List<Item> getAllAccs() {
        GameSetting set = GameSetting.getSetting();
        ArrayList<Item> items = new ArrayList<Item>();
        getFullItems(0, items, set.style);
        getFullItems(5, items, set.accs);
        getFullItems(16, items, set.voiceset);
        getFullItems(4, items, set.deco);
        return items;
    }

    public static void accsTuneup(UserItems.Item[] items, int skill0, int skill1, int skill2, int skill3) {
        for (int i = 0, len = items.length; i < len; i++) {
            UserItems.Item item = items[i];
            if (item == null || item.kind != 5)
                continue;
            item.setPotential(skill0, 20);
            item.setTuneUp(0, skill1);
            item.setTuneUp(1, skill2);
            item.setTuneUp(2, skill3);
        }
    }

    // #region Console
    public static void Print(Object textToPrint, String color) {
        PrintLn(textToPrint, color, false);
    }

    public static void Print(Object textToPrint, boolean showCallstack) {
        PrintLn(textToPrint, ColorAsString.Green, showCallstack);
    }

    public static void Print(Object textToPrint, String color, boolean showCallstack) {
        PrintLn(textToPrint, color, showCallstack);
    }

    public static void Print(Object textToPrint) {
        PrintLn(textToPrint, ColorAsString.Green, false);
    }

    public static void PrintWarm(Object textToPrint) {
        PrintLn(textToPrint, ColorAsString.Yellow, true);
    }

    public static void PrintError(Object textToPrint) {
        PrintLn(textToPrint, ColorAsString.Red, true);
    }

    private static void PrintLn(Object textToPrint, String color, boolean showCallstack) {
        PrintLn(String.valueOf(textToPrint), color, showCallstack);
    }

    private static void PrintLn(String textToPrint, String color, boolean showCallstack) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        String callstack = "";
        if (showCallstack) {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            int maxCallStack = 5;
            for (int i = 2; i < maxCallStack; i++) {
                if (i >= stackTraceElements.length)
                    continue;

                StackTraceElement caller = stackTraceElements[i];
                callstack += "<endLine>fichier : " + caller.getClassName() + " (ligne:" + caller.getLineNumber()
                        + ") : Fonction : "
                        + caller.getMethodName();
            }
        }

        String contentToWrite = "<" + color + ">" + currentTime + " : " + textToPrint
                + " <callstack>" + callstack + "</callstack> </" + color + "><endLine>";

        BufferedWriter writer = null;
        try {
            File file = new File("logs.txt");
            boolean append = file.exists();
            writer = new BufferedWriter(new FileWriter(file, append));

            if (append) {
                writer.newLine();
            }

            writer.write(contentToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // #endregion

    public static void SaveShortCuts(UserData ud) {
        if (ud.shortcuts.avatorsc == null)
            return;

        String serializeShortcuts = "";

        for (int i = 0; i < ud.shortcuts.avatorsc.length; i++) {
            AvatorShortcut shortcuts = ud.shortcuts.avatorsc[i];
            if (shortcuts == null)
                continue;

            serializeShortcuts += shortcuts.style + ";";
            serializeShortcuts += shortcuts.head + ";";
            serializeShortcuts += shortcuts.face + ";";
            serializeShortcuts += shortcuts.body + ";";
            serializeShortcuts += shortcuts.deco + ";";
            serializeShortcuts += shortcuts.accs + ";";
            serializeShortcuts += shortcuts.weapon + ";";
            serializeShortcuts += shortcuts.skin + ";";
            serializeShortcuts += shortcuts.voice + ";";
            serializeShortcuts += shortcuts.build + "/";
        }
        LocalStorage.getInstance().set("SerializeShortcuts", serializeShortcuts);
    }

    public static UserData LoadShortCuts(UserData ud) {
        ud.shortcuts.avatorsc = new AvatorShortcut[9];
        String SerializeShortcuts = LocalStorage.getInstance().get("SerializeShortcuts");
        if (SerializeShortcuts == "")
            return ud;

        String[] shortcutsList = SerializeShortcuts.split("/");

        for (int i = 0; i < shortcutsList.length; i++) {
            String[] shortcuts = shortcutsList[i].split(";");
            AvatorShortcut avatorShortcut = new AvatorShortcut();
            avatorShortcut.style = Short.valueOf(shortcuts[0]);
            avatorShortcut.head = Short.valueOf(shortcuts[1]);
            avatorShortcut.face = Short.valueOf(shortcuts[2]);
            avatorShortcut.body = Short.valueOf(shortcuts[3]);
            avatorShortcut.deco = Short.valueOf(shortcuts[4]);
            avatorShortcut.accs = Short.valueOf(shortcuts[5]);
            avatorShortcut.weapon = Short.valueOf(shortcuts[6]);
            avatorShortcut.voice = Short.valueOf(shortcuts[8]);
            avatorShortcut.build = Short.valueOf(shortcuts[9]);

            ud.shortcuts.avatorsc[i] = avatorShortcut;
        }
        return ud;
    }

    public static UserData LoadSkin(UserData ud) {
        ArrayList<Item> skins;
        try {
            skins = ((ArrayList<Item>) com.nr.Utils.deserializeObject(LocalStorage.getInstance().get("Skins")));
            com.nr.Utils.Print("load : " + skins.size(), ColorAsString.Yellow, false);

            for (Item item : skins) {
                try {
                    ud.items.AddSkin(item);
                } catch (AmpedException e) {
                    com.nr.Utils.PrintError(e.getMessage());
                }
            }
        } catch (ClassNotFoundException e) {
            com.nr.Utils.PrintError(e.getMessage());
        } catch (IOException e) {
            com.nr.Utils.PrintError(e.getMessage());
        }

        return ud;
    }

    public static void SaveSkins(UserItems us) {
        Item[] items = us.getItems();
        ArrayList<Item> skins = new ArrayList<Item>();
        for (Item item : items) {
            if (item.kind != 9)
                continue;

            skins.add(item);
        }
        com.nr.Utils.Print("saving : " + skins.size(), ColorAsString.Yellow, false);

        try {
            LocalStorage.getInstance().set("Skins", com.nr.Utils.serializeObject(skins));
        } catch (IOException e1) {
            com.nr.Utils.PrintError(e1.getMessage());
        }

        com.nr.Utils.Print("success save Skin");
    }

    public static class ColorAsString {
        public static String Green = "green";
        public static String Red = "red";
        public static String Yellow = "yellow";
        public static String White = "white";
    }

    // #region Serialize / Deserialize Object
    public static String serializeObject(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.close();
        return toBinaryString(baos.toByteArray());
    }

    // Désérialisation de la chaîne binaire en objet
    public static Object deserializeObject(String binaryString) throws IOException, ClassNotFoundException {
        byte[] data = fromBinaryString(binaryString);
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

    // Conversion de byte[] en chaîne binaire
    public static String toBinaryString(byte[] bytes) {
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }

    // Conversion de chaîne binaire en byte[]
    public static byte[] fromBinaryString(String s) {
        int sLen = s.length();
        byte[] toReturn = new byte[sLen / 8];
        for (int i = 0; i < sLen; i += 8) {
            toReturn[i / 8] = (byte) Integer.parseInt(s.substring(i, i + 8), 2);
        }
        return toReturn;
    }
    // #endregion
}
