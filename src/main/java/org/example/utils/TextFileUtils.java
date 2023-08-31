package org.example.utils;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
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
                if (file.isHidden()) {
                    Files.setAttribute(Paths.get(path), "dos:hidden", false, LinkOption.NOFOLLOW_LINKS);
                }
                FileWriter myWriter = new FileWriter(path);
                myWriter.write(content);
                myWriter.close();
                Files.setAttribute(Paths.get(path), "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
                System.out.println("Done.");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } else if (!file.exists()) {
            System.out.println("Creating new file...");
            try {
                file.createNewFile();
                writeFile(path, content, overwrite);
                System.out.println("Done.");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
