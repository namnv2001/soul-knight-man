package master.soulknight.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class SpriteSheet {
    private static final int TILE_SIZE = 31;
    public int w;
    public int h;
    private Sprite SPRITESHEET;
    private Sprite[][] spriteArray;
    private int wSprite;
    private int hSprite;

    public Sprite getSPRITESHEET() {
        return SPRITESHEET;
    }

    public SpriteSheet(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading " + file + "...");
        try {
            BufferedImage sprite = ImageIO.read(new FileInputStream(file));
            SPRITESHEET = new Sprite(sprite);
            wSprite = SPRITESHEET.image.getWidth() / w;
            hSprite = SPRITESHEET.image.getHeight() / h;
            loadSpriteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public void loadSpriteArray() {
        spriteArray = new Sprite[hSprite][wSprite];
        for (int y = 0; y < hSprite; y++) {
            for (int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }

    public Sprite getSpriteArray(int x, int y) {
        return spriteArray[y][x];
    }

    public Sprite[] getSpriteArray(int num) {
        return spriteArray[num];
    }

    public Sprite getSprite(int x, int y) {
        return SPRITESHEET.getSubImage(x * w, y * h, w, h);
    }


}
