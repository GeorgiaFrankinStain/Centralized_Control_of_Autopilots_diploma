package Logic.FootprintSpaceTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundClassTest {

    @Test
    void testEquals_negativePositive() {
        Round round1 = new RoundClass(new PointClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointClass(-1, 1), 4);

        assertTrue(round1.equals(round2));
    }
    @Test
    void testEquals_negativePositiveInversionCall() {
        Round round1 = new RoundClass(new PointClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointClass(-1, 1), 4);

        assertTrue(round2.equals(round1));
    }
    @Test
    void testEquals_negativePositiveCallYourself() {
        Round round1 = new RoundClass(new PointClass(-1, 1), 4);

        assertTrue(round1.equals(round1));
    }
    @Test
    void testEquals_nonEquals() {
        Round round1 = new RoundClass(new PointClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointClass(1, 1), 4);

        assertFalse(round1.equals(round2));
    }

    @Test
    void testHashCode_negativePositive() {
        Round round1 = new RoundClass(new PointClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointClass(-1, 1), 4);

        assertEquals(round1.hashCode(), round2.hashCode());
    }

    @Test
    void testHashCode_negativePositiveInversionCall() {
        Round round1 = new RoundClass(new PointClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointClass(-1, 1), 4);

        assertEquals(round2.hashCode(), round1.hashCode());
    }
    @Test
    void testHashCode_negativePositiveCallYourself() {
        Round round1 = new RoundClass(new PointClass(-1, 1), 4);

        assertEquals(round1.hashCode(), round1.hashCode());
    }
    @Test
    void testHashCode_nonEquals() {
        Round round1 = new RoundClass(new PointClass(-1, 1), 4);
        Round round2 = new RoundClass(new PointClass(1, 1), 4);

        int hash1 = round1.hashCode();
        int hash2 = round2.hashCode();

        System.out.println(hash1 + " == " + hash2);
        assertNotEquals(hash1, hash2);
    }
    @Test
    void testHashCode_nonEqualsPositive() {
        Round round1 = new RoundClass(new PointClass(2, 1), 4);
        Round round2 = new RoundClass(new PointClass(1, 1), 4);

        int hash1 = round1.hashCode();
        int hash2 = round2.hashCode();

        System.out.println(hash1 + " == " + hash2);
        assertNotEquals(hash1, hash2);
    }

    @Test
    void testHashCode_nonEqualsNegative() {
        Round round1 = new RoundClass(new PointClass(-2, -1), 4);
        Round round2 = new RoundClass(new PointClass(-1, -1), 4);

        int hash1 = round1.hashCode();
        int hash2 = round2.hashCode();

        System.out.println(hash1 + " == " + hash2);
        assertNotEquals(hash1, hash2);
    }

    @Test
    void testHashCode_nonEqualsRadius() {
        Round round1 = new RoundClass(new PointClass(-1, -1), 2);
        Round round2 = new RoundClass(new PointClass(-1, -1), 4);

        int hash1 = round1.hashCode();
        int hash2 = round2.hashCode();

        System.out.println(hash1 + " == " + hash2);
        assertNotEquals(hash1, hash2);
    }
}