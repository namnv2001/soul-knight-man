package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;

import java.io.FileInputStream;
import java.io.IOException;

public class MenuState extends GameState {
    Image image;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        try {
            image = new Image(new FileInputStream("src/main/resources/Sprite/Ui/States/Menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        if (mouseHandler.getPos() != null) {
            double x = mouseHandler.getPos().x;
            double y = mouseHandler.getPos().y;

            // To pick champ state
            if (gsm.isMenuState() && x >= 55 && x <= 305 && y >= 545 && y <= 748) {
                gsm.pop(0);
                gsm.add(5);
            }

            // To info state
            if (gsm.isMenuState() && x >= 397 && x <= 647 && y >= 545 && y <= 748) {
                gsm.pop(0);
                gsm.add(4);
            }
            mouseHandler.resetPos();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, 0, 0);
    }
}
