package master.soulknight.Util;

public class Vector2f {

    public static float worldX;
    public static float worldY;
    public float x;
    public float y;

    public Vector2f() {
        x = 0;
        y = 0;
    }

    public Vector2f(Vector2f pos) {
        new Vector2f(pos.x, pos.y);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static void setWorldVar(float x, float y) {
        worldX = x;
        worldY = y;
    }

    public Vector2f getWorldVar() {
        return new Vector2f(x - worldX, y - worldY);
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
