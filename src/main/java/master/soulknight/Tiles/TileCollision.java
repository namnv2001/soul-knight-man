package master.soulknight.Tiles;

import master.soulknight.Entities.Bomb;
import master.soulknight.Entities.Entity;
import master.soulknight.Entities.Player;
import master.soulknight.Tiles.Blocks.Block;

public class TileCollision {
    static double realWidth = TileManager.mapColumns * 62;
    static double realHeight = TileManager.mapRows * 62;
    static double widthRatio = realWidth / TileManager.mapColumns;
    static double heightRatio = realHeight / TileManager.mapRows;

    public static boolean isCollidedWithBlock(Entity player) {
        for ( Block block : TileManager.collideBlocks) {
            if(player.getX() + 7 < block.pos.x + block.getBlockWidth() &&
               player.getX() + 7 + player.getSize() > block.pos.x &&
               player.getY() + 7 < block.pos.y + block.getBlockHeight() &&
               player.getY() + 7 +player.getSize() > block.pos.y) {
                return true;
            }
        }
        return false;
    }

   public static boolean isCollidedWithBombs(Entity player) {
       for (Bomb bomb : TileManager.getBombs()) {
           if (player.getX() + 7 < bomb.getX() + bomb.getSize() &&
                   player.getX() + 7 + player.getSize() > bomb.getX() &&
                   player.getY() + 7 < bomb.getY() + bomb.getSize() &&
                   player.getY() + 7 + player.getSize() > bomb.getY()) {
               return true;
           }
       }
       return false;
   }



}
