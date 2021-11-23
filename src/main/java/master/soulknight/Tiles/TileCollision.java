package master.soulknight.Tiles;

import master.soulknight.Entities.Bomb;
import master.soulknight.Entities.Player;
import master.soulknight.Tiles.Blocks.Block;

public class TileCollision {
    static double realWidth = TileManager.mapColumns * 62;
    static double realHeight = TileManager.mapRows * 62;
    static double widthRatio = realWidth / TileManager.mapColumns;
    static double heightRatio = realHeight / TileManager.mapRows;

    public static boolean isCollidedWithBlock(Player player) {
//        for (int i = 0; i < TileManager.mapRows; i++) {
//            for (int j = 0; j < TileManager.mapColumns; j++) {
//                if (TileManager.getCollideMapValue(i, j) != 0) {
//                    boolean isIntersection = squareSquareIntersect(i, j, player);
//                    if (isIntersection) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;

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

   public static boolean isCollidedWithBombs(Player player) {
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
