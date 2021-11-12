package master.soulknight.Util;

import master.soulknight.Entities.Entity;

public class AABB {

    private Vector2f pos;
    private float xOffset = 0;
    private float yOffset = 0;
    private float width;
    private float height;

    private float radius;
    private int size;
    private Entity entity;

    public AABB(Vector2f pos, float width, float height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public Vector2f getPos() {
        return pos;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRadius() {
        return radius;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
