package com.nr.tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class LocalStorage {
    static final String FilePath = "bin/cache/localStorage";

    static LocalStorage _LocalStorage;

    public static LocalStorage getInstance() {
        if (_LocalStorage == null) {
            _LocalStorage = new LocalStorage();
        }
        return _LocalStorage;
    }

    HashMap<String, String> maps;

    public String get(String key) {
        try {
            this.Read();
        } catch (Exception e) {
        }

        if (this.maps == null)
            this.maps = new HashMap<String, String>();
        String ret = this.maps.get(key);
        if (ret == null)
            return "";
        return ret;
    }

    public void set(String key, String val) {
        try {
            this.Read();
        } catch (Exception e) {
        }

        if (this.maps == null)
            this.maps = new HashMap<String, String>();
        this.maps.put(key, val);

        try {
            this.Write();
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("resource")
    public void Write() throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(FilePath);
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        oos.writeObject(this.maps);
        oos.close();
    }

    @SuppressWarnings({ "unchecked", "resource" })
    public void Read() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(FilePath);
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        this.maps = (HashMap<String, String>) ois.readObject();
        ois.close();
    }
}
