package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;


public class Flame extends Bomb {

    private final double realSize = Entity.getSCALING() * SpriteSheet.getTileSize();
    public boolean removed;
    protected int leftBeforeCollide = -1;
    protected int rightBeforeCollide = -1;
    protected int upBeforeCollide = -1;
    protected int downBeforeCollide = -1;
    private int flameCounter;

    public Flame(SpriteSheet sprite, Vector2f origin, int size, double SCALING, int bombRange) {
        super(sprite, origin, size, SCALING, bombRange);
        flameCounter = 0;
        removed = false;
    }

    public void update() {
        if (flameCounter > 30) {
            removed = true;
        }
    }

    public void flameCollide() {

        for (int i = 1; i <= bombRange; i++) {
            if (leftBeforeCollide == -1) {
                if (TileManager.colliedTile((int) (pos.x - i * realSize), (int) (pos.y))) leftBeforeCollide = i - 1;
            }
            if (rightBeforeCollide == -1) {
                if (TileManager.colliedTile((int) (pos.x + i * realSize), (int) (pos.y))) rightBeforeCollide = i - 1;
            }
            if (downBeforeCollide == -1) {
                if (TileManager.colliedTile((int) (pos.x), (int) (pos.y - i * realSize))) downBeforeCollide = i - 1;
            }
            if (upBeforeCollide == -1) {
                if (TileManager.colliedTile((int) (pos.x), (int) (pos.y + i * realSize))) upBeforeCollide = i - 1;
            }
            if (i == bombRange) {
                if (leftBeforeCollide == -1) leftBeforeCollide = bombRange;
                if (rightBeforeCollide == -1) rightBeforeCollide = bombRange;
                if (upBeforeCollide == -1) upBeforeCollide = bombRange;
                if (downBeforeCollide == -1) downBeforeCollide = bombRange;
            }
        }
    }

    public void render(GraphicsContext gc) {
        flameCollide();
        flameCounter++;
        for (int i = 1; i <= bombRange; i++) {
            gc.drawImage(ani.getImage().getFxImage(), (int) (pos.x), (int) (pos.y), realSize, realSize);

            if (i <= rightBeforeCollide) {
                gc.drawImage(ani.getImage().getFxImage(), (int) (pos.x) + i * realSize, (int) (pos.y), realSize, realSize);
            }

            if (i <= leftBeforeCollide) {
                gc.drawImage(ani.getImage().getFxImage(), (int) (pos.x) - i * realSize, (int) (pos.y), realSize, realSize);
            }

            if (i <= upBeforeCollide) {
                gc.drawImage(ani.getImage().getFxImage(), (int) (pos.x), (int) (pos.y) + i * realSize, realSize, realSize);
            }

            if (i <= downBeforeCollide) {
                gc.drawImage(ani.getImage().getFxImage(), (int) (pos.x), (int) (pos.y) - i * realSize, realSize, realSize);
            }
        }
    }
}
