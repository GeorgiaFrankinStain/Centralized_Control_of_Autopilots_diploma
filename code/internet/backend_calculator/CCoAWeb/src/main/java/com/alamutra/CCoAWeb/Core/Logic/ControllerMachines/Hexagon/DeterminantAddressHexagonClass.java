package com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.Hexagon;

import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.*;
import com.alamutra.CCoAWeb.Core.Logic.GlobalVariable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeterminantAddressHexagonClass implements DeterminantAddressHexagon {
    private static final Logger LOGGER = LogManager.getLogger(DeterminantAddressHexagonClass.class);


    /*
     * LINK_hJfIOv
     *
     * * - multiple nodes
     * *C - detectedCenterHexagonInclude
     * x - point include in hexagon
     *
     * +---+
     * |   |  - repeat rectangle
     * +---+
     *
     * the common line belongs to the left hexagon
     *
     *                           ╱  ╲
     *                        ╱        ╲
     *                      |            |
     *                      |     *      |
     *                      |            |
     *                    ╱ +------------+╲
     *                 ╱    |  1╲  2╱    |   ╲
     *                |     |     |   3  |     |
     *                |    *|  1  1     *|     |
     *                |     |     |      |     |
     *              ╱  ╲    |   1╱  ╲    |    ╱  ╲
     *           ╱        ╲ | ╱   5   5╲ | ╱        ╲
     *         |            |            5            |
     *         |     *  4   4     *C     |      *     |
     *         |            +------------+            |
     *           ╲        /   ╲        /   ╲        /
     *              ╲  /         ╲   /        ╲   /
     *                |            |            |
     *                |     *      |     *      |
     *                |            |            |
     *                  ╲        /   ╲        /
     *                    ╲   /         ╲   /
     *
     *
     */


    private PointCCoA coordinate;
    private double littleDiameterHexagon;
    private double littleRadius;
    private PointCCoA leftBottomRepeatRectangle;

    private PolygonCCoA zone1TopLeft;
    private PolygonCCoA zone2Top;
    private PolygonCCoA zone3TopRight;
    private PolygonCCoA zone4Left;
    private PolygonCCoA zone5Bottom;

    private HexagonVectors hexagonVectors;

    private boolean isCoordinateAlreadyMultiplePoints = false;


    public DeterminantAddressHexagonClass(PointCCoA coordinate, double littleDiameterHexagon) {
        assert (littleDiameterHexagon != Double.POSITIVE_INFINITY);
        assert (littleDiameterHexagon != Double.NEGATIVE_INFINITY);
        assert (littleDiameterHexagon != Double.NaN);
        assert (0 < littleDiameterHexagon);

        this.coordinate = coordinate;
        this.littleDiameterHexagon = littleDiameterHexagon;
        this.littleRadius = littleDiameterHexagon / 2;

        leftBottomRepeatRectangle = detectedLeftBottomRepeatRectangle(coordinate);

        DeterminantIsMultiple determinantIsMultiple = new DeterminantIsMultipleClass(this.littleRadius, coordinate);
        if (determinantIsMultiple.isCoordinateIsMultiple()) {
            this.isCoordinateAlreadyMultiplePoints = true;
            return;
        }

        createHexagonVectors();

        createZones();
    }


    /*
     *
     *                     ╱  ╲
     *                  ╱        ╲
     *                |    Y      |
     *               a|     *     |
     *                |     |     |
     *             a╱  ╲    |    ╱  ╲
     *           ╱        ╲ | ╱        ╲
     *         |            |            |
     *        a|  ---*-----B|            |
     *         |   |        |------------|
     *           ╲ |      / | ╲    |h  /   ╲
     *       oddH  |╲  /    |    ╲ | /        ╲
     *             |  |     |      |            |
     *            --- |     *------d---- *      |
     *                |    A       |      X     |
     *                 a╲        /   ╲        /
     *                    ╲   /         ╲   /
     *
     *
     * √ is the root of
     * a = 2r/√3
     * h = a/2    (because it is located opposite an angle of 30 degrees in a right triangle)
     *
     * AY = distanceBetweenVerticalNeighborThroughOneNodes (LINK_FFPqeD)
     * AB = AY/2 = h + a = a/2 + a = 1.5a = 3r/√3 = distanceToNeighborStringNodes
     */

    @Override
    public PointCCoA detectedCenterHexagonIncludeCoordinate() {
        if (this.isCoordinateAlreadyMultiplePoints) {
            return this.coordinate.clone();
        }

        if (isCoordinateIncludeIn1TopLeftZone()) {
            LOGGER.debug("coordinate: {}, zone: {}", coordinate, 1);
            return zone1TopLeft.getCenterAverage();
        } else if (isCoordinateIncludeIn2TopZone()) {
            LOGGER.debug("coordinate: {}, zone: {}", coordinate, 2);
            return zone2Top.getCenterAverage();
        } else if (isCoordinateIncludeIn3TopRightZone()) {
            LOGGER.debug("coordinate: {}, zone: {}", coordinate, 3);
            return zone3TopRight.getCenterAverage();
        } else if (isCoordinateIncludeIn4BottomLeftZone()) {
            LOGGER.debug("coordinate: {}, zone: {}", coordinate, 4);
            return zone4Left.getCenterAverage();
        } else if (isCoordinateIncludeIn5BottomZone()) {
            LOGGER.debug("coordinate: {}, zone: {}", coordinate, 5);
            return zone5Bottom.getCenterAverage();
        } else {
            LOGGER.error("the zone is not defined, coordinate: {}", coordinate);
            assert (false);
            return new PointCCoAClass(-1, -1);
        }
    }

    @Override
    public int detectedHashCodeHexagonIncludeCoordinate() {
        PointCCoA centerHexagon = detectedCenterHexagonIncludeCoordinate();
        LOGGER.debug("centerHexagon: {}", centerHexagon);


        if (isEvenNonOffsetRow(centerHexagon)) {
            return detectedHashInNonOffsetRow(centerHexagon);
        } else {
            PointCCoA mappedCenterEvenHexagon = getMappedPointFromEvenNodes(centerHexagon);
            LOGGER.debug("mappedCenterEvenHexagon: {}", mappedCenterEvenHexagon);
            int hashMappedCenter = detectedHashInNonOffsetRow(mappedCenterEvenHexagon);
            return getModifiedHashCodeGivenItsOriginFromOdd(hashMappedCenter);
        }
    }

    private int getModifiedHashCodeGivenItsOriginFromOdd(int hashCode) {
        final int prime = 71;
        int result = prime * hashCode + prime;

        return result;
    }

    private PointCCoA getMappedPointFromEvenNodes(PointCCoA centerOfOddHexagon) {
        PointCCoA vectorToLeftBottomNeighborCenterHexagon = getVectorToLeftBottomNeighborCenterHexagon();

        return centerOfOddHexagon.getDeposeOn(vectorToLeftBottomNeighborCenterHexagon);
    }

    private PointCCoA getVectorToLeftBottomNeighborCenterHexagon() {
        HexagonVectors hexagonVectorsToNeighbor = new HexagonVectorsToNeighborsHexagonTile(littleDiameterHexagon);

        return hexagonVectorsToNeighbor.getVector(3);
    }

    private boolean isEvenNonOffsetRow(PointCCoA centerHexagon) {
        double distanceFromStartLeftBorderEvenHexagons =
                getDistanceFromStartLeftBorderEvenHexagons(centerHexagon.getX(), this.littleDiameterHexagon);
        assert (0 <= distanceFromStartLeftBorderEvenHexagons);

        double expectedFirstHexagonCenterX = this.littleDiameterHexagon / 2;
        double acceptableAccuracy = this.littleDiameterHexagon / 10;
        boolean isNearWithCenterFirstEvenHexagonX = GlobalVariable.equalsNumber(
                expectedFirstHexagonCenterX,
                distanceFromStartLeftBorderEvenHexagons,
                acceptableAccuracy
        );

        //If the center of the hexagon coincides at least in x, then it will always coincide in y
        boolean isNearWithCenterFirstEvenHexagonY = isNearWithCenterFirstEvenHexagonX;

        return isNearWithCenterFirstEvenHexagonX && isNearWithCenterFirstEvenHexagonY;
    }

    private double getDistanceFromStartLeftBorderEvenHexagons(double coordinate, double lengthDiapason) {
        double leftBorder = GlobalVariable.getLeftBorderDiapason(coordinate, lengthDiapason);

        double result = coordinate - leftBorder;
        assert (0 <= result);
        return result;
    }

    private int detectedHashInNonOffsetRow(PointCCoA centerHexagon) {
        int xNumber = GlobalVariable.getNumberDiapason(centerHexagon.getX(), this.littleDiameterHexagon);

        double distanceBetweenVerticalNeighborThroughOneNodes =
                getDistanceBetweenVerticalNeighborThroughOneNodesAY(this.littleDiameterHexagon);
        int yNumber =
                GlobalVariable.getNumberDiapason(centerHexagon.getY(), distanceBetweenVerticalNeighborThroughOneNodes);


        final int prime = 31;
        int result = 1;
        result = prime * result + (int) xNumber;
        result = prime * result + (int) yNumber;
        result = prime * result + prime;

        return result;
    }

    /**
     * see the documentation above
     * LINK_FFPqeD
     */
    public static double getDistanceBetweenVerticalNeighborThroughOneNodesAY(double littleDiameterHexagon) {
        double r = littleDiameterHexagon / 2;
        double AY = 6 * r / Math.sqrt(3);

        return AY;
    }



    private boolean isCoordinateIncludeIn1TopLeftZone() {
        return zone1TopLeft.enteringPoint(coordinate);
    }

    private boolean isCoordinateIncludeIn2TopZone() {
        return zone2Top.enteringPoint(coordinate);
    }

    private boolean isCoordinateIncludeIn3TopRightZone() {
        return zone3TopRight.enteringPoint(coordinate);
    }

    private boolean isCoordinateIncludeIn4BottomLeftZone() {
        return zone4Left.enteringPoint(coordinate);
    }

    private boolean isCoordinateIncludeIn5BottomZone() {
        return zone5Bottom.enteringPoint(coordinate);
    }

    /*
     *
     *                           ╱  ╲
     *                        ╱        ╲
     *                      |            |
     *                      |     *      |
     *                      |            |
     *                    ╱ Y------------+╲
     *                 ╱    |   ╲   ╱    |   ╲
     *                |     |     |      |     |
     *               a| r  *|     |     *|     |
     *                |---------- |      |     |
     *              ╱  ╲    |h   ╱  ╲    |    ╱  ╲
     *          a╱        ╲ | ╱        ╲ | ╱        ╲
     *         |            |            |            |
     *        a|     *     a|     *C     |      *     |
     *         |            O------------X            |
     *           ╲        /   ╲   xI   /   ╲        /
     *              ╲  /         ╲   /        ╲   /
     *                |            |            |
     *
     * √ is the root of
     * a = 2r/√3
     * h = a/2    (because it is located opposite an angle of 30 degrees in a right triangle)
     * OY = 2h + 2a = 2 * (a/2) +  2 * (2r/√3) = 2 * ((2r/√3) / 2)  +  2 * (2r/√3) = 2r/√3 + 4r/√3 = 6r/√3
     *
     */
    private PointCCoA detectedLeftBottomRepeatRectangle(PointCCoA coordinate) {
        double OX = littleDiameterHexagon;
        double newX = coordinateNearestLeftBottomRectangle(coordinate.getX(), OX);

        double OY = getOY();
        double newY = coordinateNearestLeftBottomRectangle(coordinate.getY(), OY);

        return new PointCCoAClass(newX, newY);
    }

    private double getOY() {
        double r = littleDiameterHexagon / 2;
        double OY = 6 * r / Math.sqrt(3);

        return OY;
    }

    private double coordinateNearestLeftBottomRectangle(double coordinate, double lengthSide) {
        return GlobalVariable.getLeftBorderDiapason(coordinate, lengthSide);
    }

    private void createHexagonVectors() {
        this.hexagonVectors = new HexagonClass(littleRadius, 0, new PointCCoAClass(0, 0));
    }


    private void createZones() {
        createZone1TopLeft();
        createZone2TopLeft();
        createZone3TopRight();
        createZone4Left();
        createZone5Bottom();
    }

    private void createZone1TopLeft() {
        PointCCoA inTop = hexagonVectors.getVector1InTop();
        PointCCoA pointNumber5Zone1 = leftBottomRepeatRectangle.getDeposeOn(inTop);
        this.zone1TopLeft = new HexagonClass(littleRadius, 5, pointNumber5Zone1);
    }

    private void createZone2TopLeft() {
        PointCCoA inTop = hexagonVectors.getVector1InTop();
        PointCCoA pointNumber5Zone2 = leftBottomRepeatRectangle.getDeposeOn(inTop);

        PointCCoA inTopRight = hexagonVectors.getVector2TopRight();
        pointNumber5Zone2.deposeOn(inTopRight);

        pointNumber5Zone2.deposeOn(inTop);
        this.zone2Top = new HexagonClass(littleRadius, 5, pointNumber5Zone2);
    }

    private void createZone3TopRight() {
        PointCCoA inTop = hexagonVectors.getVector1InTop();
        PointCCoA pointNumber0Zone3 = leftBottomRepeatRectangle.getDeposeOn(inTop);

        PointCCoA inTopRight = hexagonVectors.getVector2TopRight();
        pointNumber0Zone3.deposeOn(inTopRight);
        this.zone3TopRight = new HexagonClass(littleRadius, 0, pointNumber0Zone3);
    }

    private void createZone4Left() {
        PointCCoA pointNumber4Zone4 = leftBottomRepeatRectangle.clone();
        this.zone4Left = new HexagonClass(littleRadius, 4, pointNumber4Zone4);
    }

    private void createZone5Bottom() {
        PointCCoA pointNumber0Zone5 = leftBottomRepeatRectangle.clone();
        this.zone5Bottom = new HexagonClass(littleRadius, 0, pointNumber0Zone5);
    }

}
