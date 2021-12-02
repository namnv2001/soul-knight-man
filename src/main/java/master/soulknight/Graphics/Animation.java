package master.soulknight.Graphics;

public class Animation {

    private Sprite[] frames;
    private int[] states;
    private int currentFrame;
    private int numFrames;

    private int count;
    private int delay;

    private int timePlayed;

    public Animation(Sprite[] frames) {
        setFrames(0, frames);
        timePlayed = 0;
        states = new int[10];
    }

    public Animation() {
        timePlayed = 0;
        states = new int[10];
    }

    public void setFrames(int state, Sprite[] frames) {
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        timePlayed = 0;
        delay = 2;
        if (states[state] == 0) {
            numFrames = frames.length;
        } else {
            numFrames = states[state];
        }
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setNumFrames(int numFrames) {
        this.numFrames = numFrames;
    }

    public void update() {
        if (delay == -1) {
            return;
        }
        count++;
        //System.out.println(delay);
        if (count == delay) {
            currentFrame++;
            count = 0;
        }
        if (currentFrame == numFrames) {
            currentFrame = 0;
            timePlayed++;
        }
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Sprite getImage() {
        return frames[currentFrame];
    }
}
