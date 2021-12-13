package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.Vector2f;

public class Flame extends Bomb {

    private final double realSize = Entity.getSCALING() * SpriteSheet.getTileSize();
    public boolean removed;
    private int flameCounter;

    public Flame(SpriteSheet sprite, Vector2f origin, int size, double SCALING, int bombRange) {
        super(sprite, origin, size, SCALING, bombRange);
        flameCounter = 0;
        removed = false;
    }

    public void update() {
        if (flameCounter > 1) {
            removed = true;
        }
    }

    public void render(GraphicsContext gc) {
        flameCounter++;
        gc.drawImage(ani.getImage().getFxImage(), (int) (pos.x), (int) (pos.y), realSize, realSize);
    }
}
