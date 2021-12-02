package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileCollision;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public class Bomb extends Entity {

    public static boolean firstTime;
    public static int bombRange;
    private final double realSize = Entity.getSCALING() * SpriteSheet.getTileSize();
    public boolean removed;
    protected int leftBeforeCollide = -1;
    protected int rightBeforeCollide = -1;
    protected int upBeforeCollide = -1;
    protected int downBeforeCollide = -1;
    private int bombCounter;

    public Bomb(SpriteSheet sprite, Vector2f origin, int size, double SCALING, int bombRange, TileManager tm) {
        super(sprite, origin, size, SCALING, tm);
        Bomb.bombRange = bombRange;
        bombCounter = 0;
        removed = false;
        firstTime = true;
    }

    public Bomb(SpriteSheet sprite, Vector2f origin, int size, double SCALING, int bombRange) {
        super(sprite, origin, size, SCALING);
        Bomb.bombRange = bombRange;
        bombCounter = 0;
        removed = false;
        firstTime = true;
    }

    public void update() {
        ani.setDelay(10);
        ani.update();
        if (bombCounter >= 100) {
            generateFlames();
            remove();
        }
        if (!TileCollision.isCollidedWithBombs(tm.getPlayer(), tm.getBombs())) {
            leave();
        }

    }

    public void generateFlames() {
        for (int i = 1; i <= bombRange; i++) {
            if (leftBeforeCollide == -1) {
                if (tm.colliedTile((int) (pos.x - i * realSize), (int) (pos.y))
                        || tm.colliedBomb((int) (pos.x - i * realSize), (int) (pos.y)))
                    leftBeforeCollide = i - 1;
            }
            if (rightBeforeCollide == -1) {
                if (tm.colliedTile((int) (pos.x + i * realSize), (int) (pos.y))
                        || tm.colliedBomb((int) (pos.x + i * realSize), (int) (pos.y)))
                    rightBeforeCollide = i - 1;
            }
            if (downBeforeCollide == -1) {
                if (tm.colliedTile((int) (pos.x), (int) (pos.y + i * realSize))
                        || tm.colliedBomb((int) (pos.x), (int) (pos.y + i * realSize)))
                    downBeforeCollide = i - 1;
            }
            if (upBeforeCollide == -1) {
                if (tm.colliedTile((int) (pos.x), (int) (pos.y - i * realSize))
                        || tm.colliedBomb((int) (pos.x), (int) (pos.y - i * realSize)))
                    upBeforeCollide = i - 1;
            }
            if (i == bombRange) {
                if (leftBeforeCollide == -1) leftBeforeCollide = bombRange;
                if (rightBeforeCollide == -1) rightBeforeCollide = bombRange;
                if (upBeforeCollide == -1) upBeforeCollide = bombRange;
                if (downBeforeCollide == -1) downBeforeCollide = bombRange;
            }
        }
        System.out.println(leftBeforeCollide);
        System.out.println(rightBeforeCollide);
        System.out.println(upBeforeCollide);
        System.out.println(downBeforeCollide);
        for (int i = 1; i <= bombRange; i++) {
            String flamePath = "src/main/resources/Sprite/Flame - Copy.png";
            tm.addFlame(new Flame(new SpriteSheet(flamePath), new Vector2f(pos.x, pos.y), size, Entity.getSCALING(), bombRange));
            if (i <= rightBeforeCollide) {
                tm.addFlame(new Flame(new SpriteSheet(flamePath), new Vector2f((float) (pos.x + i * realSize), pos.y), size, Entity.getSCALING(), bombRange));
            }

            if (i <= leftBeforeCollide) {
                tm.addFlame(new Flame(new SpriteSheet(flamePath), new Vector2f((float) (pos.x - i * realSize), pos.y), size, Entity.getSCALING(), bombRange));
            }

            if (i <= upBeforeCollide) {
                tm.addFlame(new Flame(new SpriteSheet(flamePath), new Vector2f((pos.x), (float) (pos.y - i * realSize)), size, Entity.getSCALING(), bombRange));
            }

            if (i <= downBeforeCollide) {
                tm.addFlame(new Flame(new SpriteSheet(flamePath), new Vector2f((pos.x), (float) (pos.y + i * realSize)), size, Entity.getSCALING(), bombRange));
            }
        }
    }

    public void endCounter() {
        bombCounter = 100;
    }

    public void remove() {
        removed = true;
    }

    public void leave() {
        firstTime = false;
    }

    public void render(GraphicsContext gc) {
        bombCounter++;
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y, SpriteSheet.getTileSize() * getSCALING(), SpriteSheet.getTileSize() * getSCALING());
    }

}
