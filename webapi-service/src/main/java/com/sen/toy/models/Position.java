package com.sen.toy.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sen on 30/04/2015.
 */
public class Position {
    private Angle angle;
    private int x;
    private int y;

    public Position() {
    }

    public Position(Angle angle, int x, int y) {
        this.angle = angle;
        this.x = x;
        this.y = y;
    }


    public Angle getAngle() {
        return angle;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }

    @JsonProperty("x_pos")
    public int getX() {
        return x;
    }

    @JsonProperty("x_pos")
    public void setX(int x) {
        this.x = x;
    }


    @JsonProperty("y_pos")
    public int getY() {
        return y;
    }


    @JsonProperty("y_pos")
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
            "angle=" + angle +
            ", x_pos=" + x +
            ", y_pos=" + y +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        if (y != position.y) return false;
        return angle == position.angle;

    }

    @Override
    public int hashCode() {
        int result = angle.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    public void turnLeft() {
        this.angle = this.angle.turnLeft();
    }

    public void turnRight() {
        this.angle = this.angle.turnRight();
    }
}
