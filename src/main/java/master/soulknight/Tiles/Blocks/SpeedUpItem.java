package master.soulknight.Tiles.Blocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Entities.Player;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public class SpeedUpItem extends Block{
    public SpeedUpItem(int width, int height, Image img, Vector2f pos, Player player) {
        super(width, height, img, pos, player);
    }

    @Override
    public void update() {
        this.player.addSpeed();
    }

    @Override
    public Sprite getImage() {
        return null;
    }

    public void render(GraphicsContext gc, int TILE_SIZE) {
        gc.drawImage(img, pos.x, pos.y, TILE_SIZE, TILE_SIZE);
    }
}
