package master.soulknight.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SpriteSheet {
    private Sprite SPRITESHEET = null;
    private Sprite[][] spriteArray;
    private final int TILE_SIZE = 34;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;
    private String file;

    public SpriteSheet(String file) {
        this.file = file;
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading " + file + "...");
        SPRITESHEET = new Sprite(loadSprite(file));

        wSprite = SPRITESHEET.image.getWidth() / w;
    }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }

    public void loadSpriteArray() {
        spriteArray = new Sprite[hSprite][wSprite];

        for(int y = 0; y < hSprite; y++) {
            for(int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x,y);
            }
        }
    }

    public Sprite[] getSpriteArray(int num) {
        return spriteArray[num];
    }

    public Sprite getSprite(int x, int y) {
        return SPRITESHEET.getSubImage(x * w, y * h, w, h);
    }

    public BufferedImage getSubImage(int x, int y, int w, int h) {
        return SPRITESHEET.image.getSubimage(x, y, w, h);
    }
}
