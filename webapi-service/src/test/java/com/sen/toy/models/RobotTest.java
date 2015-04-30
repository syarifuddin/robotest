package com.sen.toy.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sen on 30/04/2015.
 */
public class RobotTest {
    final int Height = 5;
    final int Width = 5;

    private Board Board = new Board(Width, Height);
    private Robot robot;
    private Position originNorth = new Position(Angle.NORTH, 0, 0);

    @Before
    public void setup() {
       robot = new Robot("c3po", Board);
    }

    @Test
    public void placeRobot_ShouldHavePosition() throws Exception {
        Position placement = new Position(Angle.NORTH, 1, 1);
        robot.place(placement);
        assertThat(robot.getPosition(), equalTo(placement));
    }

    @Test
    public void placeRobot_OutsideOfBoard_IsNotAllowed() throws Exception {
      Position placement = new Position(Angle.NORTH, Width + 1, 1);
      robot.place(placement);
      assertNull(robot.getPosition());
    }

    @Test
    public void testTurnLeft() throws Exception {
        robot.place(originNorth);
        robot.turnLeft();

        assertThat(robot.getPosition().getAngle(), equalTo(Angle.WEST));
    }

    @Test
    public void testTurnRight() throws Exception {
        robot.place(originNorth);
        robot.turnRight();

        assertThat(robot.getPosition().getAngle(), equalTo(Angle.EAST));
    }

    @Test
    public void robotNotOnBoard_ShouldNotBeAbleTo_TurnRight() throws Exception {
        robot.turnRight();
        assertNull(robot.getPosition());
    }

    @Test
    public void robotNotOnBoard_ShouldNotBeAbleTo_TurnLeft() throws Exception {
        robot.turnLeft();
        assertNull(robot.getPosition());
    }

    @Test
    public void robotNotOnBoard_ShouldNotBeAbleTo_Move() throws Exception {
        robot.moveForward();
        assertNull(robot.getPosition());
    }

    @Test
    public void testMoveForward() throws Exception {
        robot.place(originNorth);
        robot.moveForward();
        assertThat(robot.getPosition().getY(), equalTo(originNorth.getY()+1));
    }
}
