package com.sen.toy.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sen on 30/04/2015.
 */
public class BoardTest {

    final int Height = 5;
    final int Width = 5;
    final int YTop = 4;
    final int YBottom = 0;
    final int XRight = 4;
    final int XLeft = 0;

    private Board board;

    @Before
    public void setup() {
        board = new Board(Width, Height);
    }

    @Test
    public void moveForward_FromTopEdge_ShouldStay() throws Exception {
        Position topEdge = new Position(Angle.NORTH, 1, YTop);
        Position newPosition = board.moveFrom(topEdge);
        assertThat("should stay when move from top edge",
            topEdge, equalTo(newPosition));
    }

    @Test
    public void moveForward_FromRightEdge_ShouldStay() throws Exception {
        Position rightEdge = new Position(Angle.EAST, XRight, 2);
        Position newPosition = board.moveFrom(rightEdge);
        assertThat("should stay when move from right edge",
            rightEdge, equalTo(newPosition));
    }

    @Test
    public void moveForward_FromBottomEdge_ShouldStay() throws Exception {
        Position bottomEdge = new Position(Angle.SOUTH, 3, YBottom);
        Position newPosition = board.moveFrom(bottomEdge);
        assertThat("should stay when move from bottom edge",
            bottomEdge, equalTo(newPosition));
    }


    @Test
    public void moveForward_FromLeftEdge_ShouldStay() throws Exception {
        Position leftEdge = new Position(Angle.WEST, XLeft, 2);
        Position newPosition = board.moveFrom(leftEdge);
        assertThat("should stay when move from left edge",
            leftEdge, equalTo(newPosition));
    }


    @Test
         public void moveForward_FacingNorth_ShouldMoveUpOneUnit() {
        Position current = new Position(Angle.NORTH, 1, 1);
        //when
        Position newPosition = board.moveFrom(current);

        // then
        assertThat(newPosition.getX(), equalTo(current.getX()));
       assertThat(newPosition.getY(), equalTo(current.getY() + 1));
        assertEquals(current.getAngle(), newPosition.getAngle());

    }

    @Test
    public void moveForward_FacingEast_ShouldMoveRightOneUnit() {
        Position current = new Position(Angle.EAST, 1, 1);

        Position newPosition = board.moveFrom(current);

        assertThat(newPosition.getX(), equalTo(current.getX() + 1));
        assertThat(newPosition.getY(), equalTo(current.getY()));
        assertEquals(current.getAngle(), newPosition.getAngle());

    }

    @Test
    public void moveForward_FacingSouth_ShouldMoveDownOneUnit() {
        Position current = new Position(Angle.SOUTH, 1, 1);

        Position newPosition = board.moveFrom(current);
        assertThat(newPosition.getX(), equalTo(current.getX()));
        assertThat(newPosition.getY(), equalTo(current.getY() - 1));
        assertEquals(current.getAngle(), newPosition.getAngle());

    }

    @Test
    public void moveForward_FacingWest_ShouldMoveLeftOneUnit() {
        Position current = new Position(Angle.WEST, 1, 1);

        Position newPosition = board.moveFrom(current);

        assertThat(newPosition.getX(), equalTo(current.getX() - 1));
        assertThat(newPosition.getY(), equalTo(current.getY()));
        assertEquals(current.getAngle(), newPosition.getAngle());

    }
}
