package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;

import java.util.ArrayList;

public class GameStateManager {

    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int WIN = 2;
    public static final int GAMEOVER = 3;
    public static final int INFO = 4;
    public static final int PICK = 5;
    public static GraphicsContext gc;
    private ArrayList<GameState> states;

    public GameStateManager(GraphicsContext gc) {
        GameStateManager.gc = gc;
        states = new ArrayList<>();
        states.add(new MenuState(this));
    }

    public void pop(int state) {
        states.remove(state);
    }

    public boolean isGameOverState() {
        return states.get(states.size() - 1) instanceof GameOverState;
    }

    public boolean isMenuState() {
        return states.get(states.size() - 1) instanceof MenuState;
    }

    public boolean isInfoState() {
        return states.get(states.size() - 1) instanceof InfoState;
    }

    public boolean isPickChampState() {
        return states.get(states.size() - 1) instanceof PickChampState;
    }

    public boolean isPlayState() {
        return states.get(states.size() - 1) instanceof PlayState;
    }

    public boolean isWinState() {
        return states.get(states.size() - 1) instanceof WinState;
    }

    public void add(int state) {
        if (state == PLAY) {
            states.add(new PlayState(this));
        }
        if (state == MENU) {
            states.add(new MenuState(this));
        }
        if (state == WIN) {
            states.add(new WinState(this));
        }
        if (state == GAMEOVER) {
            states.add(new GameOverState(this));
        }
        if (state == INFO) {
            states.add(new InfoState(this));
        }
        if (state == PICK) {
            states.add(new PickChampState(this));
        }
    }

    public void update() {
        for (GameState state : states) {
            state.update();
        }
    }

    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        for (GameState state : states) {
            state.input(keyHandler, mouseHandler);
        }
    }

    public void render(GraphicsContext gc) {
        for (GameState state : states) {
            state.render(gc);

        }
    }
}
