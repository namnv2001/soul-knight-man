package master.soulknight.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Font {

    private Sprite FONTSHEET;
    private Sprite[][] spriteArray;
    public int w;
    public int h;
    private int wLetter;
    private int hLetter;

    public Font(String file, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading " + file + "...");
        try {
            BufferedImage sprite = ImageIO.read(new FileInputStream(file));
            FONTSHEET = new Sprite(sprite);
            wLetter = FONTSHEET.image.getWidth() / w;
            hLetter = FONTSHEET.image.getHeight() / h;
            System.out.println(FONTSHEET.image.getHeight());
            System.out.println(FONTSHEET.image.getWidth());
            loadFontArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFontArray() {
        spriteArray = new Sprite[wLetter][hLetter];

        for (int x = 0; x < wLetter; x++) {
            for (int y = 0; y < hLetter; y++) {
                spriteArray[x][y] = getLetter(x, y);
            }
        }
    }

    public Sprite getLetter(int x, int y) {
        return FONTSHEET.getSubImage(x * w, y * h, w, h);
    }

    public Sprite getFont(char letter) {

        int x = (int) letter % wLetter;
        int y = (int) letter / wLetter;
        return getLetter(x,y);
    }
}
