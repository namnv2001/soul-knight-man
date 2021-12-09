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

public class GameOverState extends GameState {

    Font font;
    Image image;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        font = new Font("src/main/resources/Sprite/Ui/Font/FontSheet.png",10,10);
        try {
            image = new Image(new FileInputStream("src/main/resources/Sprite/Ui/States/GameOver.png"));
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
        // x,y pos in new Vector2f(x,y)
        SpriteSheet.drawArray(gc,font,"Score:" + TileManager.score,new Vector2f(400,400),40,40);

    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        if(mouseHandler.getPos() != null && !gsm.isPlayState()) {
            double x = mouseHandler.getPos().x;
            double y = mouseHandler.getPos().y;
            System.out.println(x + " " + y);
            if (gsm.isGameOverState() && x >= 612 && x <= 705 && y >= 640 && y <= 669) {
                gsm.pop(0);
                gsm.add(1);
            }
            if (gsm.isGameOverState() && x >= 738 && x <= 807 && y >= 640 && y <= 669) {
                System.out.println("quit");
                gsm.pop(0);
                gsm.add(0);
            }
            mouseHandler.resetPos();
        }
    }
}
