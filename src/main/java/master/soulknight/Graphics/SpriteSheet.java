package master.soulknight.Graphics;

import javafx.scene.image.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class SpriteSheet {
    private Sprite SPRITESHEET;
    private Sprite[][] spriteArray;
    private final int TILE_SIZE = 320;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;
    private String file;

    public SpriteSheet(String file) {

        this.file = file;
        w = TILE_SIZE;
        h = TILE_SIZE + 40;

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

//    public Image getFxImage() {
//        WritableImage wr = new WritableImage(TILE_SIZE, TILE_SIZE);
//        PixelWriter pw = wr.getPixelWriter();
//        for (int x = 0; x <TILE_SIZE; x++) {
//            for (int y = 0; y < TILE_SIZE; y++) {
//                if ( _pixels[x + y * TILE_SIZE] == TRANSPARENT_COLOR) {
//                    pw.setArgb(x, y, 0);
//                }
//                else {
//                    pw.setArgb(x, y, _pixels[x + y * TILE_SIZE]);
//                }
//            }
//        }
//        Image input = new ImageView(wr).getImage();
//        return resample(input, 2);
//    }
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

}
