package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import master.soulknight.Entities.Player;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public class PlayState extends GameState{

    public static Player player;
    public static TileManager tm;

    protected final double scaling = 2;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        tm = new TileManager("src/main/resources/levels/Level1.txt",scaling);
       // player1 = new Player(new SpriteSheet("src/main/resources/Sprite/Engineer2 - Copy.png"), new Vector2f(600,240), 64);
        player = new Player(new SpriteSheet("src/main/resources/Sprite/alchemist_0_0 #154237 - Copy.png"), new Vector2f(360,240), 128,scaling);
    }

    @Override
    public void update() {

        player.update();
    }
//
//    @Override
//    public void input(KeyEvent key) {
//        System.out.println("3");
//        player.input(key);
//    }

    @Override
    public void render(GraphicsContext gc) {
        gc.clearRect(0,0,1280,720);
        tm.render(gc);
        player.render(gc);

       // player1.render(gc);
    }
}
