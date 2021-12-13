package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Graphics.Font;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;
import master.soulknight.Util.Vector2f;

import java.io.FileInputStream;
import java.io.IOException;

public class WinState extends GameState {

    Image image;
    private final Font font;
    public WinState(GameStateManager gsm) {
        super(gsm);
        font = new Font("src/main/resources/Sprite/Ui/Font/FontSheet.png", 10, 10);
        try {
            image = new Image(new FileInputStream("src/main/resources/Sprite/Ui/States/GameClear.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, 0, 0);
        SpriteSheet.drawArray(gc, font, "Player (1): " + TileManager.score, new Vector2f(302, 70), 40, 55);
    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        if (mouseHandler.getPos() != null) {
            if (gsm.isWinState()) {
                gsm.pop(0);
                gsm.add(0);
            }

        }
    }
}
