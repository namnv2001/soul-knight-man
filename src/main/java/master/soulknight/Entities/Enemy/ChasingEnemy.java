package master.soulknight.Entities.Enemy;

import master.soulknight.Entities.Entity;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.Map.ConvertMap;
import master.soulknight.Tiles.TileCollision;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

import java.util.List;

public class ChasingEnemy extends Enemy {

    public ChasingEnemy(SpriteSheet sprite, Vector2f origin, int size, double SCALING, TileManager tm) {
        super(sprite, origin, size, SCALING, tm);
        up = false;
    }

    public void setFalse() {
        up = false;
        right = false;
        left = false;
        down = false;
    }

    @Override
    public void update() {
        ConvertMap convertMap = new ConvertMap();
        List<ConvertMap.Node> path = convertMap.convert2D(tm, this, (int) (SpriteSheet.getTileSize() * Entity.getSCALING()));
        int x = (int) Math.round(pos.x / (SpriteSheet.getTileSize() * getSCALING()));
        int y = (int) Math.round(pos.y / (SpriteSheet.getTileSize() * getSCALING()));
        super.update();
        pos.x += this.dx;
        pos.y += this.dy;
        if (TileCollision.enemyIsCollidedWithBlock(this, tm.collideBlocks) || TileCollision.isCollidedWithBombs(this, tm.getBombs())) {
            pos.x -= dx;
            pos.y -= dy;
            changeDirection();
        }
        if (!path.isEmpty()) {
            for (int i = 0; i < path.size() - 1; i++) {
                if (pos.x == path.get(i).getY() * SpriteSheet.getTileSize() * getSCALING() && pos.y == path.get(i).getX() * SpriteSheet.getTileSize() * getSCALING()) {
                    if (x - 1 == path.get(i + 1).getY() && y == path.get(i + 1).getX()) {
                        setFalse();
                        left = true;
                        this.dx = -2;
                        this.dy = 0;
                    } else if (x + 1 == path.get(i + 1).getY() && y == path.get(i + 1).getX()) {
                        setFalse();
                        right = true;
                        this.dx = 2;
                        this.dy = 0;
                    } else if (x == path.get(i + 1).getY() && y - 1 == path.get(i + 1).getX()) {
                        setFalse();
                        up = true;
                        this.dx = 0;
                        this.dy = -2;
                    } else if (x == path.get(i + 1).getY() && y + 1 == path.get(i + 1).getX()) {
                        setFalse();
                        down = true;
                        this.dx = 0;
                        this.dy = 2;
                    }
                }
            }
        }
        if (TileCollision.isCollidedWithFlames(this, tm.getFlames())) {
            this.dead = true;
        }
    }

    public void changeDirection() {
        if (up) {
            up = false;
            down = true;
            this.dx = 0;
            this.dy = 2;
        } else if (down) {
            down = false;
            up = true;
            this.dx = 0;
            this.dy = -2;
        } else if (right) {
            right = false;
            left = true;
            this.dx = 2;
            this.dy = 0;
        } else {
            left = false;
            right = true;
            this.dx = -2;
            this.dy = 0;
        }
    }
}
