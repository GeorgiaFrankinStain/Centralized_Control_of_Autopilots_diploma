package com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.Hexagon;

import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.NetworkNodes;
import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.Node;
import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.NodeClass;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.*;

import java.util.ArrayList;
import java.util.List;

public class DeterminantNeighborsNodesClass implements DeterminantNeighborsNodes {
    private PointCCoA coordinates;
    private double timeTravelFromStart;
    private NetworkNodes networkNodes;
    private double littleDiameter;
    private int numberLayersHexagonTile;
    private HexagonVectors hexagonVectors;
    private double intervalTimestamps;

    public DeterminantNeighborsNodesClass(
            PointCCoA coordinates,
            double timeTravelFromStart,
            NetworkNodes networkNodes,
            double littleDiameter,
            int numberLayersHexagonTile,
            double intervalTimestamps) {

        assert (littleDiameter != Double.POSITIVE_INFINITY);
        assert (littleDiameter != Double.NEGATIVE_INFINITY);
        assert (littleDiameter != Double.NaN);
        assert (0 < littleDiameter);

        this.coordinates = coordinates;
        this.timeTravelFromStart = timeTravelFromStart;
        this.networkNodes = networkNodes;
        this.littleDiameter = littleDiameter;
        this.numberLayersHexagonTile = numberLayersHexagonTile;
        this.intervalTimestamps = intervalTimestamps;
    }

    @Override
    public List<Node> neighboringMultipleNodes() {
        DeterminantAddressHexagon determinantAddressHexagon =
                new DeterminantAddressHexagonClass(coordinates, littleDiameter);
        PointCCoA centerHexagon = determinantAddressHexagon.detectedCenterHexagonIncludeCoordinate();
        determinationHexagonForCreatedLayer();

        return getAllMultiples(centerHexagon);
    }


    /*
     *
     * V - vectors to vertex current hexagon
     * NN - vectors to neighbor nodes
     *
     *                    ╱  ╲         ╱  ╲
     *                 ╱        ╲   ╱        ╲
     *                |           |            |
     *                |    *      |     *      |
     *                |           |    ↗ NN    |
     *              ╱  ╲         ╱^ ╲ /       ╱  ╲
     *           ╱        ╲   /  V|  / ╲   ╱        ╲
     *         |            |     | /    |            |
     *         |     *      |     *C     |      *     |
     *         |            |            |            |
     *           ╲        /   ╲        /   ╲        /
     *              ╲  /         ╲   /        ╲   /
     *                |            x            |
     *                |     *      |     *      |
     *                |            |            |
     *                  ╲        /   ╲        /
     *                    ╲   /         ╲   /
     *
     */
    private void determinationHexagonForCreatedLayer() {
        this.hexagonVectors = new HexagonVectorsToNeighborsHexagonTile(littleDiameter);
    }

    private List<Node> getAllMultiples(PointCCoA centerHexagon) {
        List<Node> allNeighboringNodes = new ArrayList<>();
        for (int layer = 1; layer <= numberLayersHexagonTile; layer++) {
            ForeachLinesLayer foreach = new ForeachLinesLayerClass(centerHexagon, layer);
            List<Node> layersNodes = foreach.getNodesFromLayer();
            allNeighboringNodes.addAll(layersNodes);
        }

        return allNeighboringNodes;
    }




    private interface ForeachLinesLayer {
        public List<Node> getNodesFromLayer();
    }

    private class ForeachLinesLayerClass implements ForeachLinesLayer {


        private PointCCoA centerHexagon;
        private int layer;

        private int numberVectorsInLine;
        private PointCCoA startPoint;
        private List<Node> nodes = new ArrayList<>();
        private PointCCoA vectorNextLine;

        public ForeachLinesLayerClass(PointCCoA centerHexagon, int layer) {
            this.centerHexagon = centerHexagon;
            this.layer = layer;
            this.numberVectorsInLine = layer;

        }

        @Override
        public List<Node> getNodesFromLayer() {
            PointCCoA vectorToLeftNode = new PointCCoAClass(-littleDiameter, 0);

            PointCCoA vectorToLeftLayerNode = vectorToLeftNode.getMultipliedVector(layer);

            PointCCoA startLeftPoint = centerHexagon.getDeposeOn(vectorToLeftLayerNode);
            this.startPoint = startLeftPoint;
            this.vectorNextLine = hexagonVectors.getVector(0);

            int numberAllSidesHexagon = 6;
            for (int numberLine = 0; numberLine < numberAllSidesHexagon; numberLine++) {
                getNodesFromLineLayer(this.vectorNextLine);
                int cycleIndexNextLineVector = (numberLine + 1) % 6;
                this.vectorNextLine = hexagonVectors.getVector(cycleIndexNextLineVector);
            }

            return this.nodes;
        }

        private void getNodesFromLineLayer(PointCCoA vectorNextNodes) {
            PointCCoA currentLinePoint = startPoint.clone();

            for (int i = 0; i < numberVectorsInLine; i++) {
                Node newNode = createNode(currentLinePoint);
                this.nodes.add(newNode);

                currentLinePoint = currentLinePoint.getDeposeOn(vectorNextNodes);
            }

            this.startPoint = currentLinePoint;
        }

        private Node createNode(PointCCoA centerNode) {

            double timeStamp = getTimeStampForMultipleNodes();

            return new NodeClass(networkNodes, centerNode, timeStamp);
        }

        private double getTimeStampForMultipleNodes() {
            double nextInterval = timeTravelFromStart - timeTravelFromStart % intervalTimestamps + intervalTimestamps;
            return nextInterval;
        }

    }


}
