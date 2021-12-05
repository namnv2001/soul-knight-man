package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;

public class GameOverState extends GameState {

    public GameOverState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.AQUA);
        gc.fillRect(0,0,122,111);
    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        if(mouseHandler.getPos() != null) {
            if(mouseHandler.getPos().x > 1000 && mouseHandler.getPos().y > 500) {
                System.out.println("clicked");
            }
        }
    }
}
