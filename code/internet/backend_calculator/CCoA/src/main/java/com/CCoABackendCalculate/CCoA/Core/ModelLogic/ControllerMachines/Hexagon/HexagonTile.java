package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.Hexagon;

import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.*;
import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.NetworkNodes;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.ParametersMovingUnique;

import java.util.ArrayList;
import java.util.List;

public class HexagonTile implements NetworkNodes {

    private MagnetCoordinateStorage magnetCoordinateStorage;
    private StorageManualNodes storageManualNodes;

    private int numberLayersHexagonTile;
    private ParametersMovingUnique parametersMovingUnique;
    private double littleDiameter;
    private double intervalTimestamps;


    private double degree180 = 3.1415926535;

    public HexagonTile(double allowedErrorCoDirectivityRadian, ParametersMovingUnique parametersMovingUnique) {
        assert (0 < allowedErrorCoDirectivityRadian && allowedErrorCoDirectivityRadian < degree180);
        this.numberLayersHexagonTile = determinationNumberLayers(allowedErrorCoDirectivityRadian);
        assert (this.numberLayersHexagonTile != 0);


        this.parametersMovingUnique = parametersMovingUnique;
        double lengthCar = this.parametersMovingUnique.getLengthStep();
        littleDiameter = lengthCar / this.numberLayersHexagonTile; //view documentation LINK_HjPgZo

        determinationIntervalTimestamps();
        this.magnetCoordinateStorage = new MagnetCoordinateStorageVacuumDoubleHashMap(intervalTimestamps);

        this.storageManualNodes =
                new DeterminantNeighborsManualNodes(littleDiameter, this, parametersMovingUnique);
    }

    private void determinationIntervalTimestamps() {
        double distanceToNeighborNode = this.littleDiameter;
        double timeTravelToNeighborNode = this.parametersMovingUnique.getTimeTravel(distanceToNeighborNode);
        this.intervalTimestamps = timeTravelToNeighborNode / this.numberLayersHexagonTile;
    }

    @Override
    public List<Node> getNeighboringNodes(PointCCoA coordinates, double timeTravelFromStart) {
        List<Node> neighbors = new ArrayList<>();

        DeterminantNeighborsNodes determinantNeighborsNodes = new DeterminantNeighborsNodesClass(
                coordinates,
                timeTravelFromStart,
                this,
                this.littleDiameter,
                this.numberLayersHexagonTile,
                this.intervalTimestamps
        );
        List<Node> neighborsCentersHexagons = determinantNeighborsNodes.neighboringMultipleNodes();
        neighbors.addAll(neighborsCentersHexagons);

        List<Node> manualsNode = storageManualNodes.neighboringMultipleNodes(
                neighborsCentersHexagons,
                coordinates,
                timeTravelFromStart
        );

        neighbors.addAll(manualsNode);

//        neighbors.addAll(nonMultipleNodesNarrowRoads());

        List<Node> neighborsWithoutDuplicatesDueToErrors = getNodesWithoutDuplicates(neighbors);

        return neighborsWithoutDuplicatesDueToErrors;
    }

    private List<Node> getNodesWithoutDuplicates(List<Node> neighbors) {
        List<Node> neighborsWithoutDuplicatesDueToErrors = new ArrayList<>();
        for (Node tryNode : neighbors) {
            Node magnetizedNode = this.magnetCoordinateStorage.getFirstMagnetizedNodeTryAdd(tryNode);
            neighborsWithoutDuplicatesDueToErrors.add(magnetizedNode);
        }

        return neighborsWithoutDuplicatesDueToErrors;
    }

    @Override
    public List<Node> getNeighboringNodes(Node currentNode) {
        return getNeighboringNodes(currentNode.getCoordinate(), currentNode.getActualTimeTravelFromStart());
    }

    @Override
    public Node createNode(PointCCoA coordinates, double timeTravelFromStart) {
        Node tryNode = new NodeClass(this, coordinates, timeTravelFromStart);

        Node noDuplicateNode = this.magnetCoordinateStorage.getFirstMagnetizedNodeTryAdd(tryNode);

        return noDuplicateNode;
    }

    @Override
    public void createManualNodeAnyTime(PointCCoA coordinates) {
        double littleRadius = littleDiameter / 2;

        DeterminantIsMultiple determinantIsMultiple = new DeterminantIsMultipleClass(littleRadius, coordinates);
        boolean isMultipleNode = determinantIsMultiple.isCoordinateIsMultiple();

        if (!isMultipleNode) {
            this.storageManualNodes.addNodeAnyTime(coordinates);
        }
    }

    /*
     *
     * * - multiple nodes
     * *C - detectedCenterHexagonInclude
     * x - point include in hexagon
     *
     *
     * each new layer increases the accuracy by 2 times.
     *
     * permissibleErrorCoDirectivity = 30 degree
     *
     * angle between neighbor Nodes 60 degree
     * angle between Node vector and Car vector 30 degree
     *                                         Node       Car
     *                    ╱  ╲         ╱  ╲   ↗         ↗
     *                 ╱        ╲   ╱       /╲        ╱
     *                |           |       /    |    ╱
     *                |    *      |     *      |  ╱
     *                |           |    |       |╱
     *              ╱  ╲         ╱  ╲ /       ╱  ╲
     *           ╱        ╲   x     x| ╲   ╱        ╲
     *         |            |        |   |            |
     *         |     *      |  x  *C | ╱ |      *     |
     *         |            x        ╱   |            |
     *           ╲        /   ╲        /   ╲        /
     *              ╲  /         ╲   /        ╲   /
     *                |            x            |
     *                |     *      |     *      |
     *                |            |            |
     *                  ╲        /   ╲        /
     *                    ╲   /         ╲   /
     *
     *
     *
     * permissibleErrorCoDirectivity = 15 degree
     *
     *                                           ╱   ╲         ╱  ╲         ╱  ╲
     *                                        ╱         ╲   ╱        ╲   ╱        ╲
     *                                      |             |            |            |
     *                                      |     *       |     *      |     *      |
     *                                      |             |            |            |
     *                                     ╱  ╲         ╱  ╲         ╱  ╲         ╱   ╲
     *                                  ╱        ╲   ╱        ╲   ╱        ╲   ╱         ╲
     *                                |             |           |            |             |
     *                                |     *       |    *      |     *      |      *      |
     *                                |             |           |            |             |
     *                               ╱  ╲         ╱  ╲         ╱  ╲         ╱  ╲         ╱   ╲
     *                            ╱        ╲   ╱        ╲   x     x  ╲   ╱        ╲   ╱         ╲
     *                           |           |            |            |            |            |
     *                           |    *      |     *      |  x  *C     |      *     |      *     |
     *                           |           |            x            |            |            |
     *                            ╲         /  ╲        /   ╲        /   ╲        /   ╲        /
     *                               ╲   /        ╲  /         ╲   /        ╲   /        ╲  /
     *                                 |            |            x            |            |
     *                                 |     *      |     *      |     *      |     *      |
     *                                 |            |            |            |            |
     *                                   ╲       /    ╲        /   ╲        /   ╲        /
     *                                      ╲  /        ╲   /         ╲   /        ╲  /
     *                                       |             |            |            |
     *                                       |     *       |     *      |     *      |
     *                                       |             |            |            |
     *                                         ╲        /    ╲        /   ╲        /
     *                                            ╲  /          ╲   /       ╲   /
     *
     */


    private int determinationNumberLayers(double allowedErrorCoDirectivityRadian) {
        double degree180 = 3.1415926535;
        assert (-degree180 <= allowedErrorCoDirectivityRadian
                && allowedErrorCoDirectivityRadian <= degree180);

        double degree30InRadian = 0.523599;
        double maximumDeviationPossibleForLayer = degree30InRadian;

        for (int numberLayers = 1; numberLayers < 7; numberLayers++) {
            boolean isAnyPermissibleErrorLessThanAllowed =
                     maximumDeviationPossibleForLayer <= allowedErrorCoDirectivityRadian;
            if (isAnyPermissibleErrorLessThanAllowed) {
                int numberLayersHexagonTile = numberLayers;
                return numberLayersHexagonTile;
            }
            maximumDeviationPossibleForLayer /= 2;
        }
        boolean isProbablyTooManyLayers = true;
        boolean isProhibit = !isProbablyTooManyLayers;
        assert(isProhibit);
        return -1;
    }







    /*
     *
     * X - intersection magnetized timestamps round with lines parallels sides of impassable polygons
     * # - intersection lines parallels sides of impassable polygons. Move in # if # inside round. Find speed for
     *           # had a magnetized timestamp
     *
     *                            |                   /
     *                            |                 /
     *                            | impassable    /
     *                      !     |             /
     *                      !     |           /
     *                      !     |         /         /
     *                      !     |       /         /
     *                      !     |     /         /
     *                      !     |   /         /
     *                      !     | /   ↗     /
     *                      !     /   /     /
     *                      !       /     /
     *                      !     /     /
     *                      !   /     /
     *                      ! /    /
     *                      #     /  impassable
     *                    / !     |
     *                  /   !     |
     *                /     !     |  impassable
     *              /       !     |
     *                _____ !     |
     *             ╱       ╲!     |  impassable
     *            |   🚗    X     |
     *             ╲______╱ !     |
     *                      !     |
     *                      !     |
     *                      !     |
     *                      !     |
     *                      !     |
     *                      !     |
     */
    /*
    private List<Node> nonMultipleNodesNarrowRoads() { //FIXME NOW
        List<Node> intersectionRadiusVectorMoveWithParallelLineImpassablePolygons;
        List<Node> intersectionLinesImpassablePolygons;
    }*/






}
