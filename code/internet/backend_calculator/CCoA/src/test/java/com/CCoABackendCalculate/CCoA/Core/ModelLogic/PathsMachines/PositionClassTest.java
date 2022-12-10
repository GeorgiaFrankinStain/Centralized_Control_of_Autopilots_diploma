package com.CCoABackendCalculate.CCoA.Core.ModelLogic.PathsMachines;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.Position;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class PositionClassTest {

    private double mediumAngleExtractionFromPrivate(double angle1, double angle2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Position anyPosition = new PositionClass(new PointCCoAClass(0, 0), 0);

        Method method = PositionClass.class.getDeclaredMethod("mediumAngle", double.class, double.class);
        method.setAccessible(true);
        double actual = (double) method.invoke(anyPosition, angle1, angle2);

        return actual;
    }

    @Test
    void privateTest_mediumAngle_two90DegreeAngles() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(1.54, mediumAngleExtractionFromPrivate(1.54, 1.54));
    }

    @Test
    void privateTest_mediumAngle_two0DegreeAngles() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(0, mediumAngleExtractionFromPrivate(0, 0));
    }

    @Test
    void privateTest_mediumAngle_from0To180Degree() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(Math.PI / 2, mediumAngleExtractionFromPrivate(Math.PI, 0));
    }

    @Test
    void privateTest_mediumAngle_from270To0Degree() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(Math.PI * -0.25, mediumAngleExtractionFromPrivate(Math.PI * -0.5, 0));
    }

    @Test
    void privateTest_mediumAngle_two90To270DegreeAngles() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(Math.PI, mediumAngleExtractionFromPrivate(Math.PI / 2, Math.PI * 1.5));
    }

    @Test
    void privateTest_mediumAngle_two135To225DegreeAngles() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(Math.PI, mediumAngleExtractionFromPrivate(Math.PI * 0.75, Math.PI * 1.25));
    }

    @Test
    void privateTest_mediumAngle_two135To45DegreeAngles() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(Math.PI / 2, mediumAngleExtractionFromPrivate(Math.PI * 0.75, Math.PI * 0.25));
    }

    private double approximationAngleExtractionFromPrivate(
            double angle1,
            double time1,
            double angle2,
            double time2,
            double timeApproximation
    ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Position anyPosition = new PositionClass(new PointCCoAClass(0, 0), 0);

        Method method = PositionClass.class.getDeclaredMethod(
                "approximationAngle",
                double.class,
                double.class,
                double.class,
                double.class,
                double.class
        );
        method.setAccessible(true);
        double actual = (double) method.invoke(anyPosition, angle1, time1, angle2, time2, timeApproximation);

        return actual;
    }

    @Test
    void privateTest_approximationAngle_0() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(0.0, approximationAngleExtractionFromPrivate(0.0, 0.0, 0.0, 0.0, 0.0));
    }

    @Test
    void privateTest_approximationAngle_angle1IsNegativeAngle2IsPositive() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(0.0, approximationAngleExtractionFromPrivate(-1.0, 0.0, 1.0, 2.0, 1.0));
    }

    @Test
    void privateTest_approximationAngle_earlierAngleIsLargerThanTheLaterOne()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(0.0, approximationAngleExtractionFromPrivate(1.0, 0.0, -1.0, 2.0, 1.0));
    }
    @Test
    void privateTest_approximationAngle_earlierAngleIsSmallerThanTheLaterOne()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(2.0, approximationAngleExtractionFromPrivate(1.0, 0.0, 3.0, 2.0, 1.0));
    }


    private double percentageProximityFromAngle1ToFoundAngleExtractionFromPrivate(
            double time1,
            double time2,
            double timeApproximation
    ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Position anyPosition = new PositionClass(new PointCCoAClass(0, 0), 0);

        Method method = PositionClass.class.getDeclaredMethod(
                "percentageProximityFromAngle1ToFoundAngle",
                double.class,
                double.class,
                double.class
        );
        method.setAccessible(true);
        double actual = (double) method.invoke(anyPosition, time1, time2, timeApproximation);

        return actual;
    }

    @Test
    void privateTest_percentageProximityFromAngle1ToFoundAngle_0()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(0.5, percentageProximityFromAngle1ToFoundAngleExtractionFromPrivate(0.0, 0.0, 0.0));
    }
    @Test
    void privateTest_percentageProximityFromAngle1ToFoundAngle_fromM1ToP1()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(0.5, percentageProximityFromAngle1ToFoundAngleExtractionFromPrivate(-1.0, 1.0, 0.0));
    }
    @Test
    void privateTest_percentageProximityFromAngle1ToFoundAngle_normal()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(0.5, percentageProximityFromAngle1ToFoundAngleExtractionFromPrivate(-1.0, 1.0, 0.0));
    }


    private boolean isTimeApproximationIncludeInTimeIntervalExtractionFromPrivate(
            double time1,
            double time2,
            double timeApproximation
    ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Position anyPosition = new PositionClass(new PointCCoAClass(0, 0), 0);

        Method method = PositionClass.class.getDeclaredMethod(
                "isTimeApproximationIncludeInTimeInterval",
                double.class,
                double.class,
                double.class
        );
        method.setAccessible(true);
        boolean actual = (boolean) method.invoke(anyPosition, time1, time2, timeApproximation);
        return actual;
    }


    @Test
    void privateTest_isTimeApproximationIncludeInTimeInterval_standard()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertTrue(isTimeApproximationIncludeInTimeIntervalExtractionFromPrivate(0, 3, 2));
    }

    private double percentageProximityExtractionFromPrivate(
            double time1,
            double time2,
            double timeApproximation
    ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Position anyPosition = new PositionClass(new PointCCoAClass(0, 0), 0);

        Method method = PositionClass.class.getDeclaredMethod(
                "percentageProximity",
                double.class,
                double.class,
                double.class
        );
        method.setAccessible(true);
        double actual = (double) method.invoke(anyPosition, time1, time2, timeApproximation);

        return actual;
    }

    @Test
    void privateTest_percentageProximityToFirst_0()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        assertEquals(0.5, percentageProximityExtractionFromPrivate(0.0, 0.0, 0.0));
    }

    @Test
    void testEquals_0() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        Position second = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        assertEquals(first, second);
    }

    @Test
    void testEquals_1() {
        Position first = new PositionClass(new PointCCoAClass(1, 1), 1.0);
        Position second = new PositionClass(new PointCCoAClass(1, 1), 1.0);
        assertEquals(first, second);
    }

    @Test
    void testEquals_1m1() {
        Position first = new PositionClass(new PointCCoAClass(1, -1), 1.0);
        Position second = new PositionClass(new PointCCoAClass(1, -1), 1.0);
        assertEquals(first, second);
    }

    @Test
    void testEquals_m1p1() {
        Position first = new PositionClass(new PointCCoAClass(-1, 1), 1.0);
        Position second = new PositionClass(new PointCCoAClass(-1, 1), 1.0);
        assertEquals(first, second);
    }

    @Test
    void testEquals_m1p1Anglem1() {
        Position first = new PositionClass(new PointCCoAClass(-1, 1), -1.0);
        Position second = new PositionClass(new PointCCoAClass(-1, 1), -1.0);
        assertEquals(first, second);
    }

    @Test
    void testEquals_differentAngle() {
        Position first = new PositionClass(new PointCCoAClass(-1, 1), 1.0);
        Position second = new PositionClass(new PointCCoAClass(-1, 1), -1.0);
        assertNotEquals(first, second);
    }

    @Test
    void testEquals_differentXCoordinate() {
        Position first = new PositionClass(new PointCCoAClass(1, 1), -1.0);
        Position second = new PositionClass(new PointCCoAClass(-1, 1), -1.0);
        assertNotEquals(first, second);
    }

    @Test
    void testEquals_differentYCoordinate() {
        Position first = new PositionClass(new PointCCoAClass(1, -1), -1.0);
        Position second = new PositionClass(new PointCCoAClass(1, 1), -1.0);
        assertNotEquals(first, second);
    }

    @Test
    void testToString() {
        Position first = new PositionClass(new PointCCoAClass(1, -1), -1.0);
        String actual = first.toString();
        String expected = "((x: 1.0   y: -1.0) rotation: -1.0)";
        assertEquals(expected, actual);
    }

    @Test
    void testHashCode_0() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        Position second = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void testHashCode_1() {
        Position first = new PositionClass(new PointCCoAClass(1, 1), 1.0);
        Position second = new PositionClass(new PointCCoAClass(1, 1), 1.0);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void testHashCode_1m1() {
        Position first = new PositionClass(new PointCCoAClass(1, -1), 1.0);
        Position second = new PositionClass(new PointCCoAClass(1, -1), 1.0);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void testHashCode_m1p1() {
        Position first = new PositionClass(new PointCCoAClass(-1, 1), 1.0);
        Position second = new PositionClass(new PointCCoAClass(-1, 1), 1.0);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void testHashCode_m1p1Anglem1() {
        Position first = new PositionClass(new PointCCoAClass(-1, 1), -1.0);
        Position second = new PositionClass(new PointCCoAClass(-1, 1), -1.0);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void testHashCode_differentAngle() {
        Position first = new PositionClass(new PointCCoAClass(-1, 1), 1.0);
        Position second = new PositionClass(new PointCCoAClass(-1, 1), -1.0);
        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void testHashCode_differentXCoordinate() {
        Position first = new PositionClass(new PointCCoAClass(1, 1), -1.0);
        Position second = new PositionClass(new PointCCoAClass(-1, 1), -1.0);
        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void testHashCode_differentYCoordinate() {
        Position first = new PositionClass(new PointCCoAClass(1, -1), -1.0);
        Position second = new PositionClass(new PointCCoAClass(1, 1), -1.0);
        assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void getCoordinates_compareOneObjectPoint() {
        PointCCoA coordinate = new PointCCoAClass(0, 0);
        Position position = new PositionClass(coordinate, 0.0);
        PointCCoA actual = position.getCoordinates();
        assertEquals(coordinate, actual);
    }

    @Test
    void getCoordinates_compareDifferentObjectPoint() {
        PointCCoA coordinate = new PointCCoAClass(0, 0);
        Position position = new PositionClass(coordinate, 0.0);
        PointCCoA actual = position.getCoordinates();
        PointCCoA expected = new PointCCoAClass(0, 0);
        assertEquals(expected, actual);
    }

    @Test
    void getCoordinates_notEquals() {
        PointCCoA coordinate = new PointCCoAClass(0, 0);
        Position position = new PositionClass(coordinate, 0.0);
        PointCCoA actual = position.getCoordinates();
        PointCCoA expected = new PointCCoAClass(1, 1);
        assertNotEquals(expected, actual);
    }

    @Test
    void getRotation_0() {
        double rotation = 0.0;
        Position position = new PositionClass(new PointCCoAClass(0, 0), rotation);
        double actualRotation = position.getRotation();
        assertEquals(rotation, actualRotation);
    }

    @Test
    void getRotation_1() {
        double rotation = 1.0;
        Position position = new PositionClass(new PointCCoAClass(0, 0), rotation);
        double actualRotation = position.getRotation();
        assertEquals(rotation, actualRotation);
    }

    @Test
    void getRotation_acceptableRange() {
        assertTrue(isRotationIsIllegalArgumentException(6.0));
    }

    @Test
    void getRotation_aLittleMorePi() {
        assertTrue(isRotationIsIllegalArgumentException(Math.PI * 1.01));
    }

    private boolean isRotationIsIllegalArgumentException(double rotation) {

        boolean isThrowIsActivate = false;
        boolean isTextErrorIsRight = false;
        try {
            Position position = new PositionClass(new PointCCoAClass(0, 0), rotation);
        } catch (IllegalArgumentException e) {
            isThrowIsActivate = true;
            isTextErrorIsRight = e.toString().equals("java.lang.IllegalArgumentException: rotation is no valid");
        }
        return isTextErrorIsRight && isThrowIsActivate;
    }

    @Test
    void getRotation_notEquals() {
        double rotation = 1.0;
        Position position = new PositionClass(new PointCCoAClass(0, 0), rotation);
        double actualRotation = position.getRotation();
        assertNotEquals(0.0, actualRotation);
    }

    @Test
    void getApproximation_from0to2Middle() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        Position second = new PositionClass(new PointCCoAClass(2, 2), 2.0);
        Position actual = first.getApproximation(0.0, second, 2.0, 1.0);
        Position expected = new PositionClass(new PointCCoAClass(1, 1), 1.0);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_reverseOrder() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        Position second = new PositionClass(new PointCCoAClass(2, 2), 2.0);
        Position actual = second.getApproximation(2.0, first, 0.0, 1.0);
        Position expected = new PositionClass(new PointCCoAClass(1, 1), 1.0);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_reverseOrderTime() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        Position second = new PositionClass(new PointCCoAClass(2, 2), 2.0);
        Position actual = first.getApproximation(2.0, second, 0.0, 1.0);
        Position expected = new PositionClass(new PointCCoAClass(1, 1), 1.0);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_bothAnglesIsNegative() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), -3.0);
        Position second = new PositionClass(new PointCCoAClass(2, 2), -1.0);
        Position actual = first.getApproximation(0.0, second, 2.0, 1.0);
        Position expected = new PositionClass(new PointCCoAClass(1, 1), -2.0);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_bothAnglesIsPositive() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 3.0);
        Position second = new PositionClass(new PointCCoAClass(2, 2), 1.0);
        Position actual = first.getApproximation(0.0, second, 2.0, 1.0);
        Position expected = new PositionClass(new PointCCoAClass(1, 1), 2.0);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_angle1IsNegativeAngle2IsPositive() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), -1.0);
        Position second = new PositionClass(new PointCCoAClass(2, 2), 1.0);
        Position actual = first.getApproximation(-2.0, second, 2.0, 0.0);
        Position expected = new PositionClass(new PointCCoAClass(1, 1), 0.0);
        assertEquals(expected, actual);
    }
    @Test
    void getApproximation_sameTime() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        Position second = new PositionClass(new PointCCoAClass(2, 2), 2.0);
        Position actual = first.getApproximation(0.0, second, 2.0, 1.0);
        Position expected = new PositionClass(new PointCCoAClass(1, 1), 1.0);
        assertEquals(expected, actual);
    }

    @Test
    void getApproximation_fromM1ToP1Middle() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        Position second = new PositionClass(new PointCCoAClass(2, 2), 2.0);
        Position actual = first.getApproximation(-1.0, second, 1.0, 0.0);
        Position expected = new PositionClass(new PointCCoAClass(1, 1), 1.0);
        assertEquals(expected, actual);
    }

    @Test
    void getApproximation_fromM1ToP1Start() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        Position second = new PositionClass(new PointCCoAClass(2, 2), 2.0);
        Position actual = first.getApproximation(-1.0, second, 1.0, -1.0);
        assertEquals(first, actual);
    }

    @Test
    void getApproximation_fromM1ToP1End() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        Position second = new PositionClass(new PointCCoAClass(2, 2), 2.0);
        Position actual = first.getApproximation(-1.0, second, 1.0, 1.0);
        assertEquals(second, actual);
    }

    @Test
    void getRotationDegree_0() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), 0.0);
        double actual = first.getRotationDegree();
        double expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void getRotationDegree_90() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), Math.PI / 2);
        double actual = first.getRotationDegree();
        double expected = 90;
        assertEquals(expected, actual);
    }

    @Test
    void getRotationDegree_180() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), Math.PI);
        double actual = first.getRotationDegree();
        double expected = 180;
        assertEquals(expected, actual);
    }


    @Test
    void getRotationDegree_m90() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), -Math.PI / 2);
        double actual = first.getRotationDegree();
        double expected = -90;
        assertEquals(expected, actual);
    }

    @Test
    void getRotationDegree_m180() {
        Position first = new PositionClass(new PointCCoAClass(0, 0), -Math.PI);
        double actual = first.getRotationDegree();
        double expected = -180;
        assertEquals(expected, actual);
    }


}