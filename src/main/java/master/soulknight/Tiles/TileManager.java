package master.soulknight.Tiles;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Entities.Bomb;
import master.soulknight.Entities.Enemy.Enemy;
import master.soulknight.Entities.Flame;
import master.soulknight.Entities.Player;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.Blocks.*;
import master.soulknight.Util.Vector2f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TileManager {


    public static final SpriteSheet itemTileSheet = new SpriteSheet("src/main/resources/Sprite/Items.png");
    public static final SpriteSheet tileSheet = new SpriteSheet("src/main/resources/Sprite/map1.png");

    public static Sprite wall1 = tileSheet.getSpriteArray(0,0);
    public static Sprite wall2 = tileSheet.getSpriteArray(1,0);
//    public static Sprite[] walls = {wall1,wall2,wall3,wall4};

    public static Sprite box = tileSheet.getSpriteArray(2,0);

    public static Sprite floor = tileSheet.getSpriteArray(3,0);
//    public static Sprite[] floors = {floor1,floor2,floor3};

    public static Sprite exBombItem = itemTileSheet.getSpriteArray(0,0);
    public static Sprite speedItem = itemTileSheet.getSpriteArray(2,0);
    public static Sprite powerUpItem = itemTileSheet.getSpriteArray(1,0);

    public static int mapRows;
    public static int mapColumns;
    public static Player player;
    public static Enemy enemy;
    public static ArrayList<Block> collideBlocks = new ArrayList<>();
    protected static ArrayList<Block> items = new ArrayList<>();
    protected static ArrayList<Enemy> enemies = new ArrayList<>();
    protected static ArrayList<Flame> flames = new ArrayList<>();
    protected static ArrayList<Bomb> bombs = new ArrayList<>();
    protected static ArrayList<Block> floorBlocks = new ArrayList<>();
    protected static int TILE_SIZE = 31;
    public boolean gameOver = false;
    protected double scaling;
    protected int level;
    protected int rows;
    protected int columns;

    public TileManager(String path, double scaling) {
        this.scaling = scaling;
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
                if (block.breakable()) {
                    collideBlocks.remove(block);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean colliedBomb(int x, int y) {
        for (Bomb bomb : bombs) {
            if (bomb.getX() == x && bomb.getY() == y) {
                bomb.endCounter();
                return true;
            }
        }
        return false;
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
                    Block item;
                    if (mapStr[i].charAt(j) == '1') {
                        block = new WallBlock(TILE_SIZE, TILE_SIZE, wall1.getFxImage()
                                , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                        collideBlocks.add(block);

                    }  else if (mapStr[i].charAt(j) == '2') {
                        block = new WallBlock(TILE_SIZE, TILE_SIZE, wall2.getFxImage()
                                , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                        collideBlocks.add(block);
                    } else if (mapStr[i].charAt(j) == '3') {
                        block = new BoxBlock(TILE_SIZE, TILE_SIZE, box.getFxImage()
                                , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                        Random random = new Random();
                        int randNum = random.nextInt((5-1)+1) + 1;
                        if ( randNum == 1) {
                            item = new PowerUpItem(TILE_SIZE, TILE_SIZE, powerUpItem.getFxImage()
                                    , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                            items.add(item);
                        }
                        if ( randNum == 2) {
                            item = new ExBombItem(TILE_SIZE, TILE_SIZE, exBombItem.getFxImage()
                                    , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                            items.add(item);
                        }
                        if ( randNum == 3) {
                            item = new SpeedUpItem(TILE_SIZE, TILE_SIZE, speedItem.getFxImage()
                                    , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                            items.add(item);
                        }
                       collideBlocks.add(block);
                    } else if (mapStr[i].charAt(j) == 'p') {
                        player = new Player(new SpriteSheet("src/main/resources/Sprite/Character_1.png"),
                                new Vector2f(TILE_SIZE * j, TILE_SIZE * i), 52, scaling);
                    } else if (mapStr[i].charAt(j) == 'a') {
                        enemy = new Enemy(new SpriteSheet("src/main/resources/Sprite/SnowApe.png"),
                                new Vector2f(TILE_SIZE * j, TILE_SIZE * i), 52, scaling);
                        enemies.add(enemy);
                    }else if (mapStr[i].charAt(j) == 'b') {
                        enemy = new Enemy(new SpriteSheet("src/main/resources/Sprite/Monkey.png"),
                                new Vector2f(TILE_SIZE * j, TILE_SIZE * i), 52, scaling);
                        enemies.add(enemy);
                    }
                    block = new FloorBlock(TILE_SIZE, TILE_SIZE, floor.getFxImage()
                            , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                    floorBlocks.add(block);


                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void update() {
        Iterator<Block> iterator = TileManager.items.iterator();
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (TileCollision.isCollidedWithItem(player,block)) {
                iterator.remove();
                block.update();
            }
        }

        for (Enemy enemy : enemies) {
            if (TileCollision.isColliedWithEnemy(enemy, player)) {
                gameOver = true;
            }
        }
        if (TileCollision.isCollidedWithFlames(player)) {
            gameOver = true;
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (TileCollision.isCollidedWithFlames(enemies.get(i))) {
                enemies.remove(enemies.get(i));
            }
        }
        if (!gameOver) {
            enemies.forEach(Enemy::update);
        }
        player.update();
        bombs.forEach(Bomb::update);
        flames.forEach(Flame::update);
    }

    public void render(GraphicsContext gc) {
        floorBlocks.forEach(g -> g.render(gc, TILE_SIZE));
        items.forEach(g -> g.render(gc,TILE_SIZE));
        collideBlocks.forEach(g -> g.render(gc, TILE_SIZE));
        bombs.forEach(g -> g.render(gc));
        flames.forEach(g -> g.render(gc));
        if (!gameOver) {
            enemies.forEach(g -> g.render(gc));
            player.render(gc);
        }
    }
}