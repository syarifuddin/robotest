package com.sen.toy.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.joda.time.LocalDate;

public class Robot {

    private String name;

    private Board board;

    private Position position;

    public Robot(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public Position getPosition() {
        return position;
    }

    public Position place(Position position) {
        if (board.isValidPosition(position)){
          this.position = position;
        }
        return this.position;
    }

    public void turnLeft(){
        if(isOnBoard()){
            this.position.turnLeft();
        }
    }

    public boolean isOnBoard() {
        return this.position != null;
    }

    public void turnRight(){
        if(isOnBoard()){
            this.position.turnRight();
        }
    }

    public void moveForward(){
        if (isOnBoard()){
            this.position = board.moveFrom(position);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Robot robot = (Robot) o;

        return name.equalsIgnoreCase(robot.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Robot{" +
            "name='" + name + '\'' +
            ", position=" + position +
            '}';
    }
}
