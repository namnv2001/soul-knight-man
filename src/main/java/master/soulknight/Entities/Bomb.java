package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.Vector2f;

public class Bomb extends Entity{
    private int bombCounter;
    public boolean removed;

    public Bomb(SpriteSheet sprite, Vector2f origin, int size, double SCALING, int bombRange) {
        super(sprite, origin, size, SCALING);
        bombCounter = 0;
        removed = false;
    }

    public void update() {
        if(bombCounter > 100) {
            remove();
        }
    }

    public void remove() {
        removed = true;
    }

    public void render(GraphicsContext gc) {
        bombCounter++;
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y,SpriteSheet.getTileSize()*getSCALING(),SpriteSheet.getTileSize()*getSCALING());
    }

}
