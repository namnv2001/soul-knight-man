package master.soulknight.Tiles.Map;

import master.soulknight.Entities.Bomb;
import master.soulknight.Entities.Enemy.Enemy;
import master.soulknight.Tiles.Blocks.Block;
import master.soulknight.Tiles.TileManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConvertMap {
    public static List<Node> path = new ArrayList<>();
    static int[][] mapMatrix = new int[13][23];
    static int[][] mapMatrixBackup = new int[13][23];
    static boolean[][] visited = new boolean[13][23];
    static int[] rowNum = {-1, 0, 0, 1};
    static int[] colNum = {0, -1, 1, 0};

    public static void convert2D(TileManager tileManager, int size) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 23; j++) {

                int playerX = Math.round(tileManager.player.getPos().x / size);
                int playerY = Math.round(tileManager.player.getPos().y / size);
                if (playerX == j && playerY == i) {
                    mapMatrix[i][j] = 2;
                } else {
                    mapMatrix[i][j] = 0;
                }
                for (Enemy enemy : tileManager.getEnemies()) {
                    int enemyX = Math.round(enemy.getPos().x / size);
                    int enemyY = Math.round(enemy.getPos().y / size);
                    if (enemyX == j && enemyY == i) {
                        mapMatrix[i][j] = 3;
                    }
                }
                for (Block block : tileManager.collideBlocks) {
                    int x = (int) (block.pos.x / size);
                    int y = (int) (block.pos.y / size);
                    if (x == j && y == i) {
                        mapMatrix[i][j] = 1;
                    }
                }
                for (Bomb bomb : tileManager.getBombs()) {
                    int x = (int) (bomb.getPos().x / size);
                    int y = (int) (bomb.getPos().y / size);
                    if (x == j && y == i) {
                        mapMatrix[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 23; j++) {
                visited[i][j] = false;
            }
        }

//                for(int i = 0 ; i < 13 ; i++) {
//            for (int j = 0 ; j < 23 ; j++) {
//                System.out.print(mapMatrix[i][j] + " ");
//            }
//            System.out.println();
//        }
        findConnect(mapMatrix, mapMatrixBackup, visited);

    }

    public static int[][] checkSurround(int[][] mapMatrix, int[][] mapMatrixBackup, int x, int y) {
        if (mapMatrix[x + 1][y] == 0) {
            mapMatrixBackup[x + 1][y] = 1;
        }
        if (mapMatrix[x - 1][y] == 0) {
            mapMatrixBackup[x - 1][y] = 1;
        }
        if (mapMatrix[x][y + 1] == 0) {
            mapMatrixBackup[x][y + 1] = 1;
        }
        if (mapMatrix[x][y - 1] == 0) {
            mapMatrixBackup[x][y - 1] = 1;
        }
        return mapMatrixBackup;
    }

    public static void findConnect(int[][] mapMatrix, int[][] mapMatrixBackup, boolean[][] visited) {
        int srcX = 0;
        int srcY = 0;
        int destX = 0;
        int destY = 0;
        for (int i = 1; i < 12; i++) {
            for (int j = 1; j < 22; j++) {
                if (mapMatrix[i][j] == 3 && !visited[i][j]) {
                    mapMatrixBackup[i][j] = 1;
                    srcX = i;
                    srcY = j;
                    checkSurround(mapMatrix, mapMatrixBackup, i, j);
                    visited[i][j] = true;
                    i = 1;
                    j = 1;
                }
                if (mapMatrixBackup[i][j] == 1 && !visited[i][j]) {
                    checkSurround(mapMatrix, mapMatrixBackup, i, j);
                    visited[i][j] = true;
                    j = 1;
                    i = 1;
                }
                if (mapMatrix[i][j] == 2) {
                    destX = i;
                    destY = j;
                }
            }
        }
//        for(int i = 0 ; i < 13 ; i++) {
//            for (int j = 0 ; j < 23 ; j++) {
//                System.out.print(mapMatrixBackup[i][j] + " ");
//            }
//            System.out.println();
//        }
        //System.out.println();
        path = BFS(mapMatrixBackup, new Node(srcX, srcY, null), new Node(destX, destY, null));
    }

    private static void findPath(Node node, List<Node> path) {
        if (node != null) {
            findPath(node.parent, path);
            path.add(node);
        }
    }

    public static List<Node> BFS(int[][] mapMatrixBackup, Node src, Node dest) {

        List<Node> path = new ArrayList<>();

        if (mapMatrixBackup[src.x][src.y] != 1 ||
                mapMatrixBackup[dest.x][dest.y] != 1) {
            return path;
        }
        boolean[][] bfsVisited = new boolean[13][23];

        bfsVisited[src.x][src.y] = true;
        Queue<Node> q = new LinkedList<>();

        q.add(src);

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == dest.x && cur.y == dest.y) {
                findPath(cur, path);
                return path;
            }

            for (int i = 0; i < 4; i++) {
                int row = cur.x + rowNum[i];
                int col = cur.y + colNum[i];

                if (isValid(row, col)
                        && mapMatrixBackup[row][col] == 1
                        && !bfsVisited[row][col]) {
                    bfsVisited[row][col] = true;
                    Node Adjcell = new Node(row, col, cur);
                    q.add(Adjcell);
                }
            }
        }
        return path;
    }

    static boolean isValid(int row, int col) {
        return (row >= 0) && (row < 13) && (col >= 0) && (col < 23);
    }

    public static class Node {
        int x, y;
        Node parent;

        Node(int x, int y, Node parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "(" + x + " " + y + ")";
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
