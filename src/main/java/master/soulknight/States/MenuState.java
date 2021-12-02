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
