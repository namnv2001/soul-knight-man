package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;

public class PickChampState extends GameState {

    Image backgroundImage;

    public PickChampState(GameStateManager gsm) {
        super(gsm);
        try {
            backgroundImage = new Image(new FileInputStream("src/main/resources/Sprite/pick champ.png"));
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
}