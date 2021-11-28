package master.soulknight.Tiles;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Entities.Bomb;
import master.soulknight.Entities.Flame;
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
    public static final Sprite tileSheet = new Sprite("src/main/resources/Sprite/map2 31x31.png");
    public static SpriteSheet grass = new SpriteSheet(2, 0, tileSheet);
    public static SpriteSheet wall = new SpriteSheet(1, 0, tileSheet);
    public static SpriteSheet box = new SpriteSheet(0, 0, tileSheet);
    public static int mapRows;
    public static int mapColumns;

    public static ArrayList<Block> collideBlocks = new ArrayList<>();
    protected static ArrayList<Flame> flames = new ArrayList<>();
    protected static ArrayList<Bomb> bombs = new ArrayList<>();
    protected static ArrayList<Block> blocks = new ArrayList<>();
    protected static int TILE_SIZE = 31;
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

    public static void addFlame(Flame flame) {
        flames.add(flame);
    }

    public static boolean colliedTile(int x, int y) {
        for (Block block : collideBlocks) {
            if (block.pos.x == x && block.pos.y == y) {
                if(block.breakable()) {
                    replaceWithFloor(block);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean colliedBomb(int x, int y) {
        for (Bomb bomb : bombs) {
            if(bomb.getX() == x && bomb.getY() == y) {
                bomb.endCounter();
                return true;
            }
        }
        return false;
    }

    public static void replaceWithFloor(Block block) {
        collideBlocks.remove(block);
        block = new FloorBlock(TILE_SIZE, TILE_SIZE, grass.getFxImage(), new Vector2f(block.pos.x, block.pos.y));
        blocks.add(block);
    }

    public static boolean bombExist(Vector2f pos) {
        for (Bomb bomb : bombs) {
            if (bomb.getX() == pos.x && bomb.getY() == pos.y) {
                System.out.println("Bomb exist");
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Flame> getFlames() {
        return flames;
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
                        collideBlocks.add(block);
                    } else if (mapStr[i].charAt(j) == '*') {
                        block = new BoxBlock(TILE_SIZE, TILE_SIZE, box.getFxImage()
                                , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                        collideBlocks.add(block);
                    } else {
                        block = new FloorBlock(TILE_SIZE, TILE_SIZE, grass.getFxImage()
                                , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                        blocks.add(block);
                    }

                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void render(GraphicsContext gc) {
        blocks.forEach(g -> g.render(gc, TILE_SIZE));
        collideBlocks.forEach(g->g.render(gc,TILE_SIZE));
        bombs.forEach(g -> g.render(gc));
        bombs.forEach(Bomb::update);
        flames.forEach(g -> g.render(gc));
        flames.forEach(Flame::update);
    }
}