package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.Vector2f;


public class Player extends Entity {
    Image image;

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
            } else {
                placeBoom = false;
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

    public void update() {
        super.update();
        if (!fallen) {
            move();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
//        gc.drawImage(ani.getImage().image,pos.x,pos.y);
    }
}
