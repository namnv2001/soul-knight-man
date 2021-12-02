package master.soulknight.Tiles.Blocks;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import master.soulknight.Entities.Player;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Util.Vector2f;


public abstract class Block {
    public Image img;
    public Vector2f pos;
    public Player player;
    protected int width;
    protected int height;


    public Block(int width, int height, Image img, Vector2f pos) {
        this.width = width;
        this.height = height;
        this.img = img;
        this.pos = pos;
    }

    public Block(int width, int height, Image img, Vector2f pos, Player player) {
        this.img = img;
        this.pos = pos;
        this.player = player;
        this.width = width;
        this.height = height;
    }

    public int getBlockWidth() {
        return width;
    }

    public int getBlockHeight() {
        return height;
    }

    public abstract void update();

    public abstract Sprite getImage();

    public Vector2f getPos() {
        return pos;
    }

    public boolean breakable() {
        return true;
    }

    public boolean solid() {
        return true;
    }

    public void render(GraphicsContext gc, int TILE_SIZE) {
        gc.drawImage(img, pos.x, pos.y, TILE_SIZE, TILE_SIZE);
    }
}
