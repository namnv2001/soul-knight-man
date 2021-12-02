package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;

public abstract class GameState {
    protected static GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();

    public abstract void render(GraphicsContext gc);

    public abstract void input(KeyHandler keyHandler, MouseHandler mouseHandler);
}
