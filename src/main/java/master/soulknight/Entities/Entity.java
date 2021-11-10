package master.soulknight.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import master.soulknight.Graphics.Animation;
import master.soulknight.Graphics.Sprite;
import master.soulknight.Graphics.SpriteSheet;
import master.soulknight.Util.Vector2f;

public abstract class Entity {

    private final int IDLE = 6;
    private final int BOOM = 5;
    private final int FALLEN = 4;
    private final int UP = 3;
    private final int DOWN = 2;
    private final int RIGHT = 0;
    private final int LEFT = 1;
    protected int currentAnimation;
    protected int currentDirection = RIGHT;

    protected Animation ani;
    protected SpriteSheet sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean fallen;
    protected boolean placeBoom;

    protected int x;
    protected int y;

    protected float dx;
    protected float dy;

    protected float speed = 4f;
    protected float acc = 3f;
    protected float deacc = 0.3f;


    protected Image img;

    public void setAnimation(int i, Sprite[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(i,frames);
        ani.setDelay(delay);

    }

    public Entity(SpriteSheet sprite, Vector2f origin, int size) {
        this.sprite = sprite;
        pos = origin;
        this.size = size;

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10 );
    }

    public void animated() {
//        if(placeBoom) {
//            if(currentAnimation < 5) {
//                setAnimation(currentAnimation + BOOM, sprite.getSpriteArray(currentAnimation + BOOM), 0);
//            }
//        } else if(up) {
//            if((currentAnimation != UP || ani.getDelay() == -1)) {
//                setAnimation(UP, sprite.getSpriteArray(UP), 5);
//            }
//        }  else if(down) {
//            if((currentAnimation != DOWN || ani.getDelay() == -1)) {
//                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
//            }
//        }  else if(right) {
//            if((currentAnimation != RIGHT || ani.getDelay() == -1)) {
//                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
//            }
//        }  else if(left) {
//            if((currentAnimation != LEFT || ani.getDelay() == -1)) {
//                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
//            }
//        }  else if(fallen) {
//            if((currentAnimation != FALLEN || ani.getDelay() == -1)) {
//                setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 5);
//            }
//        }
        if(up) {
            if(currentAnimation != UP || ani.getDelay() == -1) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        }
        else if(down) {
            if(currentAnimation != DOWN || ani.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        }
        else if(left) {
            if(currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }
        else if(right) {
            if(currentAnimation != RIGHT || ani.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        } else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    public void move() {
        if(up) {
            currentDirection = UP;
            dy -= acc;
            if(dy < -speed) {
                dy = -speed;
            }
        } else {
            if(dy < 0) {
                dy += deacc;
                if(dy > 0) {
                    dy = 0;
                }
            }
        }

        if(down) {
            currentDirection = DOWN;
            dy += acc;
            if(dy > speed) {
                dy = speed;
            }
        } else {
            if(dy > 0) {
                dy -= deacc;
                if(dy < 0) {
                    dy = 0;
                }
            }
        }

        if(left) {
            currentDirection = LEFT;
            dx -= acc;
            if(dx < -speed) {
                dx = -speed;
            }
        } else {
            if(dx < 0) {
                dx += deacc;
                if(dx > 0) {
                    dx = 0;
                }
            }
        }

        if(right) {
            currentDirection = RIGHT;
            dx += acc;
            if(dx > speed) {
                dx = speed;
            }
        } else {
            if(dx > 0) {
                dx -= deacc;
                if(dx < 0) {
                    dx = 0;
                }
            }
        }
    }


    public void render(GraphicsContext gc) {
        gc.drawImage(img,x,y);
    }

    public void update() {
        animated();
        ani.update();
        move();
    }

}
