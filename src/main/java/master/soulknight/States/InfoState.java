package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;

import java.io.FileInputStream;
import java.io.IOException;

public class InfoState extends GameState {

    Image backgroundImage;

    public InfoState(GameStateManager gsm) {
        super(gsm);
        try {
            backgroundImage = new Image(new FileInputStream("src/main/resources/Sprite/info.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
    }


    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(backgroundImage, 0, 0);
    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
    }
}
