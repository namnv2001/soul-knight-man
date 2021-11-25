package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.GamePanel;
import master.soulknight.Ui.Button;
import master.soulknight.Util.Vector2f;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class MenuState extends GameState {

    private Button playBtn;
    private Button guideBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        BufferedImage btnImage = GameStateManager.button.getSubImage(0, 0, 121, 26);

        playBtn = new Button(btnImage, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 - 48), 32, 16);

    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {

    }
}
