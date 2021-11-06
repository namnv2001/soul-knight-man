package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.Vector2f;


public class Player extends Entity {
    Image image ;

    public Player(SpriteSheet sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
    }

    public void input(KeyEvent key) {
        if(!fallen) {
            if(key.getCode() == KeyCode.W) {
                System.out.println("4");
            }
        }
    }

    public void update() {
        super.update();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(ani.getImage().image,pos.x,pos.y);
    }
}
