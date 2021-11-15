package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.Vector2f;


public class Player extends Entity {

    private KeyCode direction;

    public Player(SpriteSheet sprite, Vector2f origin, int size, double SCALING) {
        super(sprite, origin, size, SCALING);
    }

    public void handleKeyPressedEvent(KeyCode keycode) {
                if (!fallen) {
            if (keycode == KeyCode.W) {
                direction  = keycode;
                up = true;
            } else {
                up = false;
            }
            if (keycode == KeyCode.S) {
                direction  = keycode;
                down = true;
            } else {
                down = false;
            }
            if (keycode == KeyCode.A) {
                direction  = keycode;
                left = true;
            } else {
                left = false;
            }
            if (keycode == KeyCode.D) {
                direction  = keycode;
                right = true;
            } else {
                right = false;
            }
            if (keycode == KeyCode.SPACE) {
                placeBoom = true;
            }
        }
    }

    public void handleKeyReleasedEvent(KeyCode keycode) {
        if (fallen) return;
        if (direction == keycode) {
            if (direction == KeyCode.A) {
                left = false;
            }
            if (direction == KeyCode.D) {
                right = false;
            }
            if (direction == KeyCode.W) {
                up = false;
            }
            if (direction == KeyCode.S) {
                down = false;
            }
            direction = null;
        }
        if (keycode == KeyCode.SPACE) {
            placeBoom = false;
        }
    }

    public void update() {
        super.update();
        if (!fallen) {
            move();
            pos.x += x;
            pos.y += y;
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y
                , SpriteSheet.getTileSize() * Entity.getSCALING(), SpriteSheet.getTileSize() * Entity.getSCALING());
    }
}
