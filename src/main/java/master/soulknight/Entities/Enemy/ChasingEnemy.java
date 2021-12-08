package master.soulknight.Entities.Enemy;

import master.soulknight.Entities.Entity;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.Map.ConvertMap;
import master.soulknight.Tiles.TileCollision;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

import java.util.List;

public class ChasingEnemy extends Enemy {

    List<ConvertMap.Node> path ;
    ConvertMap convertMap;

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
        convertMap = new ConvertMap();
        path = convertMap.convert2D(tm, this, (int) (SpriteSheet.getTileSize() * Entity.getSCALING()));
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
                if (pos.x == path.get(i).getY() * 62 && pos.y == path.get(i).getX() * 62) {
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
