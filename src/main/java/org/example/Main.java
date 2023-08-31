package org.example;

import org.example.utils.HookUtils;
import org.example.utils.PropertyUtils;

public class Main {

    static PropertyUtils propertyUtils = PropertyUtils.getInstance();

    public static void main(String[] args) {
        HookUtils.registerHook();
        while (!HookUtils.exitIssued) {
            try {
                Thread.sleep(Integer.parseInt(propertyUtils.getStringProperty("writeEverySeconds")) * 1000);
                HookUtils.updateLogFile();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        HookUtils.unregisterHook();

    }
}