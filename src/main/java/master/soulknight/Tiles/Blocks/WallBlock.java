package master.soulknight.Tiles.Blocks;

import javafx.scene.image.Image;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Util.Vector2f;

public class WallBlock extends Block {

    public WallBlock(int width, int height, Image img, Vector2f pos) {
        super(width, height, img, pos);
    }

    @Override
    public void update() {

    }


    @Override
    public Sprite getImage() {
        return null;
    }

    @Override
    public boolean breakable() {
        return false;
    }

//    @Override
//    public void render(GraphicsContext gc, int TILE_SIZE) {
//        gc.setFill(Color.RED);
//        gc.fillRect(pos.x,pos.y,TILE_SIZE,TILE_SIZE);
//    }
}
