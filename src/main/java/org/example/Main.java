package org.example;

import org.example.utils.HookUtils;

public class Main {
    public static void main(String[] args) {
        HookUtils.registerHook();
        while (!HookUtils.exitIssued) {
            //start timer thread for 15 minutes to save file per hour

        }
    }
}