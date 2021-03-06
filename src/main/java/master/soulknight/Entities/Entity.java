package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import master.soulknight.Graphics.Animation;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Tiles.TileManager;
import master.soulknight.Util.Vector2f;

public abstract class Entity {

    private static double SCALING;
    private final int UP = 3;
    private final int DOWN = 2;
    private final int RIGHT = 0;
    private final int LEFT = 1;

    public float speed = 2f;

    protected int currentAnimation;
    protected int currentDirection = RIGHT;

    protected TileManager tm;
    protected Animation ani;
    protected SpriteSheet sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;

    protected float dx;
    protected float dy;

    protected float acc = 3f;
    protected float deacc = 0.4f;

    private int directionVar;

    public Entity(SpriteSheet sprite, Vector2f origin, int size, double SCALING) {
        this.sprite = sprite;
        pos = origin;
        this.size = size;
        Entity.SCALING = SCALING;

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public Entity(SpriteSheet sprite, Vector2f origin, int size, double SCALING, TileManager tm) {
        this.tm = tm;
        this.sprite = sprite;
        pos = origin;
        this.size = size;
        Entity.SCALING = SCALING;

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public static double getSCALING() {
        return SCALING;
    }

    public void setSprite(SpriteSheet sprite) {
        System.out.println("Sprite");
        this.sprite = sprite;
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public int getX() {
        return (int) pos.x;
    }

    public int getY() {
        return (int) pos.y;
    }

    public void setAnimation(int i, Sprite[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(i, frames);
        ani.setDelay(delay);
    }

    public void animated() {
        if (up) {
            if (currentAnimation == RIGHT || ani.getDelay() == -1) {
                setAnimation(UP, sprite.getSpriteArray(RIGHT), 4);
                directionVar = RIGHT;
            } else if (currentAnimation == LEFT || ani.getDelay() == -1) {
                setAnimation(UP, sprite.getSpriteArray(LEFT), 4);
                directionVar = LEFT;
            }
        } else if (down) {
            if (currentAnimation == RIGHT || ani.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(RIGHT), 4);
                directionVar = RIGHT;
            } else if (currentAnimation == LEFT || ani.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(LEFT), 4);
                directionVar = LEFT;
            }
        } else if (left) {
            if (currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 4);
                directionVar = LEFT;
            }
        } else if (right) {
            if (currentAnimation != RIGHT || ani.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 4);
                directionVar = RIGHT;
            }
        } else {
            setAnimation(currentAnimation, sprite.getSpriteArray(directionVar), 4);
        }
    }

    public void move() {
        if (up) {
            currentDirection = UP;
            dy -= acc;
            if (dy < -speed) {
                dy = -speed;
            }
        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }

        if (down) {
            currentDirection = DOWN;
            dy += acc;
            if (dy > speed) {
                dy = speed;
            }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }

        if (left) {
            currentDirection = LEFT;
            dx -= acc;
            if (dx < -speed) {
                dx = -speed;
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }

        if (right) {
            currentDirection = RIGHT;
            dx += acc;
            if (dx > speed) {
                dx = speed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    public abstract void render(GraphicsContext gc);

    public int getSize() {
        return size;
    }

    public Vector2f getPos() {
        return pos;
    }

    public void update() {
        animated();
    }
}