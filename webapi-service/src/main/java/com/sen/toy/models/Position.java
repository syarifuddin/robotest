package com.sen.toy.models;

/**
 * Created by sen on 30/04/2015.
 */
public class Position {
    public enum Angle { NORTH, SOUTH, EAST, WEST };
    private Angle angle;
    private int x_pos;
    private int y_pos;

    public Angle getAngle() {
        return angle;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    @Override
    public String toString() {
        return "Position{" +
            "angle=" + angle +
            ", x_pos=" + x_pos +
            ", y_pos=" + y_pos +
            '}';
    }
}
