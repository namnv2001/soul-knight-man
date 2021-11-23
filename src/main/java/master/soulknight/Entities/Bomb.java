package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.States.PlayState;
import master.soulknight.Tiles.TileCollision;
import master.soulknight.Util.Vector2f;

public class Bomb extends Entity{
    private int bombCounter;
    public boolean removed;
    public static boolean firstTime;

    public Bomb(SpriteSheet sprite, Vector2f origin, int size, double SCALING, int bombRange) {
        super(sprite, origin, size, SCALING);
        bombCounter = 0;
        removed = false;
        firstTime = true;
    }

    public void update() {
        if(bombCounter > 100) {
            remove();
        }
        if(!TileCollision.isCollidedWithBombs(PlayState.player)) {
            leave();
        }
    }

    public void remove() {
        removed = true;
    }

    public void leave() { firstTime = false; }

    public void render(GraphicsContext gc) {
        bombCounter++;
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y,SpriteSheet.getTileSize()*getSCALING(),SpriteSheet.getTileSize()*getSCALING());
    }

}
