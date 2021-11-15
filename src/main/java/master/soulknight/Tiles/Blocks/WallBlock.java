package master.soulknight.Tiles.Blocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Util.AABB;
import master.soulknight.Util.Vector2f;

public class WallBlock extends Block{

    public WallBlock(int width, int height, Image img, Vector2f pos) {
        super(width, height, img, pos);
    }

    @Override
    public boolean update(AABB p) {
        return true;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }

    @Override
    public Sprite getImage() {
        return null;
    }


}