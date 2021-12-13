package master.soulknight.Graphics;

public class Animation {

    private Sprite[] frames;
    private int[] states;
    private int currentFrame;
    private int numFrames;

    private int count;
    private int delay;

    private int timePlayed;

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

    public void update() {
        if (delay == -1) {
            return;
        }
        count++;
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
