package master.soulknight.Entities.Enemy;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Entities.Entity;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.Map.ConvertMap;
import master.soulknight.Tiles.TileCollision;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

import java.util.List;

public class TwoLifeEnemy extends Enemy {
    int delay;
    boolean firstLife = true;
    List<ConvertMap.Node> path;
    ConvertMap convertMap;

    public TwoLifeEnemy(SpriteSheet sprite, Vector2f origin, int size, double SCALING, TileManager tm) {
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

    public void setFalse() {
        up = false;
        right = false;
        left = false;
        down = false;
    }

    @Override
    public void update() {
        if (TileCollision.isCollidedWithFlames(this, tm.getFlames()) && firstLife) {
            TileManager.score += 100;
            delay = 0;
            setSprite(new SpriteSheet("src/main/resources/Sprite/Enemies/CrystalBeetle.png"));
            firstLife = false;
            int roundedX = (int) Math.round(pos.x / (SpriteSheet.getTileSize() * getSCALING()));
            int roundedY = (int) Math.round(pos.y / (SpriteSheet.getTileSize() * getSCALING()));
            pos.x = (float) (roundedX * SpriteSheet.getTileSize() * getSCALING());
            pos.y = (float) (roundedY * SpriteSheet.getTileSize() * getSCALING());
            setFalse();
        } else if (TileCollision.isCollidedWithFlames(this, tm.getFlames()) && !firstLife && delay > 50) {
            dead = true;
        }

        if (firstLife) {
            super.update();
            move();
            pos.x += dx;
            pos.y += dy;
            if (TileCollision.isCollidedWithBlock(this, tm.collideBlocks) || TileCollision.isCollidedWithBombs(this, tm.getBombs())) {
                changeDirection();
                pos.x -= dx;
                pos.y -= dy;
            }
        }
        if (!firstLife) {
            delay++;
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
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y,
                SpriteSheet.getTileSize() * Entity.getSCALING(),
                SpriteSheet.getTileSize() * Entity.getSCALING());
    }

}
