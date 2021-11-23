package master.soulknight.Tiles;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Entities.Bomb;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.Blocks.Block;
import master.soulknight.Tiles.Blocks.BoxBlock;
import master.soulknight.Tiles.Blocks.FloorBlock;
import master.soulknight.Tiles.Blocks.WallBlock;
import master.soulknight.Util.Vector2f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TileManager {
    public static final Sprite tileSheet = new Sprite("src/main/resources/Sprite/ss - Copy.png");
    public static SpriteSheet grass = new SpriteSheet(2, 0, tileSheet);
    public static SpriteSheet wall = new SpriteSheet(1, 0, tileSheet);
    public static SpriteSheet box = new SpriteSheet(0, 0, tileSheet);
    public static int[][] collideMap;
    public static int mapRows;
    public static int mapColumns;

    public static ArrayList<Block> collideBlocks = new ArrayList<>();
    protected static ArrayList<Bomb> bombs = new ArrayList<>();
    protected ArrayList<Block> blocks = new ArrayList<>();
    protected int TILE_SIZE = 31;
    protected int level;
    protected int rows;
    protected int columns;

    public TileManager(String path, double scaling) {
        TILE_SIZE *= scaling;
        readMap(path);
    }

    public static ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public static void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public static int getCollideMapValue(int row, int column) {
        return collideMap[row][column];
    }

    public void readMap(String path) {
        try {
            File file = new File(path);
            String firstLine;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            firstLine = reader.readLine();

            String[] splited = firstLine.split("\\s+");
            level = Integer.parseInt(splited[0]);
            rows = Integer.parseInt(splited[1]);
            columns = Integer.parseInt(splited[2]);
            collideMap = new int[rows][columns];
            mapRows = rows;
            mapColumns = columns;
            int count = 0;
            String[] mapStr = new String[rows];
            String line;
            while ((line = reader.readLine()) != null && count < rows) {
                mapStr[count] = "";
                mapStr[count] += line;
                count++;
            }
            System.out.println(TILE_SIZE);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Block block;
                    if (mapStr[i].charAt(j) == '#') {
                        block = new WallBlock(TILE_SIZE, TILE_SIZE, wall.getFxImage()
                                , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
//                        collideMap[i][j] = 1;
                        collideBlocks.add(block);
                    } else if (mapStr[i].charAt(j) == '*') {
                        block = new BoxBlock(TILE_SIZE, TILE_SIZE, box.getFxImage()
                                , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
//                        collideMap[i][j] = 1;
                        collideBlocks.add(block);
                    } else {
                        block = new FloorBlock(TILE_SIZE, TILE_SIZE, grass.getFxImage()
                                , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                        collideMap[i][j] = 0;
                    }
                    blocks.add(block);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void render(GraphicsContext gc) {
        blocks.forEach(g -> g.render(gc, TILE_SIZE));
        bombs.forEach(g -> g.render(gc));
        bombs.forEach(Bomb::update);
    }
}