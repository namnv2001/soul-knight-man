package master.soulknight.Tiles;

import javafx.scene.canvas.GraphicsContext;
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
    public static final Sprite tileSheet = new Sprite("src/main/resources/Sprite/ss.png");
    protected ArrayList<Block> blocks = new ArrayList<>();
    protected int TILE_SIZE = 31;
    protected int level;
    protected int rows;
    protected int columns;
    SpriteSheet grass = new SpriteSheet(2, 0, tileSheet);
    SpriteSheet wall = new SpriteSheet(1, 0, tileSheet);
    SpriteSheet box = new SpriteSheet(0, 0, tileSheet);

    public TileManager(String path, double scaling) {
        TILE_SIZE *= scaling;
        readMap(path);
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
                    } else if (mapStr[i].charAt(j) == '*') {
                        block = new BoxBlock(TILE_SIZE, TILE_SIZE, box.getFxImage()
                                , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
                    } else {
                        block = new FloorBlock(TILE_SIZE, TILE_SIZE, grass.getFxImage()
                                , new Vector2f(TILE_SIZE * j, TILE_SIZE * i));
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
    }
}
