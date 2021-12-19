package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointClassTest {

    @Test
    void testHashCode_0() {
        Point point1 = new PointClass(0, 0);
        Point point2 = new PointClass(0, 0);

        assertEquals(point1.hashCode(), point2.hashCode());
    }

    @Test
    void testHashCode_positive() {
        Point point1 = new PointClass(1, 1);
        Point point2 = new PointClass(1, 1);

        assertEquals(point1.hashCode(), point2.hashCode());
    }

    @Test
    void testHashCode_negative() {
        Point point1 = new PointClass(-1, -1);
        Point point2 = new PointClass(-1, -1);

        assertEquals(point1.hashCode(), point2.hashCode());
    }
    @Test
    void testHashCode_negativePositive() {
        Point point1 = new PointClass(1, -1);
        Point point2 = new PointClass(1, -1);

        assertEquals(point1.hashCode(), point2.hashCode());
    }

    @Test
    void testHashCode_nonEquals() {
        Point point1 = new PointClass(-1, 1);
        Point point2 = new PointClass(1, 1);

        assertNotEquals(point1.hashCode(), point2.hashCode());
    }


    @Test
    void testHashCode_EqualsObjectAndEqualsHashcode() {
        Point point1 = new PointClass(1, 1);

        Point point2 = new PointClass(1, 1);

        assertEquals(point1.hashCode(), point2.hashCode());
        assertEquals(point1, point2);
    }

    @Test
    void testHashCode_EqualsObjectAndEqualsHashcodeAfterMicroChanging() {
        Point vector = new PointClass(13, 13);
        Point point1 = new PointClass(1, 1);
        point1.deposeOn(vector);
        point1.deposeOn(vector.getInversion());

        Point point2 = new PointClass(1, 1);
        point2.deposeOn(vector);
        point2.deposeOn(vector.getInversion());

        assertEquals(point1.hashCode(), point2.hashCode());
        assertEquals(point1, point2);
    }


    @Test
    void testEquals_0() {
        Point point1 = new PointClass(0, 0);
        Point point2 = new PointClass(0, 0);

        assertEquals(point1, point2);
    }

    @Test
    void testEquals_positive() {
        Point point1 = new PointClass(1, 1);
        Point point2 = new PointClass(1, 1);

        assertEquals(point1, point2);
    }

    @Test
    void testEquals_negative() {
        Point point1 = new PointClass(-1, -1);
        Point point2 = new PointClass(-1, -1);

        assertEquals(point1, point2);
    }

    @Test
    void testEquals_negativePositive() {
        Point point1 = new PointClass(-1, 1);
        Point point2 = new PointClass(-1, 1);

        assertEquals(point1, point2);
    }

    @Test
    void testEquals_nonEquals() {
        Point point1 = new PointClass(-1, 1);
        Point point2 = new PointClass(1, 1);

        assertNotEquals(point1, point2);
    }
}