package master.soulknight.Entities.Enemy;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Entities.Entity;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.States.PlayState;
import master.soulknight.Tiles.TileCollision;
import master.soulknight.Util.Vector2f;

public class Enemy extends Entity {

    public Enemy(SpriteSheet sprite, Vector2f origin, int size, double SCALING) {
        super(sprite, origin, size, SCALING);
        up = true;
    }

    public void changeDirection() {
        if(up) {
            up = false;
            down = true;
        } else if(down) {
            down = false;
            up = true;
        } else if(right) {
            right = false;
            left = true;
        } else {
            left = false;
            right = true;
        }
    }

    @Override
    public void update() {
        animated();
        move();
        super.update();
        pos.x += dx;
        pos.y += dy;
        if(TileCollision.isCollidedWithBlock(PlayState.enemy)) {
            changeDirection();
            pos.x -= dx;
            pos.y -= dy;
        }

    }

    public void render(GraphicsContext gc){
        gc.drawImage(ani.getImage().getFxImage(), pos.x, pos.y,
                SpriteSheet.getTileSize() * Entity.getSCALING(),
                SpriteSheet.getTileSize() * Entity.getSCALING());
    }
}
