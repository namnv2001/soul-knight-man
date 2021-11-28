package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Ui.Menu;

public class MenuState extends GameState {

    public static Menu menu = new Menu();

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {
        menu.update();
    }

    @Override
    public void render(GraphicsContext gc) {
        menu.render(gc);
    }
}
