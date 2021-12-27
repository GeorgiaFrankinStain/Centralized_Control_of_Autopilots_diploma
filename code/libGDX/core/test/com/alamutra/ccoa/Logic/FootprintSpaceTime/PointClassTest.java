package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Logic.GlobalVariable;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

class PointClassTest {
    double standartDelta = GlobalVariable.DOUBLE_COMPARISON_ACCURACY;

    @Test
    public void getAngleRelative_1_0() {
        Point origin = new PointClass(0, 0);
        Point point = new PointClass(1, 0);

        double actual = point.getAngleRotareRelative(origin);
        assertEquals(0, actual, standartDelta);
    }

    @Test
    public void getAngleRelative_m1_0() {
        Point origin = new PointClass(0, 0);
        Point point = new PointClass(-1, 0);

        double actual = point.getAngleRotareRelative(origin);
        assertEquals(PI, actual, standartDelta);
    }

    @Test
    public void getAngleRelative_m1_m1() {
        Point origin = new PointClass(0, 0);
        Point point = new PointClass(-1, -1);

        double expected = -0.75 * PI;
        double actual = point.getAngleRotareRelative(origin);
        assertEquals(expected, actual, standartDelta);
    }

    @Test
    public void getAngleRelative_1_m1() {
        Point origin = new PointClass(0, 0);
        Point point = new PointClass(1, -1);

        double actual = point.getAngleRotareRelative(origin);
        assertEquals(-0.25 * PI, actual, standartDelta);
    }

    @Test
    public void getAngleRelative_1_1() {
        Point origin = new PointClass(0, 0);
        Point point = new PointClass(1, 1);

        double actual = point.getAngleRotareRelative(origin);
        assertEquals(0.25 * PI, actual, standartDelta);
    }

    @Test
    public void getRotareRelative_mPId2() {
        Point point = new PointClass(0, 10);
        Point origin = new PointClass(0, 0);

        Point expected = new PointClass(10, 0);
        Point actual = point.getRotateRelative(origin, -(PI / 2));

        assertEquals(expected, actual);
    }

    @Test
    public void getRotareRelative_bigPoint() {
        Point point = new PointClass(0, 100);
        Point origin = new PointClass(0, 0);

        Point expected = new PointClass(100, 0);
        Point actual = point.getRotateRelative(origin, -(PI / 2));

        assertEquals(expected, actual);
    }

    @Test
    public void getRotareRelative_firstQuarter_quarterTurn() {
        Point point = new PointClass(20, 20);
        Point origin = new PointClass(0, 0);

        Point expected = new PointClass(-20, 20);
        Point actual = point.getRotateRelative(origin, (PI / 2));

        assertEquals(expected, actual);
    }

    @Test
    public void getRotareRelative_halfTurn() {
        Point point = new PointClass(20, 20);
        Point origin = new PointClass(0, 0);

        Point expected = new PointClass(-20, -20);
        Point actual = point.getRotateRelative(origin, (PI));

        assertEquals(expected, actual);
    }

    @Test
    public void getRotareRelative_bigNumber_halfTurn() {
        Point point = new PointClass(200000, 200000);
        Point origin = new PointClass(0, 0);

        Point expected = new PointClass(-200000, -200000);
        Point actual = point.getRotateRelative(origin, (PI));

        assertEquals(expected, actual);
    }

    @Test
    public void getRotareRelative_270grad() {
        Point point = new PointClass(20, 20);
        Point origin = new PointClass(0, 0);

        Point expected = new PointClass(20, -20);
        Point actual = point.getRotateRelative(origin, (PI * 1.5));

        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_0() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(0, 0);

        double actual = first.getDistanceToPointProjectionX(second);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_inversionCall_0() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(0, 0);

        double actual = second.getDistanceToPointProjectionX(first);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_CallYourself() {
        Point first = new PointClass(0, 0);

        double actual = first.getDistanceToPointProjectionX(first);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_1() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(1, 0);

        double actual = first.getDistanceToPointProjectionX(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_secondPointIs0_1() {
        Point first = new PointClass(1, 0);
        Point second = new PointClass(0, 0);

        double actual = first.getDistanceToPointProjectionX(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_m1() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(-1, 0);

        double actual = first.getDistanceToPointProjectionX(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_secondPointIs0_m1() {
        Point first = new PointClass(-1, 0);
        Point second = new PointClass(0, 0);

        double actual = first.getDistanceToPointProjectionX(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }


    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_0() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(0, 0);

        double actual = first.getDistanceToPointProjectionY(second);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_inversionCall_0() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(0, 0);

        double actual = second.getDistanceToPointProjectionY(first);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_CallYourself() {
        Point first = new PointClass(0, 0);

        double actual = first.getDistanceToPointProjectionY(first);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_1() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(0, 1);

        double actual = first.getDistanceToPointProjectionY(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_secondPointIs0_1() {
        Point first = new PointClass(0, 1);
        Point second = new PointClass(0, 0);

        double actual = first.getDistanceToPointProjectionY(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_m1() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(0, -1);

        double actual = first.getDistanceToPointProjectionY(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_secondPointIs0_m1() {
        Point first = new PointClass(0, -1);
        Point second = new PointClass(0, 0);

        double actual = first.getDistanceToPointProjectionY(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_withCopy() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(0, 0);

        double secondTime = 10;
        for (double firstTime = 0; firstTime < secondTime; firstTime++) {
            for (double timeProximity = 0; timeProximity < secondTime; timeProximity++) {

                Point expected = new PointClass(0, 0);
                Point actual = first.getApproximationWith(firstTime, second, secondTime, timeProximity);
                assertEquals(expected, actual);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_centerCut() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(10, 0);

        Point expected = new PointClass(5, 0);
        Point actual = first.getApproximationWith(0, second, 10, 5);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_centerMinusCut() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(-10, 0);

        Point expected = new PointClass(-5, 0);
        Point actual = first.getApproximationWith(0, second, 10, 5);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_center2Dimension() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(-10, -10);

        Point expected = new PointClass(-5, -5);
        Point actual = first.getApproximationWith(0, second, 10, 5);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_center2DimensionAllPointNegative() {
        Point first = new PointClass(-2, -2);
        Point second = new PointClass(-10, -10);

        Point expected = new PointClass(-6, -6);
        Point actual = first.getApproximationWith(0, second, 10, 5);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_center2DimensionNegativePositive() {
        Point first = new PointClass(10, 10);
        Point second = new PointClass(-10, -10);

        Point expected = new PointClass(0, 0);
        Point actual = first.getApproximationWith(0, second, 10, 5);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_first2DimensionNegativePositive() {
        Point first = new PointClass(10, 10);
        Point second = new PointClass(-10, -10);

        Point expected = first;
        Point actual = first.getApproximationWith(0, second, 10, 0);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_second2DimensionNegativePositive() {
        Point first = new PointClass(10, 10);
        Point second = new PointClass(-10, -10);

        Point expected = second;
        Point actual = first.getApproximationWith(0, second, 10, 10);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_nearSecond2DimensionPositive() {
        Point first = new PointClass(0, 0);
        Point second = new PointClass(10, 10);

        Point expected = new PointClass(8, 8);
        Point actual = first.getApproximationWith(0, second, 10, 8);
        assertEquals(expected, actual);
    }


    @org.junit.jupiter.api.Test
    void getMultipliedVector_0() {
        Point point = new PointClass(13, 13);
        Point actual = point.getMultipliedVector(0);
        Point expected = new PointClass(0, 0);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getMultipliedVector_10() {
        Point point = new PointClass(5, 5);
        Point actual = point.getMultipliedVector(2);
        Point expected = new PointClass(10, 10);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getMultipliedVector_m10() {
        Point point = new PointClass(5, 5);
        Point actual = point.getMultipliedVector(-2);
        Point expected = new PointClass(-10, -10);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getMultipliedVector_point0() {
        Point point = new PointClass(0, 0);
        Point actual = point.getMultipliedVector(-2);
        Point expected = new PointClass(0, 0);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getMultipliedVector_mPoint_mMultiplier() {
        Point point = new PointClass(-1, -1);
        Point actual = point.getMultipliedVector(-2);
        Point expected = new PointClass(2, 2);
        assertEquals(expected, actual);
    }



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