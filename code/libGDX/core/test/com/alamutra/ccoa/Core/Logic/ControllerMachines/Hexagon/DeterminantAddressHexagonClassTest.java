package com.alamutra.ccoa.Core.Logic.ControllerMachines.Hexagon;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.HexagonVectors;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class DeterminantAddressHexagonClassTest {

    private double littleDiameterHexagon = 20;

    @Test
    void detectedCenterHexagonInclude_X0Y0Zone4() {
        DeterminantAddressHexagon determinantAddressHexagon =
                new DeterminantAddressHexagonClass(new PointCCoAClass(0, 0), 20);
        PointCCoA center = determinantAddressHexagon.detectedCenterHexagonIncludeCoordinate();
        assertEquals(new PointCCoAClass(-9.999998704415923,5.773502691896258), center);
    }

    @Test
    void detectedCenterHexagonInclude_X10Y10() {
        DeterminantAddressHexagon determinantAddressHexagon =
                new DeterminantAddressHexagonClass(new PointCCoAClass(10, 10), 20);
        PointCCoA center = determinantAddressHexagon.detectedCenterHexagonIncludeCoordinate();
        assertEquals(new PointCCoAClass(9.999998704415923, 5.773502691896258), center);
    }

    private HashSet<Integer> setHashCodes = new HashSet<>();

    @Test
    void detectedHashCodeHexagonIncludeCoordinate() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        TesterUniqueHashCodes tester = new TesterUniqueHashCodesClass();
        tester.toTest();
    }


    private interface TesterUniqueHashCodes {
        public void toTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
    }

    private class TesterUniqueHashCodesClass implements TesterUniqueHashCodes {

        private PointCCoA centerFirstEvenHexagon;
        private PointCCoA centerFirstOddHexagon;

        public TesterUniqueHashCodesClass() {
            DeterminantAddressHexagon determinantAddressHexagon =
                    new DeterminantAddressHexagonClass(new PointCCoAClass(1, 1), littleDiameterHexagon);

            centerFirstEvenHexagon = determinantAddressHexagon.detectedCenterHexagonIncludeCoordinate();

            PointCCoA vectorToCenterFirstOddHexagon = getVectorToLeftBottomNeighborCenterHexagon();
            centerFirstOddHexagon = centerFirstEvenHexagon.getDeposeOn(vectorToCenterFirstOddHexagon);
        }

        @Override
        public void toTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

            double anyNumber = 201;
            int anyMultiplier = 10;
            double xDistanceToTheNeighborThroughTheLayer = littleDiameterHexagon;
            double yDistanceToTheNeighborThroughTheLayer =
                    DeterminantAddressHexagonClass.getDistanceBetweenVerticalNeighborThroughOneNodesAY(
                            littleDiameterHexagon
                    );
            int i = 0;
            int j = 0;


            double xStartOffset = xDistanceToTheNeighborThroughTheLayer * anyMultiplier;
            double yStartOffset = yDistanceToTheNeighborThroughTheLayer * anyMultiplier;

            for (double xOffset = xStartOffset; xOffset < anyNumber; xOffset += xDistanceToTheNeighborThroughTheLayer) {
                for (double yOffset = yStartOffset; yOffset < anyNumber; yOffset += yDistanceToTheNeighborThroughTheLayer) {
                    PointCCoA offset = new PointCCoAClass(xOffset, yOffset);
                    PointCCoA currentCenterEvenHexagon = centerFirstEvenHexagon.getDeposeOn(offset);
                    assertTrue(privateTest_isEvenNonOffsetRow(currentCenterEvenHexagon), "currentCenterEvenHexagon: " + currentCenterEvenHexagon);
                    testedUniqueHashCodes(currentCenterEvenHexagon, i, j, offset);

                    PointCCoA currentCenterOddHexagon = centerFirstOddHexagon.getDeposeOn(offset);
                    assertFalse(privateTest_isEvenNonOffsetRow(currentCenterOddHexagon), "currentCenterOddHexagon: " + currentCenterOddHexagon);
                    testedUniqueHashCodes(currentCenterOddHexagon, i, j, offset);

                    j++;
                }
                i++;
            }
        }


        private void testedUniqueHashCodes(PointCCoA centerHexagon, int i, int j, PointCCoA offset) {
            DeterminantAddressHexagon determinant =
                    new DeterminantAddressHexagonClass(centerHexagon, littleDiameterHexagon);
            int hashCode = determinant.detectedHashCodeHexagonIncludeCoordinate();
            boolean isNewHashCode = !setHashCodes.contains(hashCode);
            assertTrue(isNewHashCode,
                    "centerHexagon: " + centerHexagon
                            + " i: " + i + " j: " + j
                            + " offset: " + offset
                            + " hashCode: " + hashCode
                            + " sizeSetHashCodes: " + setHashCodes.size());

            setHashCodes.add(hashCode);

            testedCoordinateNotCenterHaveSameHashCode(hashCode, centerHexagon);
        }

        private void testedCoordinateNotCenterHaveSameHashCode(int hashCode, PointCCoA centerHexagon) {
            testedCoordinate(hashCode, centerHexagon.getDeposeOn(new PointCCoAClass(1, 1)));
            testedCoordinate(hashCode, centerHexagon.getDeposeOn(new PointCCoAClass(1, -1)));
            testedCoordinate(hashCode, centerHexagon.getDeposeOn(new PointCCoAClass(-1, 1)));
            testedCoordinate(hashCode, centerHexagon.getDeposeOn(new PointCCoAClass(-1, -1)));
            testedCoordinate(hashCode, centerHexagon.getDeposeOn(new PointCCoAClass(5, 5)));
            testedCoordinate(hashCode, centerHexagon.getDeposeOn(new PointCCoAClass(5, -5)));
            testedCoordinate(hashCode, centerHexagon.getDeposeOn(new PointCCoAClass(-5, 5)));
            testedCoordinate(hashCode, centerHexagon.getDeposeOn(new PointCCoAClass(-5, -5)));
        }

        private void testedCoordinate(int expectedHashCode, PointCCoA coordinate) {
            DeterminantAddressHexagon determinant =
                    new DeterminantAddressHexagonClass(coordinate, littleDiameterHexagon);
            int hashCode = determinant.detectedHashCodeHexagonIncludeCoordinate();
            assertEquals(expectedHashCode, hashCode);
        }


        private PointCCoA getVectorToLeftBottomNeighborCenterHexagon() {
            HexagonVectors hexagonVectorsToNeighbor = new HexagonVectorsToNeighborsHexagonTile(littleDiameterHexagon);

            return hexagonVectorsToNeighbor.getVector(0);
        }
    }


    private PointCCoA centerFirstEvenHexagon = createCenterFirstEvenHexagon();

    private PointCCoA createCenterFirstEvenHexagon() {
        DeterminantAddressHexagon determinantAddressHexagon =
                new DeterminantAddressHexagonClass(new PointCCoAClass(1, 1), littleDiameterHexagon);

        PointCCoA centerFirstEvenHexagon = determinantAddressHexagon.detectedCenterHexagonIncludeCoordinate();

        return centerFirstEvenHexagon;
    }

    private double distanceBetweenVerticalNeighborThroughOneNodes =
            createDistanceBetweenVerticalNeighborThroughOneNodes();

    private double createDistanceBetweenVerticalNeighborThroughOneNodes() {
        double distanceBetweenVerticalNeighborThroughOneNodes =
                DeterminantAddressHexagonClass.getDistanceBetweenVerticalNeighborThroughOneNodesAY(
                        littleDiameterHexagon
                );

        return distanceBetweenVerticalNeighborThroughOneNodes;
    }

    private PointCCoA centerFirstOddHexagon = createCenterFirstOddHexagon();

    private PointCCoA createCenterFirstOddHexagon() {
        PointCCoA vectorToCenterFirstOddHexagon = getVectorToLeftBottomNeighborCenterHexagon();
        PointCCoA centerFirstOddHexagon = centerFirstEvenHexagon.getDeposeOn(vectorToCenterFirstOddHexagon);
        return centerFirstOddHexagon;
    }

    @Test
    void isEvenNonOffsetRow_centerFirstEvenHexagon() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        assertTrue(privateTest_isEvenNonOffsetRow(centerFirstEvenHexagon));
    }

    @Test
    void isEvenNonOffsetRow_bottomNeighborCenter() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        PointCCoA bottomNeighborCenter = centerFirstOddHexagon.getDeposeOn(new PointCCoAClass(0, -distanceBetweenVerticalNeighborThroughOneNodes));
        assertFalse(privateTest_isEvenNonOffsetRow(bottomNeighborCenter));
    }

    @Test
    void isEvenNonOffsetRow_topNeighborCenter() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        PointCCoA topNeighborCenter = centerFirstOddHexagon.getDeposeOn(new PointCCoAClass(0, distanceBetweenVerticalNeighborThroughOneNodes));
        assertFalse(privateTest_isEvenNonOffsetRow(topNeighborCenter));
    }

    @Test
    void isEvenNonOffsetRow_centerFirstOddHexagon() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        assertFalse(privateTest_isEvenNonOffsetRow(centerFirstOddHexagon));
    }

    @Test
    void isEvenNonOffsetRow_horizontalOffsetEven() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        PointCCoA horizontalOffsetEven = centerFirstEvenHexagon.getDeposeOn(new PointCCoAClass(littleDiameterHexagon, 0));
        assertTrue(privateTest_isEvenNonOffsetRow(horizontalOffsetEven));
    }

    @Test
    void isEvenNonOffsetRow_negativeHorizontalOffsetEven() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        PointCCoA negativeHorizontalOffsetEven = centerFirstEvenHexagon.getDeposeOn(new PointCCoAClass(-littleDiameterHexagon, 0));
        assertTrue(privateTest_isEvenNonOffsetRow(negativeHorizontalOffsetEven));
    }

    @Test
    void isEvenNonOffsetRow_negativeVerticalOffsetEven() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        PointCCoA negativeVerticalOffsetEven = centerFirstEvenHexagon.getDeposeOn(new PointCCoAClass(0, -distanceBetweenVerticalNeighborThroughOneNodes));
        assertTrue(privateTest_isEvenNonOffsetRow(negativeVerticalOffsetEven));
    }

    private PointCCoA getVectorToLeftBottomNeighborCenterHexagon() {
        HexagonVectors hexagonVectorsToNeighbor = new HexagonVectorsToNeighborsHexagonTile(littleDiameterHexagon);

        return hexagonVectorsToNeighbor.getVector(0);
    }


    private boolean privateTest_isEvenNonOffsetRow(PointCCoA centerHexagon)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        PointCCoA anyCoordinate = new PointCCoAClass(1, 1);
        DeterminantAddressHexagon determinant =
                new DeterminantAddressHexagonClass(anyCoordinate, littleDiameterHexagon);

        Method method = DeterminantAddressHexagonClass.class.getDeclaredMethod("isEvenNonOffsetRow", PointCCoA.class);
        method.setAccessible(true);
        boolean actual = (boolean) method.invoke(determinant, centerHexagon);

        return actual;
    }
}