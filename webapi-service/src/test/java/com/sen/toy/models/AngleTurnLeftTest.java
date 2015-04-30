package com.sen.toy.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sen on 30/04/2015.
 */
@RunWith(value = Parameterized.class)
public class AngleTurnLeftTest {

    private Angle origin;

    private Angle expectedAngle;

    public AngleTurnLeftTest(Angle initialDirection, Angle expectedDirection) {
        this.origin = initialDirection;
        this.expectedAngle = expectedDirection;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
            {Angle.NORTH, Angle.WEST },
            {Angle.WEST, Angle.SOUTH },
            {Angle.SOUTH, Angle.EAST },
            {Angle.EAST, Angle.NORTH },
        };
        return Arrays.asList(data);
    }

    @Test
    public void testTurnLeft() throws Exception {
        Angle leftSide = origin.turnLeft();
        assertEquals(expectedAngle, leftSide);

        assertThat(
            "When facing " + origin + " then turning LEFT should cause robot to face " + expectedAngle,
            expectedAngle, equalTo(leftSide));
    }
}
