package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Util.KeyHandler;

public abstract class GameState {
    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void input(KeyHandler key);
    public abstract void render(GraphicsContext gc);
}
