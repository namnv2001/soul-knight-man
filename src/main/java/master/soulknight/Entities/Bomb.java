package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.States.GameStateManager;
import master.soulknight.Tiles.TileCollision;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.PlaySound;
import master.soulknight.Util.Vector2f;

public class Bomb extends Entity {

    private final double realSize = Entity.getSCALING() * SpriteSheet.getTileSize();
    public boolean firstTime;
    public boolean removed;
    public int bombRange;
    public int bombCounter;
    public int bombTime;
    protected int leftBeforeCollide = -1;
    protected int rightBeforeCollide = -1;
    protected int upBeforeCollide = -1;
    protected int downBeforeCollide = -1;

    private MediaPlayer mp;
    PlaySound ps;

    public Bomb(SpriteSheet sprite, Vector2f origin, int size, double SCALING, int bombRange, TileManager tm) {
        super(sprite, origin, size, SCALING, tm);
        this.bombRange = bombRange;
        bombCounter = 0;
        removed = false;
        firstTime = true;
        this.bombTime = 100;
    }

    public Bomb(SpriteSheet sprite, Vector2f origin, int size, double SCALING, int bombRange) {
        super(sprite, origin, size, SCALING);
        this.bombRange = bombRange;
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
            //explode sfx
            ps = new PlaySound("src/main/resources/Music/fx_explode_big.wav");
            ps.play(1,1);
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
        for (int i = 1; i <= bombRange; i++) {
            String flamePath = "src/main/resources/Sprite/Ui/Interactive/Flame.png";
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
        bombCounter = bombTime;
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
