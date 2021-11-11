package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import master.soulknight.Entities.Player;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.Vector2f;

public class PlayState extends GameState{

    private Player player;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        player = new Player(new SpriteSheet("src/main/resources/Sprite/Druid2 - Copy.png"), new Vector2f(360,240), 128);
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void input(KeyHandler key) {
        System.out.println("3");
        player.input(key);
    }

    @Override
    public void render(GraphicsContext gc) {
        player.render(gc);
    }
}
