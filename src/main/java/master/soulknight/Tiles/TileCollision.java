package master.soulknight.Tiles;

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

    private static boolean squareSquareIntersect(int row, int col, Player player) {
        //RectA.X1 < RectB.X2 && RectA.X2 > RectB.X1 && RectA.Y1 < RectB.Y2 && RectA.Y2 > RectB.Y1

        // X and Y coordinate of player
        int playerX = (int) Math.ceil(player.getX() / widthRatio);
        int playerY = (int) Math.ceil(player.getY() / heightRatio);
        System.out.println(playerX + " " + playerY);
        if (playerX == col && playerY == row) {
            System.out.println("collied");
            return true;
        }
        return false;
    }
}