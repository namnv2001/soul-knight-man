package master.soulknight.Tiles.Blocks;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Entities.Entity;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.AABB;
import master.soulknight.Util.Vector2f;

public abstract class Block {
    protected int width;
    protected int height;

    public Image img;
    public Vector2f pos;

    public Block(int width, int height, Image img, Vector2f pos) {
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

    public void render(GraphicsContext gc, int TILE_SIZE) {
        gc.drawImage(img, pos.x,  pos.y, SpriteSheet.getTileSize() * Entity.getSCALING(),SpriteSheet.getTileSize() * Entity.getSCALING());
    }
}