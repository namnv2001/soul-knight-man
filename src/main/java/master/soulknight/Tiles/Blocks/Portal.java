package master.soulknight.Tiles.Blocks;

import javafx.scene.image.Image;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Util.Vector2f;

public class Portal extends Block{

    public Portal(int width, int height, Image img, Vector2f pos) {
        super(width, height, img, pos);
    }

    @Override
    public void update() {

    }

    @Override
    public Sprite getImage() {
        return null;
    }
}
