package Logic.FootprintSpaceTime;

import Logic.GlobalVariable;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class LineCutTest {

    @Test
    void intersectionLine_itself() {
        LineCut lineCut = new LineCutClass(new PointClass(0, -4), new PointClass(0, 4));
        assertTrue(lineCut.intersectionLine(lineCut));
    }

    @Test
    void intersectionLine_cross() {
        LineCut lineCut = new LineCutClass(new PointClass(0, -4), new PointClass(0, 4));
        LineCut lineCut2 = new LineCutClass(new PointClass(-4, 0), new PointClass(4, 0));
        assertTrue(lineCut.intersectionLine(lineCut2));
    }

    @Test
    void intersectionLine_crossRightToLeft() {
        LineCut lineCut = new LineCutClass(new PointClass(0, -4), new PointClass(0, 4));
        LineCut lineCut2 = new LineCutClass(new PointClass(4, 0), new PointClass(-4, 0));
        assertTrue(lineCut.intersectionLine(lineCut2));
    }

    @Test
    void intersectionLine_sharedOnlyOnePoint() {
        LineCut lineCut = new LineCutClass(new PointClass(0, -4), new PointClass(0, 4));
        LineCut lineCut2 = new LineCutClass(new PointClass(0, -4), new PointClass(-100, -100));
        assertTrue(lineCut.intersectionLine(lineCut2));
    }

    @Test
    void intersectionLine_positiveNumber() {
        LineCut lineCut = new LineCutClass(new PointClass(0, 0), new PointClass(10, 10));
        LineCut lineCut2 = new LineCutClass(new PointClass(2, 2), new PointClass(16, 4));
        assertTrue(lineCut.intersectionLine(lineCut2));
    }

    @Test
    void intersectionLine_positiveNumberCopy() {
        LineCut lineCut = new LineCutClass(new PointClass(0, 0), new PointClass(4, 4));
        LineCut lineCut2 = new LineCutClass(new PointClass(0, 0), new PointClass(4, 4));
        assertTrue(lineCut.intersectionLine(lineCut2));
    }

    @Test
    void intersectionLine_withoutZerosCopy() {
        LineCut lineCut = new LineCutClass(new PointClass(1, 1), new PointClass(4, 4));
        LineCut lineCut2 = new LineCutClass(new PointClass(1, 1), new PointClass(4, 4));
        assertTrue(lineCut.intersectionLine(lineCut2));
    }

    @Test
    void intersectionLine_withoutZeros() {
        LineCut lineCut = new LineCutClass(new PointClass(1, 1), new PointClass(4, 4));
        LineCut lineCut2 = new LineCutClass(new PointClass(4, 1), new PointClass(1, 4));
        assertTrue(lineCut.intersectionLine(lineCut2));
    }

    @Test
    void intersectionLine_parallel() {
        LineCut lineCut = new LineCutClass(new PointClass(0, -4), new PointClass(0, 4));
        LineCut lineCut2 = new LineCutClass(new PointClass(-4, -4), new PointClass(-4, 4));
        assertFalse(lineCut.intersectionLine(lineCut2));
    }

    @Test
    void length_0() {
        LineCut lineCut = new LineCutClass(new PointClass(0, 0), new PointClass(0, 0));
        assertEquals(0, lineCut.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_x4() {
        LineCut lineCut = new LineCutClass(new PointClass(0, 0), new PointClass(4, 0));
        assertEquals(4, lineCut.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_y4() {
        LineCut lineCut = new LineCutClass(new PointClass(0, 0), new PointClass(0, 4));
        assertEquals(4, lineCut.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_sum5() {
        LineCut lineCut = new LineCutClass(new PointClass(3, 0), new PointClass(0, 4));
        assertEquals(5, lineCut.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_minusSum5() {
        LineCut lineCut = new LineCutClass(new PointClass(-3, 0), new PointClass(0, -4));
        assertEquals(5, lineCut.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void getStart() {
        Point start = new PointClass(0, 0);
        Point end = new PointClass(1, 1);
        LineCut lineCut = new LineCutClass(start, end);

        assertEquals(start, lineCut.getStart());
    }

    @Test
    void getEnd() {
        Point start = new PointClass(0, 0);
        Point end = new PointClass(1, 1);
        LineCut lineCut = new LineCutClass(start, end);

        assertEquals(end, lineCut.getEnd());
    }
}