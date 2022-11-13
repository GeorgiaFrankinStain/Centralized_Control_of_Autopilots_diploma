package com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.Hexagon;

import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.NetworkNodes;
import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.Node;
import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.NodeClass;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.ParametersMovingUnique;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeterminantNeighborsManualNodes implements StorageManualNodes {
    private static final Logger LOGGER = LogManager.getLogger(DeterminantNeighborsManualNodes.class);


    private HashMap<Integer, List<PointCCoA>> storage = new HashMap<>();

    private double littleDiameter;
    private NetworkNodes networkNodes;
    private ParametersMovingUnique parametersMovingUnique;

    public DeterminantNeighborsManualNodes(
            double littleDiameter,
            NetworkNodes networkNodes,
            ParametersMovingUnique parametersMovingUnique
    ) {
        this.littleDiameter = littleDiameter;
        this.networkNodes = networkNodes;
        this.parametersMovingUnique = parametersMovingUnique;
    }

    @Override
    public List<Node> neighboringMultipleNodes(
            List<Node> neighborsCentresNodes,
            PointCCoA coordinatePreviousNode,
            double timeTravelPreviousNode
    ) {
        List<Node> neighborsManualNodes = new ArrayList<>();

        for (Node current : neighborsCentresNodes) {
            int hashCode = determinationHashHexagon(current.getCoordinate());
            List<PointCCoA> storageForHexagonZone = this.storage.get(hashCode);

            if (storageForHexagonZone == null) {
                continue;
            }

            for (PointCCoA coordinateManual : storageForHexagonZone) {
                neighborsManualNodes.add(createNode(coordinateManual, coordinatePreviousNode, timeTravelPreviousNode));
            }
        }

        return neighborsManualNodes;
    }

    private Node createNode(
            PointCCoA coordinateManual,
            PointCCoA coordinatePreviousNode,
            double timeTravelPreviousNode
    ) {
        double distanceMove = coordinateManual.getDistanceToPoint(coordinatePreviousNode);
        double timeFromStartTravel =
                timeTravelPreviousNode + parametersMovingUnique.getTimeTravel(distanceMove);
        Node manualNode = new NodeClass(networkNodes, coordinateManual, timeFromStartTravel);

        return manualNode;
    }

    @Override
    public void addNodeAnyTime(PointCCoA coordinate) {
        int hashCode = determinationHashHexagon(coordinate);

        List<PointCCoA> storageForZoneHexagon = this.storage.get(hashCode);

        boolean isFirstTime = storageForZoneHexagon == null;
        if (isFirstTime) {
            storageForZoneHexagon = new ArrayList<>();
            this.storage.put(hashCode, storageForZoneHexagon);
            LOGGER.debug("addStorage with hashCode: {}", hashCode);
        }

        LOGGER.debug("addCoordinate: {}", coordinate);
        storageForZoneHexagon.add(coordinate);
    }

    private int determinationHashHexagon(PointCCoA coordinate) {
        DeterminantAddressHexagon determinant =
                new DeterminantAddressHexagonClass(coordinate, littleDiameter);
        return determinant.detectedHashCodeHexagonIncludeCoordinate();
    }
}
