package master.soulknight.Tiles.Blocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Entities.Player;
import master.soulknight.Util.Vector2f;

public class ExBombItem extends Block {
    public ExBombItem(int width, int height, Image img, Vector2f pos, Player player) {
        super(width, height, img, pos, player);
    }

    @Override
    public void update() {
        this.player.addBombsInHand();
    }

    public void render(GraphicsContext gc, int TILE_SIZE) {
        gc.drawImage(img, pos.x, pos.y, TILE_SIZE, TILE_SIZE);
    }
}
