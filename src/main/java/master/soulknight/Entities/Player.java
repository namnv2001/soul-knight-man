package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

import java.util.Iterator;


public class Player extends Entity {

    private Bomb bomb;
    private int bombsInHand;
    private int bombRange;
    private float x,y;

    public static Sprite boom = new Sprite("src/main/resources/Sprite/MyVer.zip - Copy.png");
    private KeyCode direction;

    public Player(SpriteSheet sprite, Vector2f origin, int size, double SCALING) {
        super(sprite, origin, size, SCALING);
        bombsInHand = 1;
        bombRange = 1;
    }

    public void handleKeyPressedEvent(KeyCode keycode) {
        if (!fallen) {
            if (keycode == KeyCode.W) {
                direction = keycode;
                up = true;
            } else {
                up = false;
            }
            if (keycode == KeyCode.S) {
                direction = keycode;
                down = true;
            } else {
                down = false;
            }
            if (keycode == KeyCode.A) {
                direction = keycode;
                left = true;
            } else {
                left = false;
            }
            if (keycode == KeyCode.D) {
                direction = keycode;
                right = true;
            } else {
                right = false;
            }
            if (keycode == KeyCode.SPACE) {

                if(bombsInHand > 0) {
                    bombsInHand--;
                    bomb = new Bomb(new SpriteSheet("src/main/resources/Sprite/MyVer.zip - Copy.png"),getBombPos(pos),size,Entity.getSCALING(),bombRange);
                    TileManager.addBomb(bomb);
                }
            }
        }
    }

    public void handleKeyReleasedEvent(KeyCode keycode) {
        if (fallen) return;
        if (direction == keycode) {
            if (direction == KeyCode.A) {
                left = false;
            }
            if (direction == KeyCode.D) {
                right = false;
            }
            if (direction == KeyCode.W) {
                up = false;
            }
            if (direction == KeyCode.S) {
                down = false;
            }
//            direction = null;
        }

    }

    public void update() {
        if (!fallen) {
            super.update();
        }
        bombClear();
    }

    public void bombClear() {
        Iterator<Bomb> iterator = TileManager.getBombs().iterator();
        while ( iterator.hasNext()) {
            Bomb bomb = iterator.next();
            if(bomb.removed) {
                iterator.remove();
                bombsInHand++;
            }
        }
//        for(Bomb bomb : TileManager.getBombs()) {
//            if(bomb.removed && !(TileManager.getBombs().isEmpty())) {
//                TileManager.getBombs().remove(bomb);
//                bombsInHand++;
//            }
//        }
    }

    private static Vector2f getBombPos(Vector2f pos) {
        int playerX = (int) Math.round(pos.x / (SpriteSheet.getTileSize() * getSCALING()));
        int playerY = (int) Math.round(pos.y / (SpriteSheet.getTileSize() * getSCALING()));
        System.out.println(playerX + " " + playerY);
        return new Vector2f((int)(playerX * SpriteSheet.getTileSize() * getSCALING()), (int)(playerY * SpriteSheet.getTileSize() * getSCALING()));
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y,
                SpriteSheet.getTileSize() * Entity.getSCALING(),
                SpriteSheet.getTileSize() * Entity.getSCALING());
        gc.setFill(Color.BLUE);
        gc.fillRect(pos.x + 7, pos.y + 7, getSize(), getSize());

    }
}