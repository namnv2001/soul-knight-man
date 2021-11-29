package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import master.soulknight.GamePanel;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Ui.Button;
import master.soulknight.Util.Vector2f;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class MenuState extends GameState {

    private Button playBtn;
    private Button guideBtn;
    Image image;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        try {
             image = new Image(new FileInputStream("src/main/resources/Sprite/Menu/menu-background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void mouseEvent(double x, double y) {
        if ( x > 0 && y > 0 && gsm.isMenuState()) {
            gsm.pop(0);
            gsm.add(1);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, 0, 0);
    }
}
