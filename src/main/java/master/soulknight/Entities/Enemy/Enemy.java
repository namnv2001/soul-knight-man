package master.soulknight.Entities.Enemy;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Entities.Entity;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public abstract class Enemy extends Entity {

    public Enemy(SpriteSheet sprite, Vector2f origin, int size, double SCALING, TileManager tm) {
        super(sprite, origin, size, SCALING, tm);
        up = true;
    }

    @Override
    public void update() {
        animated();
//        if (ani.getDelay() != 10) {
//            this.ani.setDelay(10);
//        }
        super.update();
        ani.update();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y,
                SpriteSheet.getTileSize() * Entity.getSCALING(),
                SpriteSheet.getTileSize() * Entity.getSCALING());
    }
}
