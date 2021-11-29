package master.soulknight.Graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;

public class Sprite {

    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    public BufferedImage image;
    public int[] pixels;
    private int[] ogpixels;

    public Sprite(BufferedImage image) {
        this.image = image;
        int w = image.getWidth();
        int h = image.getHeight();
        pixels = new int[w * h];
        ogpixels = image.getRGB(0, 0, w, h, ogpixels, 0, w);
        pixels = ogpixels;
    }

    public Sprite getSubImage(int x, int y, int w, int h) {
        return new Sprite(image.getSubimage(x, y, w, h));
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(SpriteSheet.getTileSize(), SpriteSheet.getTileSize());
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SpriteSheet.getTileSize(); x++) {
            for (int y = 0; y < SpriteSheet.getTileSize(); y++) {
                if (pixels[x + y * SpriteSheet.getTileSize()] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                } else {
                    pw.setArgb(x, y, pixels[x + y * SpriteSheet.getTileSize()]);
                }
            }
        }
        return new ImageView(wr).getImage();
    }
}