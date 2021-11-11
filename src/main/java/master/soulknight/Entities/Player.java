package master.soulknight.Entities;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.Vector2f;


public class Player extends Entity {

    public Player(SpriteSheet sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
    }

    public void input(KeyHandler key) {
        if (!fallen) {
            if (key.up.down) {
                up = true;
            } else {
                up = false;
            }
            if (key.down.down) {
                down = true;
            } else {
                down = false;
            }
            if (key.left.down) {
                left = true;
            } else {
                left = false;
            }
            if (key.right.down) {
                right = true;
            } else {
                right = false;
            }
            if (key.attack.clicked) {
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
