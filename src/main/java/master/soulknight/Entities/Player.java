package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.States.PlayState;
import master.soulknight.Tiles.TileCollision;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.Vector2f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Player extends Entity {

    public Bomb bomb;
    public int bombsInHand;
    public int bombRange;

    public Player(SpriteSheet sprite, Vector2f origin, int size, double SCALING, TileManager tm) {
        super(sprite, origin, size, SCALING, tm);
        bombsInHand = 1;
        bombRange = 1;
    }

    public static Vector2f getBombPos(Vector2f pos) {
        int playerX = (int) Math.round(pos.x / (SpriteSheet.getTileSize() * getSCALING()));
        int playerY = (int) Math.round(pos.y / (SpriteSheet.getTileSize() * getSCALING()));
        return new Vector2f((int) (playerX * SpriteSheet.getTileSize() * getSCALING()), (int) (playerY * SpriteSheet.getTileSize() * getSCALING()));
    }

    public void addBombsInHand() {
        System.out.println("Ex Bomb");
        if (bombsInHand < 4) {
            this.bombsInHand++;
        }
    }

    public void addBombRange() {
        System.out.println("Power Up");
        if (bombRange < 4) {
            this.bombRange++;
        }
    }

    public void addSpeed() {
        System.out.println("Speed");
        if (this.speed < 5f) {
            this.speed += 1f;
        }
    }

    public void input(KeyHandler keyHandler) {
        Set<KeyCode> activeKeys = keyHandler.getActiveKeys();
        up = activeKeys.contains(KeyCode.W);
        down = activeKeys.contains(KeyCode.S);
        left = activeKeys.contains(KeyCode.A);
        right = activeKeys.contains(KeyCode.D);
        if (activeKeys.contains(KeyCode.SPACE)) {
            if (bombsInHand > 0 && !tm.bombExist(getBombPos(pos))) {
                bombsInHand--;
                bomb = new Bomb(new SpriteSheet("src/main/resources/Sprite/Ui/Interactive/Bombs.png"), getBombPos(pos), size, Entity.getSCALING(), bombRange, tm);
                tm.addBomb(bomb);
            }
        }
        if (activeKeys.contains(KeyCode.Q)) {
            tm.getEnemies().clear();
        }
    }

    public boolean checkFirstTime(ArrayList<Bomb> bombs) {
        for (Bomb bomb : bombs) {
            if (bomb.firstTime) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        if (!PlayState.gameOver) {
            super.update();
            move();
            ani.update();
            pos.x += dx;
            pos.y += dy;
            if (TileCollision.isCollidedWithBlock(this, tm.collideBlocks) || (TileCollision.isCollidedWithBombs(this, tm.getBombs()) && !checkFirstTime(tm.getBombs()))) {
                pos.x -= dx;
                pos.y -= dy;
            }
        }
        bombClear();
        clearFlame();
    }

    public void bombClear() {
        Iterator<Bomb> iterator = tm.getBombs().iterator();
        while (iterator.hasNext()) {
            Bomb bomb = iterator.next();
            if (bomb.removed) {
                iterator.remove();
                bombsInHand++;
            }
        }
    }

    public void clearFlame() {
        tm.getFlames().removeIf(flame -> flame.removed);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y,
                SpriteSheet.getTileSize() * Entity.getSCALING(),
                SpriteSheet.getTileSize() * Entity.getSCALING());
//        gc.setFill(Color.BLUE);
//        gc.fillRect(pos.x + 7, pos.y + 7, getSize(), getSize());
    }
}