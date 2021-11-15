package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;


public abstract class GameState {
    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();

    public abstract void render(GraphicsContext gc);
}
