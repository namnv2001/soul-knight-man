package master.soulknight.Tiles.Blocks;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Util.AABB;
import master.soulknight.Util.Vector2f;

public abstract class Block {
    protected int width;
    protected int height;

    public Sprite img;
    public Vector2f pos;

    public Block(int width, int height, Sprite img, Vector2f pos) {
        this.width = width;
        this.height = height;
        this.img = img;
        this.pos = pos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract boolean update(AABB p);
    public abstract boolean isInside(AABB p);

    public abstract Sprite getImage();

    public Vector2f getPos() {
        return pos;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(SwingFXUtils.toFXImage(img.image,null), pos.getWorldVar().x,  pos.getWorldVar().y);
    }
}
