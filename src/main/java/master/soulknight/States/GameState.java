package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public abstract class GameState {
    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();
//    public abstract void input(KeyEvent key);
    public abstract void render(GraphicsContext gc);
}
