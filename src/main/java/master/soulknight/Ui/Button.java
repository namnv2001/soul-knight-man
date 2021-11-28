package master.soulknight.Ui;

import master.soulknight.Util.AABB;
import master.soulknight.Util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    private BufferedImage image;

    private Vector2f iPos;

    private AABB bounds;
    private boolean clicked;

    public Button(BufferedImage image, Vector2f pos, int buttonWidth, int buttonHeight) {
        if (buttonWidth == -1) {
            buttonWidth = buttonHeight;
        }
        this.image = createButton(image, buttonWidth, buttonHeight, buttonWidth, buttonHeight);
        this.iPos = new Vector2f(pos.x - this.image.getWidth() / 2, pos.y - this.image.getHeight() / 2);
        this.bounds = new AABB(iPos, this.image.getWidth(), this.image.getHeight());
    }

    public BufferedImage createButton(BufferedImage image, int width, int height, int buttonWidth, int buttonHeight) {
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        if (image.getWidth() != width || image.getHeight() != height) {
            image = resizeImage(image, width, height);
        }

        Graphics g = result.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);

        return result;
    }

    public BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();

        g.drawImage(temp, 0, 0, null);
        g.dispose();
        return result;
    }
}
