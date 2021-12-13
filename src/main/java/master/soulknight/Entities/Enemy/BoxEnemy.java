package master.soulknight.Entities.Enemy;

import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public class BoxEnemy extends ChasingEnemy {
    public BoxEnemy(SpriteSheet sprite, Vector2f origin, int size, double SCALING, TileManager tm) {
        super(sprite, origin, size, SCALING, tm);
    }
}
