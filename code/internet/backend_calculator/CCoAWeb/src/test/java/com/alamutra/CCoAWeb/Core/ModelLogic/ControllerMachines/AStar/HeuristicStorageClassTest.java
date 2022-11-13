package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.AStar;

import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.Hexagon.HexagonTile;
import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.NetworkNodes;
import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.Node;
import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.NodeClass;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.Square20ParametersMovingUnique;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeuristicStorageClassTest {

    private ParametersMovingUnique car20 = new Square20ParametersMovingUnique();

    /*
     *     B3
     *     *         * B2
     *                        * D
     *
     *   A *         * B1
     *
     * A-B1-D < A-B2-D
     *
     * B1 is best node
     */
    @Test
    void getNodeWithBestHeuristicScope_nearestDestinationNodeIsNotBestNode() {
        double degree60 = 1.0472;
        NetworkNodes networkNodes = new HexagonTile(degree60, car20);
        Node a = new NodeClass(networkNodes, new PointCCoAClass(0, 0), 0.0);
        Node b1 = new NodeClass(networkNodes, new PointCCoAClass(10, 0), 1.0);
        Node b2 = new NodeClass(networkNodes, new PointCCoAClass(10, 10), 1.0 * Math.sqrt(2));
        Node b3 = new NodeClass(networkNodes, new PointCCoAClass(0, 10), 1.0);

        PointCCoA destination = new PointCCoAClass(15, 6.666);

        HeuristicStorage heuristicStorage = new HeuristicStorageClass(car20, destination);
        heuristicStorage.addNode(a, b1);
        heuristicStorage.addNode(a, b2);
        heuristicStorage.addNode(a, b3);

        Node actual = heuristicStorage.getNodeWithBestHeuristicScope();

        assertEquals(b1, actual);
    }

    /*
     *         B1    B2
     *   A *   *     *      * D
     *
     * A-B2-D < A-B1-D
     *
     * B2 is best node
     */
    @Test
    void getNodeWithBestHeuristicScope_onLine() {
        double degree60 = 1.0472;
        NetworkNodes networkNodes = new HexagonTile(degree60, car20);
        Node a = new NodeClass(networkNodes, new PointCCoAClass(0, 0), 0.0);
        Node b1 = new NodeClass(networkNodes, new PointCCoAClass(10, 0), 1.0);
        Node b2 = new NodeClass(networkNodes, new PointCCoAClass(20, 0), 2.0);

        PointCCoA destination = new PointCCoAClass(30, 0);

        HeuristicStorage heuristicStorage = new HeuristicStorageClass(car20, destination);
        heuristicStorage.addNode(a, b1);
        heuristicStorage.addNode(a, b2);

        Node actual = heuristicStorage.getNodeWithBestHeuristicScope();

        assertEquals(b2, actual);
    }
}