package com.nr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nr.tool.LocalStorage;

import ga2.client.GetAmped2;
import ga2.data.AmpedItem;
import ga2.data.UserData;
import ga2.data.UserItems;
import ga2.data.UserShortcuts.AvatorShortcut;
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

    static void getFullItems(int kind, List<UserItems.Item> itemList, AmpedItem[] ais) {
        long time = System.currentTimeMillis();
        for (int l = 0, len = ais.length; l < len; ++l) {
            AmpedItem ai = ais[l];
            if (ai == null || ai.isPrivately() || ai.getIcon() == null)
                continue;
            itemList.add(new UserItems.Item((byte) kind, l, 0, 15, time, 0));
        }
    }

    public static List<UserItems.Item> getAllAccs() {
        GameSetting set = GameSetting.getSetting();
        ArrayList<UserItems.Item> items = new ArrayList<UserItems.Item>();
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

    public static void Print(Object textToPrint, String color) {
        PrintLn(textToPrint, color);
    }

    public static void Print(Object textToPrint) {
        PrintLn(textToPrint, ColorAsString.Green);
    }

    public static void PrintWarm(Object textToPrint) {
        PrintLn(textToPrint, ColorAsString.Yellow);
    }

    public static void PrintError(Object textToPrint) {
        PrintLn(textToPrint, ColorAsString.Red);
    }

    private static void PrintLn(Object textToPrint, String color) {
        PrintLn(String.valueOf(textToPrint), color);
    }

    private static void PrintLn(String textToPrint, String color) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        String contentToWrite = "<" + color + ">" + currentTime + " : " + textToPrint + "</" + color + ">";

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
            avatorShortcut.skin = Integer.valueOf(shortcuts[7]);
            avatorShortcut.voice = Short.valueOf(shortcuts[8]);
            avatorShortcut.build = Short.valueOf(shortcuts[9]);

            ud.shortcuts.avatorsc[i] = avatorShortcut;
        }
        return ud;
    }

    public static class ColorAsString {
        public static String Green = "green";
        public static String Red = "red";
        public static String Yellow = "yellow";
        public static String White = "white";
    }
}
