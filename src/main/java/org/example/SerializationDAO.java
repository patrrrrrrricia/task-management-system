package org.example.dao;
import java.io.*;
import java.util.*;

public class SerializationDAO {
    private static final String FILE_NAME = "data.ser";

    public void saveData(Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(data);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public Object loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return ois.readObject();
        } catch (Exception e) { return null; }
    }
}