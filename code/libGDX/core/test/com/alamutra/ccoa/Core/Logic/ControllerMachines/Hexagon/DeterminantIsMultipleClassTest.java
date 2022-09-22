package com.alamutra.ccoa.Core.Logic.ControllerMachines.Hexagon;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import com.alamutra.ccoa.Core.Logic.GlobalVariable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeterminantIsMultipleClassTest {

    private double littleRadius = 10;
    private double distanceBetweenHorizontalNeighboringNodes = 20;
    private double distanceBetweenVerticalNeighborThroughOneNodes = 34.64101615137755;

    @Test
    void isCoordinateIsMultiple_noMultiple_x0y0() {
        DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius, new PointCCoAClass(0, 0));
        assertFalse(determinant.isCoordinateIsMultiple());
    }

    @Test
    void isCoordinateIsMultiple_evenMultiple_x10y5p77() {
        DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius,
                new PointCCoAClass(9.99999222649604, 5.773500447880849));
        assertTrue(determinant.isCoordinateIsMultiple());
    }

    @Test
    void isCoordinateIsMultiple_evenMultiple_xM10y5p77() {
        DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius,
                new PointCCoAClass(-10.00000777350396, 5.773500447880849));
        assertTrue(determinant.isCoordinateIsMultiple());
    }

    @Test
    void isCoordinateIsMultiple_oddMultiple_x0y23() {
        DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius,
                new PointCCoAClass(-9.069088036994799E-6, 23.09400627955275));
        assertTrue(determinant.isCoordinateIsMultiple());
    }

    @Test
    void isCoordinateIsMultiple_oddMultiple_x19y23() {
        DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius,
                new PointCCoAClass(19.999990930911963, 23.09400627955275));
        assertTrue(determinant.isCoordinateIsMultiple());
    }

    @Test
    void isCoordinateIsMultiple_oddMultipleFor() {
        PointCCoA startOddMultipleSetCoordinates = new PointCCoAClass(0, 23.09400627955275);

        int anyMultiplier = -5;

        for (int horizontalIndex = 0; horizontalIndex < Math.abs(anyMultiplier); horizontalIndex++) {
            for (int vertivalIndex = 0; vertivalIndex < Math.abs(anyMultiplier); vertivalIndex++) {
                PointCCoA testedMultiplePoint = startOddMultipleSetCoordinates.getDeposeOn(new PointCCoAClass(
                        distanceBetweenHorizontalNeighboringNodes * horizontalIndex,
                        distanceBetweenVerticalNeighborThroughOneNodes * vertivalIndex
                ));

                DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius, testedMultiplePoint);
                assertTrue(determinant.isCoordinateIsMultiple());

                testedZoneAccuracy(testedMultiplePoint);
            }
        }
    }

    @Test
    void isCoordinateIsMultiple_evenMultipleFor() {
        PointCCoA startEvenMultipleSetCoordinates = new PointCCoAClass(10, 5.773502691896258);

        int anyMultiplier = -5;

        for (int horizontalIndex = 0; horizontalIndex < Math.abs(anyMultiplier); horizontalIndex++) {
            for (int vertivalIndex = 0; vertivalIndex < Math.abs(anyMultiplier); vertivalIndex++) {
                PointCCoA testedMultiplePoint = startEvenMultipleSetCoordinates.getDeposeOn(new PointCCoAClass(
                        distanceBetweenHorizontalNeighboringNodes * horizontalIndex,
                        distanceBetweenVerticalNeighborThroughOneNodes * vertivalIndex
                ));

                DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius, testedMultiplePoint);
                assertTrue(determinant.isCoordinateIsMultiple());

                testedZoneAccuracy(testedMultiplePoint);
            }
        }
    }

    private void testedZoneAccuracy(PointCCoA multiplePoint) {
        double permissibleError = GlobalVariable.DOUBLE_COMPARISON_ACCURACY / 3;

        for (int xDeviationIndex = -1; xDeviationIndex < 2; xDeviationIndex++) {
            for (int yDeviationIndex = -1; yDeviationIndex < 2; yDeviationIndex++) {
                double xDeviation = permissibleError * xDeviationIndex;
                double yDeviation = permissibleError * yDeviationIndex;
                PointCCoA deviationPoint = multiplePoint.getDeposeOn(new PointCCoAClass(xDeviation, yDeviation));

                DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius, deviationPoint);
                assertTrue(determinant.isCoordinateIsMultiple());
            }
        }
    }
}