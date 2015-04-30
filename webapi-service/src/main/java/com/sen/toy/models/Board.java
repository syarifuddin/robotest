package com.sen.toy.models;

import java.util.HashMap;

/**
 * Created by sen on 30/04/2015.
 */
public class Board {
    private final int width;
    private final int height;
    private static final HashMap<Angle, Translation> Translations = new HashMap<>();

    static
    {
        Translations.put(Angle.NORTH, new Translation(0, 1));
        Translations.put(Angle.WEST, new Translation(-1, 0));
        Translations.put(Angle.SOUTH, new Translation(0, -1));
        Translations.put(Angle.EAST, new Translation(1, 0));
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private boolean isValidAxisY(int y) {
        return y >= 0 && y < height;
    }

    private boolean isValidAxisX(int x) {
        return x >= 0 && x < width;
    }

    public boolean isValidPosition(Position position){
        return isValidAxisX(position.getX()) && isValidAxisY(position.getY());
    }

    public Position moveFrom(Position currentPos){
        Translation translation = Translations.get(currentPos.getAngle());
        Position newPosition = translation.translate(currentPos);
        // don't move if the attempted movement is not valid
        if (!isValidPosition(newPosition)) return currentPos;

        return newPosition;
    }

    @Override
    public String toString() {
        return "Board{" +
            "width=" + width +
            ", height=" + height +
            '}';
    }
}
