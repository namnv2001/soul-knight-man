package master.soulknight.Tiles;

import master.soulknight.Entities.Bomb;
import master.soulknight.Entities.Entity;
import master.soulknight.Entities.Flame;
import master.soulknight.Tiles.Blocks.Block;
import master.soulknight.Entities.Portal;

import java.util.ArrayList;

public class TileCollision {
    public static boolean isCollidedWithBlock(Entity player, ArrayList<Block> collideBlocks) {
        for (Block block : collideBlocks) {
            if (player.getX() + 7 < block.pos.x + block.getBlockWidth() &&
                    player.getX() + 7 + player.getSize() > block.pos.x &&
                    player.getY() + 7 < block.pos.y + block.getBlockHeight() &&
                    player.getY() + 7 + player.getSize() > block.pos.y) {
                return true;
            }
        }
        return false;
    }

    public static boolean enemyIsCollidedWithBlock(Entity player, ArrayList<Block> collideBlocks) {
        for (Block block : collideBlocks) {
            if (player.getX()  < block.pos.x + block.getBlockWidth() &&
                    player.getX() + player.getSize() > block.pos.x &&
                    player.getY() < block.pos.y + block.getBlockHeight() &&
                    player.getY() + player.getSize() > block.pos.y) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCollidedWithItem(Entity player, Block block) {

        return player.getX() + 7 < block.pos.x + block.getBlockWidth() &&
                player.getX() + 7 + player.getSize() > block.pos.x &&
                player.getY() + 7 < block.pos.y + block.getBlockHeight() &&
                player.getY() + 7 + player.getSize() > block.pos.y;
    }

    public static boolean isCollidedWithFlames(Entity player, ArrayList<Flame> flames) {
        for (Flame flame : flames) {
            if (player.getX() + 7 < flame.getX() + flame.getSize() &&
                    player.getX() + 7 + player.getSize() > flame.getX() &&
                    player.getY() + 7 < flame.getY() + flame.getSize() &&
                    player.getY() + 7 + player.getSize() > flame.getY()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCollidedWithBombs(Entity player, ArrayList<Bomb> bombs) {
        for (Bomb bomb : bombs) {
            if (player.getX() + 7 < bomb.getX() + bomb.getSize() &&
                    player.getX() + 7 + player.getSize() > bomb.getX() &&
                    player.getY() + 7 < bomb.getY() + bomb.getSize() &&
                    player.getY() + 7 + player.getSize() > bomb.getY()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isColliedWithEnemy(Entity player, Entity enemy) {
        return player.getX() + 7 < enemy.getX() + enemy.getSize() &&
                player.getX() + 7 + player.getSize() > enemy.getX() &&
                player.getY() + 7 < enemy.getY() + enemy.getSize() &&
                player.getY() + 7 + player.getSize() > enemy.getY();
    }

    public static boolean isColliedWithPortals(Entity player, ArrayList<Portal> portals) {
        for (Portal block : portals) {
            if (player.getX() + 7 < block.getX() + block.getSize() &&
                    player.getX() + 7 + player.getSize() > block.getX() &&
                    player.getY() + 7 < block.getY() + block.getSize() &&
                    player.getY() + 7 + player.getSize() > block.getY()) {
                return true;
            }
        }
        return false;
    }
}
