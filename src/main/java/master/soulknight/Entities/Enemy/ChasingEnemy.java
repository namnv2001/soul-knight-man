package master.soulknight.Entities.Enemy;

import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.Map.ConvertMap;
import master.soulknight.Tiles.TileCollision;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public class ChasingEnemy extends Enemy {

    int x, y;

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
        x = (int) Math.round(pos.x / (SpriteSheet.getTileSize() * getSCALING()));
        y = (int) Math.round(pos.y / (SpriteSheet.getTileSize() * getSCALING()));
        System.out.println(y + " " + x);
        System.out.println(pos.y + " " + pos.x);
        for (int i = 0; i < ConvertMap.path.size(); i++) {
            System.out.print(ConvertMap.path.get(i).toString());
        }
        super.update();

        System.out.println(this.dx + " " + this.dy);
        pos.x += this.dx;
        pos.y += this.dy;
        if (TileCollision.enemyIsCollidedWithBlock(this, tm.collideBlocks) || TileCollision.isCollidedWithBombs(this, tm.getBombs())) {
            pos.x -= dx;
            pos.y -= dy;
        }
        if (!ConvertMap.path.isEmpty()) {
            for (int i = 0; i < ConvertMap.path.size() - 1; i++) {
                if (pos.x == ConvertMap.path.get(i).getY() * 62 && pos.y == ConvertMap.path.get(i).getX() * 62) {
                    if (x - 1 == ConvertMap.path.get(i + 1).getY() && y == ConvertMap.path.get(i + 1).getX()) {
                        setFalse();
                        left = true;
                        this.dx = -2;
                        this.dy = 0;
                    } else if (x + 1 == ConvertMap.path.get(i + 1).getY() && y == ConvertMap.path.get(i + 1).getX()) {
                        setFalse();
                        right = true;
                        this.dx = 2;
                        this.dy = 0;
                    } else if (x == ConvertMap.path.get(i + 1).getY() && y - 1 == ConvertMap.path.get(i + 1).getX()) {
                        setFalse();
                        up = true;
                        this.dx = 0;
                        this.dy = -2;
                    } else if (x == ConvertMap.path.get(i + 1).getY() && y + 1 == ConvertMap.path.get(i + 1).getX()) {
                        setFalse();
                        down = true;
                        this.dx = 0;
                        this.dy = 2;
                    }
                }
            }
        }
    }
}
