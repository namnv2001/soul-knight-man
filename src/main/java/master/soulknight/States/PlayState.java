package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Entities.Player;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public class PlayState extends GameState {

    public static Player player;
    public static TileManager tm;

    protected final double scaling = 2;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        tm = new TileManager("src/main/resources/levels/Level1.txt", scaling);
        player = new Player(new SpriteSheet("src/main/resources/Sprite/alchemist_0_0 #154237 - Copy.png"), new Vector2f(62, 62), 52, scaling);
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void render(GraphicsContext gc) {
        tm.render(gc);
        player.render(gc);
    }
}
