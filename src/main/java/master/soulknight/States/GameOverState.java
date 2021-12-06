package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;

import java.io.FileInputStream;
import java.io.IOException;

public class GameOverState extends GameState {

    Image image;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        try {
            image = new Image(new FileInputStream("src/main/resources/Sprite/GameOver.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, 0, 0);
    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        if(mouseHandler.getPos() != null && !gsm.isPlayState()) {
            double x = mouseHandler.getPos().x;
            double y = mouseHandler.getPos().y;
            System.out.println(x + " " + y);
            if (gsm.isGameOverState() && x >= 458 && x <= 555 && y >= 687 && y <= 734) {
                System.out.println("--------------------[Before add]: " + gsm.getSize());
                gsm.pop(0);
                gsm.add(1);
                System.out.println("--------------------[After add]: " + gsm.getSize());
            }
            if (gsm.isGameOverState() && x >= 866 && x <= 944 && y >= 687 && y <= 734) {
                System.out.println("quit");
                gsm.pop(0);
                gsm.add(0);
            }
            mouseHandler.resetPos();

        }
    }
}
