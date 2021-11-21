package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.Vector2f;


public class Player extends Entity {

    private float x,y;

    public static Sprite boom = new Sprite("src/main/resources/Sprite/MyVer.zip - Copy.png");
    private KeyCode direction;

    public Player(SpriteSheet sprite, Vector2f origin, int size, double SCALING) {
        super(sprite, origin, size, SCALING);
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
                x = pos.x;
                y = pos.y;
                placeBoom = true;
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
            direction = null;
        }
        if (keycode == KeyCode.SPACE) {
            placeBoom = false;
        }
    }

//    public void update() {
//        double realWidth = TileManager.mapColumns * 62;
//        double realHeight = TileManager.mapRows * 62;
//        double widthRatio = realWidth / TileManager.mapColumns;
//        double heightRatio = realHeight / TileManager.mapRows;
//        boolean isMovable = true;
//        if (!fallen) {
//            for (int i = 0; i < TileManager.mapRows; i++) {
//                for (int j = 0; j < TileManager.mapColumns; j++) {
//                    boolean xAllowed = Math.round(pos.x / widthRatio) != j;
//                    boolean yAllowed = Math.round(pos.y / heightRatio) != i;
//                    boolean mapAllowed = TileManager.collideMap[i][j] == 0;
//                    if (xAllowed && yAllowed && mapAllowed) {
//                        isMovable = true;
//                    } else if (!xAllowed && !yAllowed && !mapAllowed) {
//                        isMovable = false;
//                        System.out.println("collided");
//                    }
//                }
//            }
//            if (isMovable) {
//                super.update();
//            } else if (up || down) {
//                super.collidedUpdateUpDown();
//            }
//        }
//    }

    public void update() {
        if (!fallen) {
            super.update();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y,
                SpriteSheet.getTileSize() * Entity.getSCALING(),
                SpriteSheet.getTileSize() * Entity.getSCALING());
        gc.setFill(Color.BLUE);
        gc.fillRect(pos.x + 7, pos.y + 7, getSize(), getSize());
        if(placeBoom) {
            gc.drawImage(boom.getFxImage(), x, y);
        }
    }
}