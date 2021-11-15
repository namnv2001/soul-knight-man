package master.soulknight.Graphics;

import javafx.scene.image.*;
import master.soulknight.Tiles.TileManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import static master.soulknight.Tiles.TileManager.tileSheet;

public class SpriteSheet {
    private static final int TILE_SIZE = 31;

    public int w;
    public int h;
    private Sprite SPRITESHEET;
    private Sprite[][] spriteArray;
    private int _x,_y;
    public int[] pixels;
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

    public static SpriteSheet grass = new SpriteSheet(2,0, tileSheet);
    public static SpriteSheet wall = new SpriteSheet(1,0,tileSheet);
    public static SpriteSheet box = new SpriteSheet(0,0,tileSheet);

    public SpriteSheet( int x, int y, Sprite sheet) {

        pixels = new int[TILE_SIZE * TILE_SIZE];
        _x = x * TILE_SIZE;
        _y = y * TILE_SIZE;
        SPRITESHEET = sheet;
        load();
    }

    private void load() {
        for (int y = 0; y < TILE_SIZE; y++) {
            for (int x = 0; x < TILE_SIZE; x++) {
                pixels[x + y * TILE_SIZE] =  SPRITESHEET.pixels[(x + _x) + (y + _y) *  93];
            }
        }
    }


    public static int getTileSize() {
        return TILE_SIZE;
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
        for (int y = 0; y < hSprite; y++) {
            for (int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
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


//
//    private Image resample(Image input, int scaleFactor) {
//        final int W = (int) input.getWidth();
//        final int H = (int) input.getHeight();
//        final int S = scaleFactor;
//
//        WritableImage output = new WritableImage(
//                W * S,
//                H * S
//        );
//
//        PixelReader reader = input.getPixelReader();
//        PixelWriter writer = output.getPixelWriter();
//
//        for (int y = 0; y < H; y++) {
//            for (int x = 0; x < W; x++) {
//                final int argb = reader.getArgb(x, y);
//                for (int dy = 0; dy < S; dy++) {
//                    for (int dx = 0; dx < S; dx++) {
//                        writer.setArgb(x * S + dx, y * S + dy, argb);
//                    }
//                }
//            }
//        }
//
//        return output;

//    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(SpriteSheet.getTileSize(), SpriteSheet.getTileSize());
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x <SpriteSheet.getTileSize(); x++) {
            for (int y = 0; y < SpriteSheet.getTileSize(); y++) {
                if ( pixels[x + y * SpriteSheet.getTileSize()] == 0xffff00ff) {
                    pw.setArgb(x, y, 0);
                }
                else {
                    pw.setArgb(x, y, pixels[x + y * SpriteSheet.getTileSize()]);
                }
            }
        }
        return new ImageView(wr).getImage();

    }

//    private Image resample(Image input, int scaleFactor) {
//        final int W = (int) input.getWidth();
//        final int H = (int) input.getHeight();
//        final int S = scaleFactor;
//
//        WritableImage output = new WritableImage(
//                W * S,
//                H * S
//        );
//
//        PixelReader reader = input.getPixelReader();
//        PixelWriter writer = output.getPixelWriter();
//
//        for (int y = 0; y < H; y++) {
//            for (int x = 0; x < W; x++) {
//                final int argb = reader.getArgb(x, y);
//                for (int dy = 0; dy < S; dy++) {
//                    for (int dx = 0; dx < S; dx++) {
//                        writer.setArgb(x * S + dx, y * S + dy, argb);
//                    }
//                }
//            }
//        }
//
//        return output;
//    }
}
