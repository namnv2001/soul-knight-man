package master.soulknight.Graphics;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class Sprite {

    private String path;
    public BufferedImage image;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;

    public int[] pixels;
    private int[] ogpixels;

    private int w;
    private int h;

    public static Sprite tiles = new Sprite("src/main/resources/Sprite/ss - Copy.png");

    public Sprite(BufferedImage image) {
        this.image = image;
        this.w = image.getWidth();
        this.h = image.getHeight();

        ogpixels = image.getRGB(0,0,w,h,ogpixels,0,w);
        pixels = ogpixels;
    }

    public Sprite(String path) {
        try {
            image = ImageIO.read(new FileInputStream(path));
            int w = image.getWidth();
            int h = image.getHeight();
            pixels = new int [93*31];
            image.getRGB(0,0,w,h,pixels,0,w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public Sprite getSubImage(int x, int y, int w, int h) {
        return new Sprite(image.getSubimage(x,y,w,h));
    }
    public Image getFxImage() {
        WritableImage wr = new WritableImage(SpriteSheet.getTileSize(), SpriteSheet.getTileSize());
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x <SpriteSheet.getTileSize(); x++) {
            for (int y = 0; y < SpriteSheet.getTileSize(); y++) {
                if ( pixels[x + y * SpriteSheet.getTileSize()] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                }
                else {
                    pw.setArgb(x, y, pixels[x + y * SpriteSheet.getTileSize()]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return input;
    }

//    public static final int DEFAULT_SIZE = 34;
//    public static final int SCALED_SIZE = DEFAULT_SIZE * 4;
//    private static final int TRANSPARENT_COLOR = 0xffff00ff;
//    public final int SIZE;
//    public int[] pixels;
//
//    public Sprite(int size, int color) {
//        this.SIZE = size;
//        this.pixels = new int[SIZE * SIZE];
//        setColor(color);
//
//    }
//
//    public void setColor(int color) {
//        for(int i =0; i < pixels.length; i++) {
//            pixels[i] = color;
//        }
//    }
//
//    public int getSIZE() {
//        return SIZE;
//    }
//
//    public int getPixels(int num) {
//        return pixels[num];
//    }

}
