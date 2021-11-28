package master.soulknight.Ui;

import javafx.scene.canvas.GraphicsContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu {
    private boolean isPlaying = false;
    public static Image background;

    static {
        try {
            background = ImageIO.read(new File("src/main/resources/Sprite/Menu/menu-background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Menu() {

    }

    public void update() {

    }

    public void render(GraphicsContext gc) {
        if (!isPlaying) {
//            gc.drawImage(background);
        }
    }
}
