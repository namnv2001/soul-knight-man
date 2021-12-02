package master.soulknight.Tiles;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Entities.Bomb;
import master.soulknight.Entities.Enemy.Enemy;
import master.soulknight.Entities.Flame;
import master.soulknight.Entities.Player;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.States.PlayState;
import master.soulknight.Tiles.Blocks.*;
import master.soulknight.Util.KeyHandler;
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

    public static final SpriteSheet tileSheet = new SpriteSheet("src/main/resources/Sprite/Map1.png");

    public final Sprite floor = tileSheet.getSpriteArray(3,0);
//    public static Sprite[] floors = {floor1,floor2,floor3};

    public final Sprite wall1 = tileSheet.getSpriteArray(0,0);
    public final Sprite wall2 = tileSheet.getSpriteArray(1,0);
//    public static Sprite[] walls = {wall1,wall2,wall3,wall4};

    public final Sprite box = tileSheet.getSpriteArray(2,0);

    public final Sprite exBombItem = itemTileSheet.getSpriteArray(0,0);
    public final Sprite speedItem = itemTileSheet.getSpriteArray(2,0);
    public final Sprite powerUpItem = itemTileSheet.getSpriteArray(1,0);

    public static int mapRows;
    public static int mapColumns;

    public Player player;
    public Enemy enemy;

//    public ArrayList<Block> tmCollideBlocks = new ArrayList<>();
    public ArrayList<Block> collideBlocks = new ArrayList<>();

    protected ArrayList<Block> portals = new ArrayList<>();
    protected ArrayList<Block> items = new ArrayList<>();
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    protected ArrayList<Flame> flames = new ArrayList<>();
    protected ArrayList<Bomb> bombs = new ArrayList<>();
    protected ArrayList<Block> floorBlocks = new ArrayList<>();

    protected static int TILE_SIZE = 31;
    protected int realSize = 31;
    protected double scaling;
    protected int level;
    protected int rows;
    protected int columns;

    public boolean gameOver = false;

    public TileManager(String path, double scaling) {
        this.scaling = scaling;
        realSize *= scaling;
        readMap(path);
    }

    public Player getPlayer() {
        return player;
    }

    // Bomb------------------------------------------------------------------------------
    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public boolean colliedBomb(int x, int y) {
        for (Bomb bomb : bombs) {
            if (bomb.getX() == x && bomb.getY() == y) {
                bomb.endCounter();
                return true;
            }
        }
        return false;
    }

    public boolean bombExist(Vector2f pos) {
        for (Bomb bomb : bombs) {
            if (bomb.getX() == pos.x && bomb.getY() == pos.y) {
                System.out.println("Bomb exist");
                return true;
            }
        }
        return false;
    }
    // ----------------------------------------------------------------------------------

    // Flame-----------------------------------------------------------------------------
    public void addFlame(Flame flame) {
        flames.add(flame);
    }

    public boolean colliedTile(int x, int y) {
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

    public ArrayList<Flame> getFlames() {
        return flames;
    }
    // ----------------------------------------------------------------------------------


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
            System.out.println(realSize);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Block block;
                    Block item;

                    if (mapStr[i].charAt(j) == '1') {
                        block = new WallBlock(realSize, realSize, wall1.getFxImage()
                                , new Vector2f(realSize * j, realSize * i));
                        collideBlocks.add(block);
                    } else if (mapStr[i].charAt(j) == '2') {
                        block = new WallBlock(realSize, realSize, wall2.getFxImage()
                                , new Vector2f(realSize * j, realSize * i));
                        collideBlocks.add(block);
                    } else if (mapStr[i].charAt(j) == '3') {
                        block = new BoxBlock(realSize, realSize, box.getFxImage()
                                , new Vector2f(realSize * j, realSize * i));
                        Random random = new Random();
                        int randNum = random.nextInt((5-1)+1) + 1;
                        if ( randNum == 1) {
                            item = new PowerUpItem(realSize, realSize, powerUpItem.getFxImage()
                                    , new Vector2f(realSize * j, realSize * i), player);
                            items.add(item);
                        }
                        if ( randNum == 2) {
                            item = new ExBombItem(realSize, realSize, exBombItem.getFxImage()
                                    , new Vector2f(realSize * j, realSize * i), player);
                            items.add(item);
                        }
                        if ( randNum == 3) {
                            item = new SpeedUpItem(realSize, realSize, speedItem.getFxImage()
                                    , new Vector2f(realSize * j, realSize * i), player);
                            items.add(item);
                        }
                       collideBlocks.add(block);
                    } else if (mapStr[i].charAt(j) == 'p') {
                        player = new Player(new SpriteSheet("src/main/resources/Sprite/Character_1.png"), new Vector2f(realSize * j, realSize * i), 52, scaling, this);
                    } else if (mapStr[i].charAt(j) == 'b') {
                        enemy = new Enemy(new SpriteSheet("src/main/resources/Sprite/Monkey.png"), new Vector2f(realSize * j, realSize * i), 52, scaling, this);
                        enemies.add(enemy);
                    } else if (mapStr[i].charAt(j) == 'a') {
                        enemy = new Enemy(new SpriteSheet("src/main/resources/Sprite/SnowApe.png"), new Vector2f(realSize * j, realSize * i), 52, scaling, this);
                        enemies.add(enemy);
                    }
                    block = new FloorBlock(realSize, realSize, floor.getFxImage()
                            , new Vector2f(realSize * j, realSize * i));
                    floorBlocks.add(block);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void update() {
        Iterator<Block> iterator = items.iterator();
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
        if (TileCollision.isCollidedWithFlames(player, flames)) {
            gameOver = true;
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (TileCollision.isCollidedWithFlames(enemies.get(i),flames)) {
                enemies.remove(enemies.get(i));
            }
        }
        if (!gameOver) {
            enemies.forEach(Enemy::update);
        }
        if (TileCollision.isColliedWithPortals(player, portals)) {
            System.out.println("Portal hit");
            PlayState.levelUp();
        }
        player.update();
        bombs.forEach(Bomb::update);
        flames.forEach(Flame::update);
    }

    public void render(GraphicsContext gc) {
        floorBlocks.forEach(g -> g.render(gc, realSize));
        items.forEach(g -> g.render(gc,realSize));
        collideBlocks.forEach(g -> g.render(gc, realSize));
        bombs.forEach(g -> g.render(gc));
        flames.forEach(g -> g.render(gc));
        portals.forEach(g -> g.render(gc, realSize));
        if (!gameOver) {
            enemies.forEach(g -> g.render(gc));
            player.render(gc);
        }
    }

    public void input(KeyHandler keyHandler) {
        player.input(keyHandler);
    }
}