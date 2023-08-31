package org.example.utils;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileUtils {

    public static File getFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            System.out.println("File loaded successfully.");
        } else {
            System.out.println("File not found, creating new one...");
            writeFile(path, "", true);
        }
        return file;
    }

    public static void updateFile(String path, String content) {
        String data = null;
        try {
            data = new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception ex) {
            data = "";
            System.err.println(ex.getMessage());
        }
        writeFile(path, data + "\n" + content, true);
    }

    public static void writeFile(String path, String content, boolean overwrite) {
        File file = new File(path);
        if (file.exists() && overwrite) {
            System.out.println("Overwriting into file...");
            try {
                FileWriter myWriter = new FileWriter(path);
                myWriter.write(content);
                myWriter.close();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } else if (!file.exists()) {
            System.out.println("Creating new file...");
            try {
                file.createNewFile();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        System.out.println("Done.");
    }

}
