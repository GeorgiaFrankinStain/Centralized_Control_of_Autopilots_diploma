package Logic.FootprintSpaceTime;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    void getApproximation_radius() {
        Round round1 = new RoundClass(new PointClass(0, 0), 4);
        Round round2 = new RoundClass(new PointClass(0, 0), 2);

        Round actual = round1.getApproximation(0, round2, 10, 5);
        Round expected = new RoundClass(new PointClass(0, 0), 3);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_center() {
        Round round1 = new RoundClass(new PointClass(0, 0), 4);
        Round round2 = new RoundClass(new PointClass(10, 10), 2);

        Round actual = round1.getApproximation(0, round2, 10, 5);
        Round expected = new RoundClass(new PointClass(5, 5), 3);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_centerNegative() {
        Round round1 = new RoundClass(new PointClass(-2, -2), 4);
        Round round2 = new RoundClass(new PointClass(-10, -10), 2);

        Round actual = round1.getApproximation(0, round2, 10, 5);
        Round expected = new RoundClass(new PointClass(-6, -6), 3);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_centerNegativePositive() {
        Round round1 = new RoundClass(new PointClass(10, 10), 4);
        Round round2 = new RoundClass(new PointClass(-10, -10), 2);

        Round actual = round1.getApproximation(0, round2, 10, 5);
        Round expected = new RoundClass(new PointClass(0, 0), 3);
        assertEquals(expected, actual);
    }

    @Test
    void getApproximation_centerCopy() {
        Round round1 = new RoundClass(new PointClass(10, 10), 4);
        Round round2 = new RoundClass(new PointClass(10, 10), 4);

        Round actual = round1.getApproximation(0, round2, 10, 5);
        Round expected = new RoundClass(new PointClass(10, 10), 4);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_centerYourself() {
        Round round1 = new RoundClass(new PointClass(10, 10), 4);

        Round actual = round1.getApproximation(0, round1, 10, 5);
        Round expected = new RoundClass(new PointClass(10, 10), 4);
        assertEquals(expected, actual);
    }

}