package org.example.utils;

public class ShutdownThread extends Thread {

    public void run() {
        System.out.println("Running cleanup, saving last contents...");
        HookUtils.updateLogFile();
        System.out.println("Done.");
    }

}
