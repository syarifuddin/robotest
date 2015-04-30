package com.sen.toy.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by sen on 30/04/2015.
 */
@RunWith(value = Parameterized.class)
public class AngleTurnRightTest {

    private Angle origin;

    private Angle expectedAngle;

    public AngleTurnRightTest(Angle initialDirection, Angle expectedDirection) {
        this.origin = initialDirection;
        this.expectedAngle = expectedDirection;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
            {Angle.NORTH, Angle.EAST },
            {Angle.WEST, Angle.NORTH },
            {Angle.SOUTH, Angle.WEST },
            {Angle.EAST, Angle.SOUTH },
        };
        return Arrays.asList(data);
    }

    @Test
    public void testTurnRight() throws Exception {
        Angle rightSide = origin.turnRight();
        assertEquals(expectedAngle, rightSide);

        assertThat(
            "When facing " + origin + " then turning right should cause robot to face " + expectedAngle,
            expectedAngle, equalTo(rightSide));
    }
}
