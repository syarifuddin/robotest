package com.sen.toy.models;

/**
 * Created by sen on 30/04/2015.
 */
public class Translation {
    private int translateX;
    private int translateY;

    public Translation(int translateX, int translateY) {
        this.translateX = translateX;
        this.translateY = translateY;
    }

    public Position translate(Position origin){
        return new Position(origin.getAngle(),
            origin.getX() + translateX,
            origin.getY() + translateY);
    }
}
