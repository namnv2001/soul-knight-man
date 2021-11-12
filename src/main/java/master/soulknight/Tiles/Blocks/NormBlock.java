package master.soulknight.Tiles.Blocks;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Util.AABB;
import master.soulknight.Util.Vector2f;

public class NormBlock extends Block{
    public NormBlock(int width, int height, Sprite img, Vector2f pos) {
        super(width, height, img, pos);
    }

    @Override
    public boolean update(AABB p) {
        return false;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }

    @Override
    public Sprite getImage() {
        return null;
    }

    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}
