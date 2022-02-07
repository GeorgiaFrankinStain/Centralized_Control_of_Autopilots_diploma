package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Core.Logic.GlobalVariable;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

class PointCCoAClassTest {
    double standartDelta = GlobalVariable.DOUBLE_COMPARISON_ACCURACY;

    @Test
    public void getAngleRelative_1_0() {
        PointCCoA origin = new PointCCoAClass(0, 0);
        PointCCoA pointCCoA = new PointCCoAClass(1, 0);

        double actual = pointCCoA.getAngleRotareRelative(origin);
        assertEquals(0, actual, standartDelta);
    }

    @Test
    public void getAngleRelative_m1_0() {
        PointCCoA origin = new PointCCoAClass(0, 0);
        PointCCoA pointCCoA = new PointCCoAClass(-1, 0);

        double actual = pointCCoA.getAngleRotareRelative(origin);
        assertEquals(PI, actual, standartDelta);
    }

    @Test
    public void getAngleRelative_m1_m1() {
        PointCCoA origin = new PointCCoAClass(0, 0);
        PointCCoA pointCCoA = new PointCCoAClass(-1, -1);

        double expected = -0.75 * PI;
        double actual = pointCCoA.getAngleRotareRelative(origin);
        assertEquals(expected, actual, standartDelta);
    }

    @Test
    public void getAngleRelative_1_m1() {
        PointCCoA origin = new PointCCoAClass(0, 0);
        PointCCoA pointCCoA = new PointCCoAClass(1, -1);

        double actual = pointCCoA.getAngleRotareRelative(origin);
        assertEquals(-0.25 * PI, actual, standartDelta);
    }

    @Test
    public void getAngleRelative_1_1() {
        PointCCoA origin = new PointCCoAClass(0, 0);
        PointCCoA pointCCoA = new PointCCoAClass(1, 1);

        double actual = pointCCoA.getAngleRotareRelative(origin);
        assertEquals(0.25 * PI, actual, standartDelta);
    }

    @Test
    public void getRotareRelative_mPId2() {
        PointCCoA pointCCoA = new PointCCoAClass(0, 10);
        PointCCoA origin = new PointCCoAClass(0, 0);

        PointCCoA expected = new PointCCoAClass(10, 0);
        PointCCoA actual = pointCCoA.getRotateRelative(origin, -(PI / 2));

        assertEquals(expected, actual);
    }

    @Test
    public void getRotareRelative_bigPoint() {
        PointCCoA pointCCoA = new PointCCoAClass(0, 100);
        PointCCoA origin = new PointCCoAClass(0, 0);

        PointCCoA expected = new PointCCoAClass(100, 0);
        PointCCoA actual = pointCCoA.getRotateRelative(origin, -(PI / 2));

        assertEquals(expected, actual);
    }

    @Test
    public void getRotareRelative_firstQuarter_quarterTurn() {
        PointCCoA pointCCoA = new PointCCoAClass(20, 20);
        PointCCoA origin = new PointCCoAClass(0, 0);

        PointCCoA expected = new PointCCoAClass(-20, 20);
        PointCCoA actual = pointCCoA.getRotateRelative(origin, (PI / 2));

        assertEquals(expected, actual);
    }

    @Test
    public void getRotareRelative_halfTurn() {
        PointCCoA pointCCoA = new PointCCoAClass(20, 20);
        PointCCoA origin = new PointCCoAClass(0, 0);

        PointCCoA expected = new PointCCoAClass(-20, -20);
        PointCCoA actual = pointCCoA.getRotateRelative(origin, (PI));

        assertEquals(expected, actual);
    }

    @Test
    public void getRotareRelative_bigNumber_halfTurn() {
        PointCCoA pointCCoA = new PointCCoAClass(200000, 200000);
        PointCCoA origin = new PointCCoAClass(0, 0);

        PointCCoA expected = new PointCCoAClass(-200000, -200000);
        PointCCoA actual = pointCCoA.getRotateRelative(origin, (PI));

        assertEquals(expected, actual);
    }

    @Test
    public void getRotareRelative_270grad() {
        PointCCoA pointCCoA = new PointCCoAClass(20, 20);
        PointCCoA origin = new PointCCoAClass(0, 0);

        PointCCoA expected = new PointCCoAClass(20, -20);
        PointCCoA actual = pointCCoA.getRotateRelative(origin, (PI * 1.5));

        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_0() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(0, 0);

        double actual = first.getDistanceToPointProjectionX(second);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_inversionCall_0() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(0, 0);

        double actual = second.getDistanceToPointProjectionX(first);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_CallYourself() {
        PointCCoA first = new PointCCoAClass(0, 0);

        double actual = first.getDistanceToPointProjectionX(first);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_1() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(1, 0);

        double actual = first.getDistanceToPointProjectionX(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_secondPointIs0_1() {
        PointCCoA first = new PointCCoAClass(1, 0);
        PointCCoA second = new PointCCoAClass(0, 0);

        double actual = first.getDistanceToPointProjectionX(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_m1() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(-1, 0);

        double actual = first.getDistanceToPointProjectionX(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionX_secondPointIs0_m1() {
        PointCCoA first = new PointCCoAClass(-1, 0);
        PointCCoA second = new PointCCoAClass(0, 0);

        double actual = first.getDistanceToPointProjectionX(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }


    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_0() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(0, 0);

        double actual = first.getDistanceToPointProjectionY(second);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_inversionCall_0() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(0, 0);

        double actual = second.getDistanceToPointProjectionY(first);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_CallYourself() {
        PointCCoA first = new PointCCoAClass(0, 0);

        double actual = first.getDistanceToPointProjectionY(first);
        assertEquals(0, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_1() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(0, 1);

        double actual = first.getDistanceToPointProjectionY(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_secondPointIs0_1() {
        PointCCoA first = new PointCCoAClass(0, 1);
        PointCCoA second = new PointCCoAClass(0, 0);

        double actual = first.getDistanceToPointProjectionY(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_m1() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(0, -1);

        double actual = first.getDistanceToPointProjectionY(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getDistanceToPointProjectionY_secondPointIs0_m1() {
        PointCCoA first = new PointCCoAClass(0, -1);
        PointCCoA second = new PointCCoAClass(0, 0);

        double actual = first.getDistanceToPointProjectionY(second);
        assertEquals(1, actual, GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_withCopy() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(0, 0);

        double secondTime = 10;
        for (double firstTime = 0; firstTime < secondTime; firstTime++) {
            for (double timeProximity = 0; timeProximity < secondTime; timeProximity++) {

                PointCCoA expected = new PointCCoAClass(0, 0);
                PointCCoA actual = first.getApproximationWith(firstTime, second, secondTime, timeProximity);
                assertEquals(expected, actual);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_centerCut() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(10, 0);

        PointCCoA expected = new PointCCoAClass(5, 0);
        PointCCoA actual = first.getApproximationWith(0, second, 10, 5);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_centerMinusCut() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(-10, 0);

        PointCCoA expected = new PointCCoAClass(-5, 0);
        PointCCoA actual = first.getApproximationWith(0, second, 10, 5);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_center2Dimension() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(-10, -10);

        PointCCoA expected = new PointCCoAClass(-5, -5);
        PointCCoA actual = first.getApproximationWith(0, second, 10, 5);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_center2DimensionAllPointNegative() {
        PointCCoA first = new PointCCoAClass(-2, -2);
        PointCCoA second = new PointCCoAClass(-10, -10);

        PointCCoA expected = new PointCCoAClass(-6, -6);
        PointCCoA actual = first.getApproximationWith(0, second, 10, 5);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_center2DimensionNegativePositive() {
        PointCCoA first = new PointCCoAClass(10, 10);
        PointCCoA second = new PointCCoAClass(-10, -10);

        PointCCoA expected = new PointCCoAClass(0, 0);
        PointCCoA actual = first.getApproximationWith(0, second, 10, 5);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_first2DimensionNegativePositive() {
        PointCCoA first = new PointCCoAClass(10, 10);
        PointCCoA second = new PointCCoAClass(-10, -10);

        PointCCoA expected = first;
        PointCCoA actual = first.getApproximationWith(0, second, 10, 0);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_second2DimensionNegativePositive() {
        PointCCoA first = new PointCCoAClass(10, 10);
        PointCCoA second = new PointCCoAClass(-10, -10);

        PointCCoA expected = second;
        PointCCoA actual = first.getApproximationWith(0, second, 10, 10);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getApproximationWith_nearSecond2DimensionPositive() {
        PointCCoA first = new PointCCoAClass(0, 0);
        PointCCoA second = new PointCCoAClass(10, 10);

        PointCCoA expected = new PointCCoAClass(8, 8);
        PointCCoA actual = first.getApproximationWith(0, second, 10, 8);
        assertEquals(expected, actual);
    }


    @org.junit.jupiter.api.Test
    void getMultipliedVector_0() {
        PointCCoA pointCCoA = new PointCCoAClass(13, 13);
        PointCCoA actual = pointCCoA.getMultipliedVector(0);
        PointCCoA expected = new PointCCoAClass(0, 0);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getMultipliedVector_10() {
        PointCCoA pointCCoA = new PointCCoAClass(5, 5);
        PointCCoA actual = pointCCoA.getMultipliedVector(2);
        PointCCoA expected = new PointCCoAClass(10, 10);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getMultipliedVector_m10() {
        PointCCoA pointCCoA = new PointCCoAClass(5, 5);
        PointCCoA actual = pointCCoA.getMultipliedVector(-2);
        PointCCoA expected = new PointCCoAClass(-10, -10);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getMultipliedVector_point0() {
        PointCCoA pointCCoA = new PointCCoAClass(0, 0);
        PointCCoA actual = pointCCoA.getMultipliedVector(-2);
        PointCCoA expected = new PointCCoAClass(0, 0);
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getMultipliedVector_mPoint_mMultiplier() {
        PointCCoA pointCCoA = new PointCCoAClass(-1, -1);
        PointCCoA actual = pointCCoA.getMultipliedVector(-2);
        PointCCoA expected = new PointCCoAClass(2, 2);
        assertEquals(expected, actual);
    }



    @Test
    void testHashCode_0() {
        PointCCoA pointCCoA1 = new PointCCoAClass(0, 0);
        PointCCoA pointCCoA2 = new PointCCoAClass(0, 0);

        assertEquals(pointCCoA1.hashCode(), pointCCoA2.hashCode());
    }

    @Test
    void testHashCode_positive() {
        PointCCoA pointCCoA1 = new PointCCoAClass(1, 1);
        PointCCoA pointCCoA2 = new PointCCoAClass(1, 1);

        assertEquals(pointCCoA1.hashCode(), pointCCoA2.hashCode());
    }

    @Test
    void testHashCode_negative() {
        PointCCoA pointCCoA1 = new PointCCoAClass(-1, -1);
        PointCCoA pointCCoA2 = new PointCCoAClass(-1, -1);

        assertEquals(pointCCoA1.hashCode(), pointCCoA2.hashCode());
    }
    @Test
    void testHashCode_negativePositive() {
        PointCCoA pointCCoA1 = new PointCCoAClass(1, -1);
        PointCCoA pointCCoA2 = new PointCCoAClass(1, -1);

        assertEquals(pointCCoA1.hashCode(), pointCCoA2.hashCode());
    }

    @Test
    void testHashCode_nonEquals() {
        PointCCoA pointCCoA1 = new PointCCoAClass(-1, 1);
        PointCCoA pointCCoA2 = new PointCCoAClass(1, 1);

        assertNotEquals(pointCCoA1.hashCode(), pointCCoA2.hashCode());
    }


    @Test
    void testHashCode_EqualsObjectAndEqualsHashcode() {
        PointCCoA pointCCoA1 = new PointCCoAClass(1, 1);

        PointCCoA pointCCoA2 = new PointCCoAClass(1, 1);

        assertEquals(pointCCoA1.hashCode(), pointCCoA2.hashCode());
        assertEquals(pointCCoA1, pointCCoA2);
    }

    @Test
    void testHashCode_EqualsObjectAndEqualsHashcodeAfterMicroChanging() {
        PointCCoA vector = new PointCCoAClass(13, 13);
        PointCCoA pointCCoA1 = new PointCCoAClass(1, 1);
        pointCCoA1.deposeOn(vector);
        pointCCoA1.deposeOn(vector.getInversion());

        PointCCoA pointCCoA2 = new PointCCoAClass(1, 1);
        pointCCoA2.deposeOn(vector);
        pointCCoA2.deposeOn(vector.getInversion());

        assertEquals(pointCCoA1.hashCode(), pointCCoA2.hashCode());
        assertEquals(pointCCoA1, pointCCoA2);
    }


    @Test
    void testEquals_0() {
        PointCCoA pointCCoA1 = new PointCCoAClass(0, 0);
        PointCCoA pointCCoA2 = new PointCCoAClass(0, 0);

        assertEquals(pointCCoA1, pointCCoA2);
    }

    @Test
    void testEquals_positive() {
        PointCCoA pointCCoA1 = new PointCCoAClass(1, 1);
        PointCCoA pointCCoA2 = new PointCCoAClass(1, 1);

        assertEquals(pointCCoA1, pointCCoA2);
    }

    @Test
    void testEquals_negative() {
        PointCCoA pointCCoA1 = new PointCCoAClass(-1, -1);
        PointCCoA pointCCoA2 = new PointCCoAClass(-1, -1);

        assertEquals(pointCCoA1, pointCCoA2);
    }

    @Test
    void testEquals_negativePositive() {
        PointCCoA pointCCoA1 = new PointCCoAClass(-1, 1);
        PointCCoA pointCCoA2 = new PointCCoAClass(-1, 1);

        assertEquals(pointCCoA1, pointCCoA2);
    }

    @Test
    void testEquals_nonEquals() {
        PointCCoA pointCCoA1 = new PointCCoAClass(-1, 1);
        PointCCoA pointCCoA2 = new PointCCoAClass(1, 1);

        assertNotEquals(pointCCoA1, pointCCoA2);
    }
}