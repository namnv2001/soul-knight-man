package master.soulknight.Util;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class KeyHandler implements EventHandler<KeyEvent> {
    final private Set<KeyCode> activeKeys = new HashSet<>();

    @Override
    public void handle(KeyEvent keyEvent) {
        if (KeyEvent.KEY_PRESSED.equals(keyEvent.getEventType())) {
            activeKeys.add(keyEvent.getCode());
        } else if (KeyEvent.KEY_RELEASED.equals(keyEvent.getEventType())) {
            activeKeys.remove(keyEvent.getCode());
        }
    }

    public Set<KeyCode> getSetActiveKeys() {
        return activeKeys;
    }

    public Set<KeyCode> getActiveKeys() {
        return Collections.unmodifiableSet(activeKeys);
    }
}
