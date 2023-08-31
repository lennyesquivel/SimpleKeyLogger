package org.example;

import org.example.utils.HookUtils;
import org.example.utils.PropertyUtils;
import org.example.utils.ShutdownThread;

public class Main {

    static PropertyUtils propertyUtils = PropertyUtils.getInstance();

    public static void main(String[] args) {
        HookUtils.registerHook();
        Runtime.getRuntime().addShutdownHook(new ShutdownThread());
        while (!HookUtils.exitIssued) {
            try {
                Thread.sleep(Integer.parseInt(propertyUtils.getStringProperty("writeEverySeconds")) * 1000L);
                HookUtils.updateLogFile();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        HookUtils.unregisterHook();
    }

}