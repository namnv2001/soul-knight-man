package master.soulknight.Graphics;

import java.awt.image.BufferedImage;

public class Sprite {

    public BufferedImage image;

    private int[] pixels;
    private int[] ogpixels;

    private int w;
    private int h;

    public Sprite(BufferedImage image) {
        this.image = image;
        this.w = image.getWidth();
        this.h = image.getHeight();
        ogpixels = image.getRGB(0,0,w,h,ogpixels,0,w);
        pixels = ogpixels;
    }

    public Sprite getSubImage(int x, int y, int w, int h) {
        return new Sprite(image.getSubimage(x,y,w,h));
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
