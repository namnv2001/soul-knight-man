package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;

public class PauseState extends GameState {

    public PauseState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {
        System.out.println("1");
    }

    @Override
    public void render(GraphicsContext gc) {
    }


    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {

    }
}
