package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;

public class MenuState extends GameState {

    Image backgroundImage;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        try {
             backgroundImage = new Image(new FileInputStream("src/main/resources/Sprite/menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void mouseEvent(double x, double y) {
        if (gsm.isMenuState() && x >= 130 && y >= 540 && x <= 345 && y <= 715) {
            gsm.pop(0);
            gsm.add(1);
        }
        if (gsm.isMenuState() && x >= 415 && y >= 540 && x <= 630 && y <= 715) {
            gsm.pop(0);
            gsm.add(4);
        }
        if (gsm.isInfoState() && x >= 1307 && y >= 71 && x <= 1363 && y <= 133) {
            gsm.pop(0);
            gsm.add(0);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        int scale = 5;
        gc.drawImage(backgroundImage, 0, 0);
//        gc.setFill(Color.BLUE);
//        gc.fillRect(130, 540, 43*scale, 35*scale);
//        gc.fillRect(415, 540, 43*scale, 35*scale);
//        gc.drawImage(playBtn, 200, 600, 43*scale, 35*scale);
//        gc.drawImage(infoBtn, 800, 600, 43*scale, 35*scale);
    }
}
