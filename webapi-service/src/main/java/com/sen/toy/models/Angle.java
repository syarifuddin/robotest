package com.sen.toy.models;

import java.util.Arrays;

/**
 * Created by sen on 30/04/2015.
 */
public enum Angle {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    private static Angle[] AntiClockwise = new Angle[] {NORTH, WEST, SOUTH, EAST};

    public Angle turnLeft(){
        int currentIndex = Arrays.asList(AntiClockwise).indexOf(this);
        int leftSide = currentIndex + 1 >= AntiClockwise.length ? 0 : currentIndex + 1;
        return AntiClockwise[leftSide];
    }

    public Angle turnRight() {
        int currentIndex = Arrays.asList(AntiClockwise).indexOf(this);
        if (currentIndex == 0) return AntiClockwise[AntiClockwise.length - 1];
        return AntiClockwise[currentIndex - 1];
    }
}

