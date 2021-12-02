package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Tiles.TileManager;

public class PlayState extends GameState {

    public static boolean gameOver = false;
    public static TileManager tm;

    protected final double scaling = 2;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        tm = new TileManager("src/main/resources/levels/Level2.txt", scaling);
    }

    @Override
    public void update() {
        gameOver = tm.gameOver;
        tm.update();
    }

    @Override
    public void render(GraphicsContext gc) {
        tm.render(gc);
    }
}
