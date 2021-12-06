package master.soulknight.Tiles.Map;

import master.soulknight.Entities.Enemy.Enemy;
import master.soulknight.Tiles.Blocks.Block;
import master.soulknight.Tiles.TileManager;

public class ConvertMap {
    static int[][] mapMatrix = new int[13][23];
    static int[][] mapMatrixBackup = new int[13][23];
    static boolean[][] adjMatrix = new boolean[13*23][13*23];
    static boolean[][] visited = new boolean[13][23];


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
                    if(enemyX == j && enemyY == i) {
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
            }
        }
        for ( int i = 0 ; i < 13 ; i++) {
            for (int j = 0 ; j < 23 ; j++) {
                visited[i][j] = false;
            }
        }
        findConnect(mapMatrix, mapMatrixBackup, visited);

//        for (int i = 0; i < 13; i++) {
//            for (int j = 0; j < 23; j++) {
//                System.out.print(mapMatrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
    public static int[][] checkSurround(int[][]mapMatrix, int[][]mapMatrixBackup, int x, int y) {
        if(mapMatrix[x+1][y] == 0) {
            mapMatrixBackup[x+1][y] = 1;
        }
        if(mapMatrix[x-1][y] == 0) {
            mapMatrixBackup[x-1][y] = 1;
        }
        if(mapMatrix[x][y+1] == 0) {
            mapMatrixBackup[x][y+1] = 1;
        }
        if(mapMatrix[x][y-1] == 0) {
            mapMatrixBackup[x][y-1] = 1;
        }
        return mapMatrixBackup;
    }

    public static void findConnect(int[][] mapMatrix, int[][] mapMatrixBackup, boolean[][] visited) {
        for (int i = 1; i < 12; i++) {
            for (int j = 1; j < 22; j++) {
                if (mapMatrix[i][j] == 3 && !visited[i][j]) {
                    mapMatrixBackup[i][j] = 3;
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
            }
        }
//        System.out.println(canTouchPlayer());
//        for (int i = 0; i < 13; i++) {
//            for (int j = 0; j < 23; j++) {
//                System.out.print(mapMatrixBackup[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    public static boolean canTouchPlayer() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 23; j++) {
                System.out.print(mapMatrixBackup[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0 ; i < 13 ; i++ ) {
            for ( int j = 0 ;  j < 23 ; j++) {
                if (mapMatrixBackup[i][j] == 1 && mapMatrix[i][j] == 2) {
                    return true;
                }
            }
        }
        return false;
    }
//
//    private static int[] BFS(int[][] mapMatrix, int src, int dest) {
//        int size = 13*23;
//        boolean[] visited = new boolean[size];
//        int[] parent = new int[size];
//
//        for ( int i = 0 ; i < size ; i++) {
//            visited[i] = false;
//            parent[i] = -1;
//        }
//
//        LinkedList<Integer> queue = new LinkedList<>();
//        queue.add(src);
//        visited[src] = true;
//        while(queue.size() != 0) {
//            src = queue.peek();
//            queue.remove();
//            for(int i = 0 ; i < size; i++) {
//                if(!visited[i] && mapMatrix[src][i])
//            }
//        }
//
//    }

//    private static int height = 13;
//    private static int width = 23;
//    private static int no_nodes = height * width;
//    public static boolean[][] AdjMatrixBackUp = new boolean[no_nodes][no_nodes];
//    public static boolean[][] AdjMatrixBackUp_copy = new boolean[no_nodes][no_nodes];
//
//
//    public static void modifyMatrixUnit (int temp, int adj_x, int adj_y, boolean[][] adjacencyMatrix) {
//        if (adj_x >= 0 && adj_y >= 0) {
//            if (adj_y * width + adj_x < no_nodes) {
//                adjacencyMatrix[temp][adj_y * width + adj_x] = true;    //has relationship
//                adjacencyMatrix[adj_y * width + adj_x][temp] = true;
//            }
//        }
//    }
//    public static void modifyMatrix(int x, int y, boolean[][] adjacencyMatrix) {
//        /*
//            -->x
//            y       left_up  |mid_up  |right_up
//                    mid_left |*       |mid_right
//                    left_down|mid_down|right_down
//
//
//         */
//        int temp = y * width + x;
//        //get 8 direction
//        int adj_x, adj_y;
//        //mid_up
//        adj_x = x;
//        adj_y = y - 1;
//        modifyMatrixUnit(temp, adj_x, adj_y, adjacencyMatrix);
//        //mid_left
//        adj_x = x - 1;
//        adj_y = y;
//        modifyMatrixUnit(temp, adj_x, adj_y, adjacencyMatrix);
//        //mid_right
//        adj_x = x + 1;
//        adj_y = y;
//        modifyMatrixUnit(temp, adj_x, adj_y, adjacencyMatrix);
//        //mid_down
//        adj_x = x;
//        adj_y = y + 1;
//        modifyMatrixUnit(temp, adj_x, adj_y, adjacencyMatrix);
//    }
//    public static void convert(int[][] mapGame, boolean[][] AdjMatrix) {
        //init AdjMatrix
//        for (int i = 0; i < no_nodes; i++) {
//            for (int j = 0; j < no_nodes; j++) {
//                AdjMatrix[i][j] = false;
//            }
//        }
//
//        for (int i = 1; i < height - 1; i++) {
//            for (int j = 1; j < width - 1; j++) {
//                modifyMatrix(j, i, AdjMatrix);
//            }
//        }
//
//        //make back up AdjMatrix
//        for (int i = 0; i < no_nodes; i++) {
//            for (int j = 0; j < no_nodes; j++) {
//                AdjMatrixBackUp[i][j] = AdjMatrix[i][j];
//                AdjMatrixBackUp_copy[i][j] = AdjMatrix[i][j];
//            }
//        }
//
//        //remove all location which don't really has relationship
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                if (mapGame[i][j] == 1) {
//                    //current position has wall or brick
//                    int index = i * width + j;
//                    for (int k = 0; k < no_nodes; k ++) {
//                        AdjMatrix[index][k] = false;
//                        AdjMatrix[k][index] = false;
//                    }
//                }
//            }
//        }
//
//        for ( int i = 0 ; i < height; i++) {
//            for (int j = 0 ; j< width ; j++) {
//                System.out.print(AdjMatrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }


}
