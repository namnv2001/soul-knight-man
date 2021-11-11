package master.soulknight.Entities;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.Vector2f;


public class Player extends Entity {

    private KeyCode direction;

    public Player(SpriteSheet sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
    }

    public void input(KeyEvent key) {
        if (!fallen) {
            if (key.getCode() == KeyCode.W) {
                up = true;
            } else {
                up = false;
            }
            if (key.getCode() == KeyCode.S) {
                down = true;
            } else {
                down = false;
            }
            if (key.getCode() == KeyCode.A) {
                left = true;
            } else {
                left = false;
            }
            if (key.getCode() == KeyCode.D) {
                right = true;
            } else {
                right = false;
            }
            if (key.getCode() == KeyCode.SPACE) {
                placeBoom = true;
            }
            if (up && down) {
                up = false;
                down = false;
            }
            if (left && right) {
                left = false;
                right = false;
            }
        } else {
            up = false;
            down = false;
            left = false;
            right = false;
            placeBoom = false;
        }
    }

    public void handleKeyPressedEvent(KeyCode keycode) {
        if (keycode == KeyCode.LEFT) {
            direction  = keycode;
            left = true;
        } else if (keycode == KeyCode.RIGHT) {
            direction  = keycode;
            right = true;
        } else if (keycode == KeyCode.UP) {
            direction  = keycode;
            up = true;
        } else if (keycode == KeyCode.DOWN) {
            direction  = keycode;
            down = true;
        }
        if (keycode ==  KeyCode.SPACE) {
            placeBoom = true;
        }
    }

    public void handleKeyReleasedEvent(KeyCode keycode) {
        if (direction == keycode) {
            if (direction == KeyCode.LEFT) {
                left = false;
            }
            if (direction == KeyCode.RIGHT) {
                right = false;
            }
            if (direction == KeyCode.UP) {
                up = false;
            }
            if (direction == KeyCode.DOWN) {
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
            if (left || right) {
                pos.x += dx;
            } else if (up || down) {
                pos.y += dy;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(SwingFXUtils.toFXImage(ani.getImage().image, null),pos.x,pos.y);
    }
}
