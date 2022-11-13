package com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime;

import com.alamutra.CCoAWeb.Core.Logic.GlobalVariable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineCutClassTest {

    @Test
    void intersectionLine_itself() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(0, -4), new PointCCoAClass(0, 4));
        assertTrue(lineCut.intersectionLineCut(lineCut));
    }

    @Test
    void intersectionLine_cross() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(0, -4), new PointCCoAClass(0, 4));
        LineCut lineCut2 = new LineCutClass(new PointCCoAClass(-4, 0), new PointCCoAClass(4, 0));
        assertTrue(lineCut.intersectionLineCut(lineCut2));
    }

    @Test
    void intersectionLine_crossRightToLeft() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(0, -4), new PointCCoAClass(0, 4));
        LineCut lineCut2 = new LineCutClass(new PointCCoAClass(4, 0), new PointCCoAClass(-4, 0));
        assertTrue(lineCut.intersectionLineCut(lineCut2));
    }

    @Test
    void intersectionLine_sharedOnlyOnePoint() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(0, -4), new PointCCoAClass(0, 4));
        LineCut lineCut2 = new LineCutClass(new PointCCoAClass(0, -4), new PointCCoAClass(-100, -100));
        assertTrue(lineCut.intersectionLineCut(lineCut2));
    }

    @Test
    void intersectionLine_positiveNumber() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(0, 0), new PointCCoAClass(10, 10));
        LineCut lineCut2 = new LineCutClass(new PointCCoAClass(2, 2), new PointCCoAClass(16, 4));
        assertTrue(lineCut.intersectionLineCut(lineCut2));
    }

    @Test
    void intersectionLine_positiveNumberCopy() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(0, 0), new PointCCoAClass(4, 4));
        LineCut lineCut2 = new LineCutClass(new PointCCoAClass(0, 0), new PointCCoAClass(4, 4));
        assertTrue(lineCut.intersectionLineCut(lineCut2));
    }

    @Test
    void intersectionLine_withoutZerosCopy() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(1, 1), new PointCCoAClass(4, 4));
        LineCut lineCut2 = new LineCutClass(new PointCCoAClass(1, 1), new PointCCoAClass(4, 4));
        assertTrue(lineCut.intersectionLineCut(lineCut2));
    }

    @Test
    void intersectionLine_withoutZeros() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(1, 1), new PointCCoAClass(4, 4));
        LineCut lineCut2 = new LineCutClass(new PointCCoAClass(4, 1), new PointCCoAClass(1, 4));
        assertTrue(lineCut.intersectionLineCut(lineCut2));
    }

    @Test
    void intersectionLine_parallel() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(0, -4), new PointCCoAClass(0, 4));
        LineCut lineCut2 = new LineCutClass(new PointCCoAClass(-4, -4), new PointCCoAClass(-4, 4));
        assertFalse(lineCut.intersectionLineCut(lineCut2));
    }

    @Test
    void length_0() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(0, 0), new PointCCoAClass(0, 0));
        assertEquals(0, lineCut.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_x4() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(0, 0), new PointCCoAClass(4, 0));
        assertEquals(4, lineCut.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_y4() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(0, 0), new PointCCoAClass(0, 4));
        assertEquals(4, lineCut.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_sum5() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(3, 0), new PointCCoAClass(0, 4));
        assertEquals(5, lineCut.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void length_minusSum5() {
        LineCut lineCut = new LineCutClass(new PointCCoAClass(-3, 0), new PointCCoAClass(0, -4));
        assertEquals(5, lineCut.length(), GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @Test
    void getStart() {
        PointCCoA start = new PointCCoAClass(0, 0);
        PointCCoA end = new PointCCoAClass(1, 1);
        LineCut lineCut = new LineCutClass(start, end);

        assertEquals(start, lineCut.getStart());
    }

    @Test
    void getEnd() {
        PointCCoA start = new PointCCoAClass(0, 0);
        PointCCoA end = new PointCCoAClass(1, 1);
        LineCut lineCut = new LineCutClass(start, end);

        assertEquals(end, lineCut.getEnd());
    }
}