package master.soulknight.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class SpriteSheet {
    private Sprite SPRITESHEET;
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

//    private BufferedImage loadSprite(String file) {
//        BufferedImage sprite = new BufferedImage();
//        try {System.out.println(file);
//            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
//            System.out.println(sprite);
//        } catch (Exception e) {
//            System.out.println("ERROR: could not load file: " + file);
//        }
//        return sprite;
//    }

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
