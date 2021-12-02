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
            image = new Image(new FileInputStream("src/main/resources/Sprite/menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        if (mouseHandler.getPos() == null) {
        } else if (mouseHandler.getPos().x > 0 && mouseHandler.getPos().y > 0 && gsm.isMenuState()) {
            gsm.pop(0);
            gsm.add(1);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, 0, 0);
    }
}
