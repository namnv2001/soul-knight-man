package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import master.soulknight.GamePanel;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.Vector2f;

import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> states;

    public static Vector2f map;

    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;
    public static final int EDIT = 4;

    public static SpriteSheet ui;
    public static SpriteSheet button;
    public static GraphicsContext gc;

    public GameStateManager(GraphicsContext gc) {
        map = new Vector2f(GamePanel.width, GamePanel.height);
        GameStateManager.gc = gc;
        states = new ArrayList<>();
        Vector2f.setWorldVar(map.x, map.y);

        states.add(new PlayState(this));
    }

    public void pop(int state) {
        states.remove(state);
    }

    public void add(int state) {
        if(states.get(state) != null) {
            return;
        }
        if(state == PLAY){
            states.add(new PlayState(this));
        }
        if(state == MENU){
            states.add(new MenuState(this));
        }
        if(state == PAUSE){
            states.add(new PauseState(this));
        }
        if(state == GAMEOVER){
            states.add(new GameOverState(this));
        }
    }

    public void update() {
        for (int i = 0; i < states.size(); i++) {
            states.get(i).update();
        }
    }
//
//    public void input( KeyEvent key) {
//        for (int i = 0; i < states.size(); i++) {
//            System.out.println("2");
//            states.get(i).input(key);
//        }
//    }

    public void render(GraphicsContext gc) {
        for (int i = 0; i < states.size(); i++) {
            states.get(i).render(gc);
        }
    }
}


