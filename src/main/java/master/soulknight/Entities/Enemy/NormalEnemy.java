package master.soulknight.Entities.Enemy;

import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileCollision;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public class NormalEnemy extends Enemy {

    public NormalEnemy(SpriteSheet sprite, Vector2f origin, int size, double SCALING, TileManager tm) {
        super(sprite, origin, size, SCALING, tm);
    }

    public void changeDirection() {
        if (up) {
            up = false;
            down = true;
        } else if (down) {
            down = false;
            up = true;
        } else if (right) {
            right = false;
            left = true;
        } else {
            left = false;
            right = true;
        }
    }

    @Override
    public void update() {
        super.update();
        move();
        pos.x += dx;
        pos.y += dy;
        if (TileCollision.isCollidedWithBlock(this, tm.collideBlocks) || TileCollision.isCollidedWithBombs(this, tm.getBombs())) {
            changeDirection();
            pos.x -= dx;
            pos.y -= dy;
        }
        if (TileCollision.isCollidedWithFlames(this, tm.getFlames())) {
            this.dead = true;
        }
    }


}
