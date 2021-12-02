package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;

import java.util.ArrayList;

public class PlayState extends GameState {

    protected final double scaling = 2;
    protected final TileManager tm1 = new TileManager("src/main/resources/levels/Level1.txt", scaling);
    protected final TileManager tm2 = new TileManager("src/main/resources/levels/Level2.txt", scaling);

    private int delay = 0;
    private int currentLevel = 0;
    private static int level = 0;

    public static boolean gameOver = false;
    public final ArrayList<TileManager> tms = new ArrayList<>();

    public PlayState(GameStateManager gsm) {
        super(gsm);
        tms.add(tm1);
        tms.add(tm2);
    }

    public static void levelUp() {
        level++;
    }

    @Override
    public void update() {
        tms.get(level).update();
    }

    @Override
    public void render(GraphicsContext gc) {
        if (delay++ > 40) {
            tms.get(level).render(gc);
            if(currentLevel != level) {
                delay = 0;
                currentLevel++;
            }
        } else {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, 1364, 868);
        }
    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        tms.get(level).input(keyHandler);
    }
}
