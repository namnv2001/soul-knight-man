package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;

import java.io.FileInputStream;
import java.io.IOException;

public class PickChampState extends GameState {

    Image backgroundImage;

    public PickChampState(GameStateManager gsm) {
        super(gsm);
        try {
            backgroundImage = new Image(new FileInputStream("src/main/resources/Sprite/PickChamp.png"));
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
            // Back to menu state
            if (gsm.isPickChampState() && x >= 1249 && x <= 1344 && y >= 25 && y <= 130) {
                gsm.pop(0);
                gsm.add(0);
            }
            // Pick first champ
            if (gsm.isPickChampState() && x >= 242 && x <= 512 && y >= 690 && y <= 775) {
                gsm.pop(0);
                gsm.add(1);
                System.out.println("Pick 1st champ");
            }
            // Pick second champ
            if (gsm.isPickChampState() && x >= 576 && x <= 855 && y >= 690 && y <= 775) {
                gsm.pop(0);
                gsm.add(1);
                System.out.println("Pick 2nd champ");
            }
            // Pick third champ
            if (gsm.isPickChampState() && x >= 934 && x <= 1213 && y >= 690 && y <= 775) {
                gsm.pop(0);
                gsm.add(1);
                System.out.println("Pick 3rd champ");
            }
        }
    }
}
