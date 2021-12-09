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
            backgroundImage = new Image(new FileInputStream("src/main/resources/Sprite/Ui/States/Info.png"));
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
        if (mouseHandler.getPos() != null) {
            double x = mouseHandler.getPos().x;
            double y = mouseHandler.getPos().y;
            if (gsm.isInfoState() && x >= 1294 && x <= 1363 && y >= 71 && y <= 147) {
                gsm.pop(0);
                gsm.add(0);
            }
            mouseHandler.resetPos();
        }
    }
}
