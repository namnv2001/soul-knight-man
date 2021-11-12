package master.soulknight.Entities;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.Vector2f;


public class Player extends Entity {


    private KeyCode direction;

    public Player(SpriteSheet sprite, Vector2f origin, int size, double SCALING) {
        super(sprite, origin, size, SCALING);
    }
//
//    public void input(KeyEvent key) {
//        if (!fallen) {
//            if (key.getCode() == KeyCode.W) {
//                up = true;
//            } else {
//                up = false;
//            }
//            if (key.getCode() == KeyCode.S) {
//                down = true;
//            } else {
//                down = false;
//            }
//            if (key.getCode() == KeyCode.A) {
//                left = true;
//            } else {
//                left = false;
//            }
//            if (key.getCode() == KeyCode.D) {
//                right = true;
//            } else {
//                right = false;
//            }
//            if (key.getCode() == KeyCode.SPACE) {
//                placeBoom = true;
//            }
//        } else {
//            up = false;
//            down = false;
//            left = false;
//            right = false;
//            placeBoom = false;
//        }
//    }

    public void handleKeyPressedEvent(KeyCode keycode) {
        if (fallen) return;
        if (keycode == KeyCode.A) {
            direction  = keycode;
            left = true;
        } else if (keycode == KeyCode.D) {
            direction  = keycode;
            right = true;
        } else if (keycode == KeyCode.W) {
            direction  = keycode;
            up = true;
        } else if (keycode == KeyCode.S) {
            direction  = keycode;
            down = true;
        }
        if (keycode ==  KeyCode.SPACE) {
            placeBoom = true;
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
            if (left || right) {
                pos.x += dx;
            } else if (up || down) {
                pos.y += dy;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(SwingFXUtils.toFXImage(ani.getImage().image, null), pos.x, pos.y
                , SpriteSheet.getTileSize() * Entity.getSCALING(), SpriteSheet.getTileSize() * Entity.getSCALING());
    }
}
