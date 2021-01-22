package Logic.FootprintSpaceTime;

import Logic.GlobalVariable;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    @Test
    void intersectionLine_itself() {
        Line line = new LineClass(new PointClass(0, -4), new PointClass(0, 4));
        assertTrue(line.intersectionLine(line));
    }

    @Test
    void intersectionLine_cross() {
        Line line = new LineClass(new PointClass(0, -4), new PointClass(0, 4));
        Line line2 = new LineClass(new PointClass(-4, 0), new PointClass(4, 0));
        assertTrue(line.intersectionLine(line2));
    }

    @Test
    void intersectionLine_crossRightToLeft() {
        Line line = new LineClass(new PointClass(0, -4), new PointClass(0, 4));
        Line line2 = new LineClass(new PointClass(4, 0), new PointClass(-4, 0));
        assertTrue(line.intersectionLine(line2));
    }

    @Test
    void intersectionLine_sharedOnlyOnePoint() {
        Line line = new LineClass(new PointClass(0, -4), new PointClass(0, 4));
        Line line2 = new LineClass(new PointClass(0, -4), new PointClass(-100, -100));
        assertTrue(line.intersectionLine(line2));
    }

    @Test
    void intersectionLine_positiveNumber() {
        Line line = new LineClass(new PointClass(0, 0), new PointClass(10, 10));
        Line line2 = new LineClass(new PointClass(2, 2), new PointClass(16, 4));
        assertTrue(line.intersectionLine(line2));
    }

    @Test
    void intersectionLine_positiveNumberCopy() {
        Line line = new LineClass(new PointClass(0, 0), new PointClass(4, 4));
        Line line2 = new LineClass(new PointClass(0, 0), new PointClass(4, 4));
        assertTrue(line.intersectionLine(line2));
    }

    @Test
    void intersectionLine_withoutZerosCopy() {
        Line line = new LineClass(new PointClass(1, 1), new PointClass(4, 4));
        Line line2 = new LineClass(new PointClass(1, 1), new PointClass(4, 4));
        assertTrue(line.intersectionLine(line2));
    }

    @Test
    void intersectionLine_withoutZeros() {
        Line line = new LineClass(new PointClass(1, 1), new PointClass(4, 4));
        Line line2 = new LineClass(new PointClass(4, 1), new PointClass(1, 4));
        assertTrue(line.intersectionLine(line2));
    }

    @Test
    void intersectionLine_parallel() {
        Line line = new LineClass(new PointClass(0, -4), new PointClass(0, 4));
        Line line2 = new LineClass(new PointClass(-4, -4), new PointClass(-4, 4));
        assertFalse(line.intersectionLine(line2));
    }

    @Test
    void length_0() {
        Line line = new LineClass(new PointClass(0, 0), new PointClass(0, 0));
        assertEquals(0, line.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_x4() {
        Line line = new LineClass(new PointClass(0, 0), new PointClass(4, 0));
        assertEquals(4, line.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_y4() {
        Line line = new LineClass(new PointClass(0, 0), new PointClass(0, 4));
        assertEquals(4, line.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_sum5() {
        Line line = new LineClass(new PointClass(3, 0), new PointClass(0, 4));
        assertEquals(5, line.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_minusSum5() {
        Line line = new LineClass(new PointClass(-3, 0), new PointClass(0, -4));
        assertEquals(5, line.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void getStart() {
        Point start = new PointClass(0, 0);
        Point end = new PointClass(1, 1);
        Line line = new LineClass(start, end);

        assertEquals(start, line.getStart());
    }

    @Test
    void getEnd() {
        Point start = new PointClass(0, 0);
        Point end = new PointClass(1, 1);
        Line line = new LineClass(start, end);

        assertEquals(end, line.getEnd());
    }
}