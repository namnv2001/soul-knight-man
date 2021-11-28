package master.soulknight.Tiles.Blocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public class ExBombItem extends Block{
    public ExBombItem(int width, int height, Image img, Vector2f pos) {
        super(width, height, img, pos);
    }

    @Override
    public void update() {
        System.out.println("ExBomb");
        TileManager.player.addBombsInHand();
    }

    @Override
    public Sprite getImage() {
        return null;
    }

    public void render(GraphicsContext gc, int TILE_SIZE) {
        gc.drawImage(img, pos.x, pos.y, TILE_SIZE, TILE_SIZE);
    }
}
