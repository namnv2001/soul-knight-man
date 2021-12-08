package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import master.soulknight.GamePanel;
import master.soulknight.Graphics.Font;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;
import master.soulknight.Util.Vector2f;

import java.util.ArrayList;

public class PlayState extends GameState {

    protected final double scaling = 2;
    TileManager tm1 = new TileManager("src/main/resources/levels/Level1.txt", "src/main/resources/Sprite/Ui/Maps/Map1.png", scaling);
    TileManager tm2 = new TileManager("src/main/resources/levels/Level2.txt", "src/main/resources/Sprite/Ui/Maps/Map2.png", scaling);
    Font font;

    private int delay = 0;
    private int currentLevel = 0;
    private static int level = 0;

    public static boolean gameOver = false;
    public final ArrayList<TileManager> tms = new ArrayList<>();

    public PlayState(GameStateManager gsm) {
        super(gsm);
        font = new Font("src/main/resources/Sprite/Ui/Font/FontSheet.png",10,10);
        tms.add(tm1);
        tms.add(tm2);
    }

    public static void levelUp() {
        level++;
    }

    @Override
    public void update() {
        tms.get(level).update();
        if (tms.get(level).gameOver) {
            level = 0;
            gsm.pop(0);
            gsm.add(3);
            tms.get(level).gameOver = false;
        }
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
            gc.fillRect(0, 0, GamePanel.width, GamePanel.height);
        }
        SpriteSheet.drawArray(gc,font,GamePanel.oldFrameCount + " FPS",new Vector2f(GamePanel.width - 180,807),40,40);
    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        tms.get(level).input(keyHandler);
    }
}
