package com.alamutra.ccoa.Core.Logic.ControllerMachines.Hexagon;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import com.alamutra.ccoa.Core.Logic.GlobalVariable;

public class DeterminantIsMultipleClass implements DeterminantIsMultiple {
    private double distanceBetweenHorizontalNeighboringNodes;
    private double distanceToNeighborStringNodes;
    private double distanceBetweenVerticalNeighborThroughOneNodes;
    private double lengthSideHexagon;
    private double littleRadius;
    private PointCCoA coordinate;

    private double littleDiameterHexagon;

    public DeterminantIsMultipleClass(double littleRadius, PointCCoA coordinate) {
        this.coordinate = coordinate;
        this.littleRadius = littleRadius;
        this.littleDiameterHexagon = littleRadius * 2;
        lengthSideHexagon = 2 * littleRadius / Math.sqrt(3);
        distanceBetweenHorizontalNeighboringNodes = littleDiameterHexagon;
        distanceToNeighborStringNodes = 3 * littleRadius / Math.sqrt(3);
        distanceBetweenVerticalNeighborThroughOneNodes = distanceToNeighborStringNodes * 2;
    }

    @Override
    public boolean isCoordinateIsMultiple() {
        boolean isMultipleEvenString = isMultipleEvenString();
        boolean isMultipleOddString = isMultipleOddString();
        return isMultipleEvenString || isMultipleOddString;
    }

    private boolean isMultipleEvenString() {
        PointCCoA startOfSetsOfEvenMultipleNodes = new PointCCoAClass(littleRadius, lengthSideHexagon / 2);

        return isMultiplePoint(startOfSetsOfEvenMultipleNodes);
    }

    private boolean isMultipleOddString() {
        PointCCoA startOfSetsOfOddEvenMultipleNodes = new PointCCoAClass(0.0, 23.09400627955275);

        return isMultiplePoint(startOfSetsOfOddEvenMultipleNodes);
    }

    private boolean isMultiplePoint(
            PointCCoA startSets
    ) {
        double xOffset = offset(distanceBetweenHorizontalNeighboringNodes, startSets.getX());
        boolean isMultipleX = isMultiple(distanceBetweenHorizontalNeighboringNodes, xOffset, coordinate.getX());

        double yOffset = offset(distanceBetweenVerticalNeighborThroughOneNodes, startSets.getY());
        boolean isMultipleY = isMultiple(distanceBetweenVerticalNeighborThroughOneNodes, yOffset, coordinate.getY());

        return isMultipleX && isMultipleY;
    }

    private double offset(double lengthDiapason, double startFirstDiapason) {
        double startPreviousDiapason = startFirstDiapason - lengthDiapason;
        assert (startPreviousDiapason <= 0);
        assert (Math.abs(startPreviousDiapason) <= lengthDiapason);
        return startPreviousDiapason;
    }

    private boolean isMultiple(
            double distanceBetweenNeighboringNodes,
            double offsetFrom0ToPreviousNeighborMultiple,
            double coordinate) {
        double xCoordinatePreviousMultipleNodes = GlobalVariable.getLeftBorderOffsetDiapason(coordinate,
                distanceBetweenNeighboringNodes, offsetFrom0ToPreviousNeighborMultiple);
        double distanceBetweenCoordinateAndPreviousNeighborMultiplesCoordinate =
                Math.abs(coordinate - xCoordinatePreviousMultipleNodes);

        assert (distanceBetweenCoordinateAndPreviousNeighborMultiplesCoordinate <= distanceBetweenNeighboringNodes);

        boolean isMultiple = isMultipleStartOrEnd(
                distanceBetweenCoordinateAndPreviousNeighborMultiplesCoordinate,
                distanceBetweenNeighboringNodes
        );

        return isMultiple;
    }

    private boolean isMultipleStartOrEnd(double distanceFromStartDiapason, double distanceToNextNode) {
        boolean isMultipleStartFirstDiapason = GlobalVariable.equalsNumber(0.0, distanceFromStartDiapason);
        boolean isMultipleEndFirstDiapason = GlobalVariable.equalsNumber(distanceToNextNode, distanceFromStartDiapason);

        return isMultipleStartFirstDiapason || isMultipleEndFirstDiapason;
    }
}
