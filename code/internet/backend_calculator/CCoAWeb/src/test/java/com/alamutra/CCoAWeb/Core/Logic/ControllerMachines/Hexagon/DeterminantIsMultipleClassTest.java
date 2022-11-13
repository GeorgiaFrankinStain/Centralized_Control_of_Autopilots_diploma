package com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.Hexagon;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import com.alamutra.CCoAWeb.Core.Logic.GlobalVariable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeterminantIsMultipleClassTest {

    private double littleRadius = 10;
    private double distanceBetweenHorizontalNeighboringNodes = 20;
    private double distanceBetweenVerticalNeighborThroughOneNodes = 34.64101615137755;

    private PointCCoA startEvenMultipleSetCoordinates = new PointCCoAClass(10.0,11.547005383792516);
    private PointCCoA startOddMultipleSetCoordinates = new PointCCoAClass(0.0, 28.86751345948129);

    @Test
    void isCoordinateIsMultiple_noMultiple_x0y0() {
        DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius, new PointCCoAClass(0, 0));
        assertFalse(determinant.isCoordinateIsMultiple());
    }

    @Test
    void isCoordinateIsMultiple_evenMultiple_x10y5p77() {
        DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius,
                startEvenMultipleSetCoordinates);
        assertTrue(determinant.isCoordinateIsMultiple());
    }

    @Test
    void isCoordinateIsMultiple_evenMultiple_xM10y5p77() {
        DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius,
                new PointCCoAClass(-littleRadius, 11.547005383792516));
        assertTrue(determinant.isCoordinateIsMultiple());
    }

    @Test
    void isCoordinateIsMultiple_oddMultiple_x0y23() {
        DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius,
                startOddMultipleSetCoordinates);
        assertTrue(determinant.isCoordinateIsMultiple());
    }

    @Test
    void isCoordinateIsMultiple_oddMultiple_x19y23() {
        DeterminantIsMultiple determinant = new DeterminantIsMultipleClass(littleRadius,
                new PointCCoAClass(distanceBetweenHorizontalNeighboringNodes, 28.86751345948129));
        assertTrue(determinant.isCoordinateIsMultiple());
    }

    @Test
    void isCoordinateIsMultiple_oddMultipleFor() {

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