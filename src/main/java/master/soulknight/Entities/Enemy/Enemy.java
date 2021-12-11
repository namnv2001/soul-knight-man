package master.soulknight.Entities.Enemy;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Entities.Entity;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public abstract class Enemy extends Entity {
    public boolean dead;

    public Enemy(SpriteSheet sprite, Vector2f origin, int size, double SCALING, TileManager tm) {
        super(sprite, origin, size, SCALING, tm);
        up = true;
        dead = false;
    }

    @Override
    public void update() {
        animated();
        super.update();
        ani.update();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y,
                SpriteSheet.getTileSize() * Entity.getSCALING(),
                SpriteSheet.getTileSize() * Entity.getSCALING());
    }
}
