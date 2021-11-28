package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Ui.Button;

public class MenuState extends GameState {

    private Button playBtn;
    private Button guideBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);

//        Sprite btnImage = GameStateManager.button.getSprite(0, 0);

//        playBtn = new Button(btnImage, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 - 48), 32, 16);

    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {

    }
}
