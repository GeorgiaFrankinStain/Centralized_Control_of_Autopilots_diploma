package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundClassTest {

    @Test
    void testEquals_negativePositive() {
        Round round1 = new RoundClass(new PointCCoAClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointCCoAClass(-1, 1), 4);

        assertTrue(round1.equals(round2));
    }
    @Test
    void testEquals_negativePositiveInversionCall() {
        Round round1 = new RoundClass(new PointCCoAClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointCCoAClass(-1, 1), 4);

        assertTrue(round2.equals(round1));
    }
    @Test
    void testEquals_negativePositiveCallYourself() {
        Round round1 = new RoundClass(new PointCCoAClass(-1, 1), 4);

        assertTrue(round1.equals(round1));
    }
    @Test
    void testEquals_nonEquals() {
        Round round1 = new RoundClass(new PointCCoAClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointCCoAClass(1, 1), 4);

        assertFalse(round1.equals(round2));
    }

    @Test
    void testHashCode_negativePositive() {
        Round round1 = new RoundClass(new PointCCoAClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointCCoAClass(-1, 1), 4);

        assertEquals(round1.hashCode(), round2.hashCode());
    }

    @Test
    void testHashCode_negativePositiveInversionCall() {
        Round round1 = new RoundClass(new PointCCoAClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointCCoAClass(-1, 1), 4);

        assertEquals(round2.hashCode(), round1.hashCode());
    }
    @Test
    void testHashCode_negativePositiveCallYourself() {
        Round round1 = new RoundClass(new PointCCoAClass(-1, 1), 4);

        assertEquals(round1.hashCode(), round1.hashCode());
    }
    @Test
    void testHashCode_nonEquals() {
        Round round1 = new RoundClass(new PointCCoAClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointCCoAClass(1, 1), 4);

        int hash1 = round1.hashCode();
        int hash2 = round2.hashCode();

        assertNotEquals(hash1, hash2);
    }
    @Test
    void testHashCode_nonEqualsPositive() {
        Round round1 = new RoundClass(new PointCCoAClass(2, 1), 4);
        Round round2 = new RoundClass(new PointCCoAClass(1, 1), 4);

        int hash1 = round1.hashCode();
        int hash2 = round2.hashCode();

        assertNotEquals(hash1, hash2);
    }

    @Test
    void testHashCode_nonEqualsNegative() {
        Round round1 = new RoundClass(new PointCCoAClass(-2, -1), 4);
        Round round2 = new RoundClass(new PointCCoAClass(-1, -1), 4);

        int hash1 = round1.hashCode();
        int hash2 = round2.hashCode();

        assertNotEquals(hash1, hash2);
    }

    @Test
    void testHashCode_nonEqualsRadius() {
        Round round1 = new RoundClass(new PointCCoAClass(-1, -1), 2);
        Round round2 = new RoundClass(new PointCCoAClass(-1, -1), 4);

        int hash1 = round1.hashCode();
        int hash2 = round2.hashCode();

        assertNotEquals(hash1, hash2);
    }


    @Test
    void getApproximation_radius() {
        Round round1 = new RoundClass(new PointCCoAClass(0, 0), 4);
        Round round2 = new RoundClass(new PointCCoAClass(0, 0), 2);

        Round actual = round1.getApproximation(0, round2, 10, 5);
        Round expected = new RoundClass(new PointCCoAClass(0, 0), 3);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_center() {
        Round round1 = new RoundClass(new PointCCoAClass(0, 0), 4);
        Round round2 = new RoundClass(new PointCCoAClass(10, 10), 2);

        Round actual = round1.getApproximation(0, round2, 10, 5);
        Round expected = new RoundClass(new PointCCoAClass(5, 5), 3);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_centerNegative() {
        Round round1 = new RoundClass(new PointCCoAClass(-2, -2), 4);
        Round round2 = new RoundClass(new PointCCoAClass(-10, -10), 2);

        Round actual = round1.getApproximation(0, round2, 10, 5);
        Round expected = new RoundClass(new PointCCoAClass(-6, -6), 3);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_centerNegativePositive() {
        Round round1 = new RoundClass(new PointCCoAClass(10, 10), 4);
        Round round2 = new RoundClass(new PointCCoAClass(-10, -10), 2);

        Round actual = round1.getApproximation(0, round2, 10, 5);
        Round expected = new RoundClass(new PointCCoAClass(0, 0), 3);
        assertEquals(expected, actual);
    }

    @Test
    void getApproximation_centerCopy() {
        Round round1 = new RoundClass(new PointCCoAClass(10, 10), 4);
        Round round2 = new RoundClass(new PointCCoAClass(10, 10), 4);

        Round actual = round1.getApproximation(0, round2, 10, 5);
        Round expected = new RoundClass(new PointCCoAClass(10, 10), 4);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_centerYourself() {
        Round round1 = new RoundClass(new PointCCoAClass(10, 10), 4);

        Round actual = round1.getApproximation(0, round1, 10, 5);
        Round expected = new RoundClass(new PointCCoAClass(10, 10), 4);
        assertEquals(expected, actual);
    }

}