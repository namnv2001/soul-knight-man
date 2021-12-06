package master.soulknight.Util;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;

public class MouseHandler implements EventHandler<MouseEvent> {
    Vector2f pos;
    final private Set<Vector2f> activePos = new HashSet<>();

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (MouseEvent.MOUSE_CLICKED.equals(mouseEvent.getEventType())) {
            System.out.println("CLICKED");
            pos = new Vector2f((float) mouseEvent.getX(), (float) mouseEvent.getY());
            System.out.println(pos);
        }
    }

    public Vector2f getPos() {
        return pos;
    }

    public void resetPos() {
        pos = null;
    }
}
