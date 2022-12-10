package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AStar;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.Node;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;

import java.util.HashMap;
import java.util.Map;

public class HeuristicStorageClass implements HeuristicStorage {

    private Map<Node, Double> fScoreHeuristic = new HashMap<Node, Double>();

    private ParametersMovingUnique parametersMovingUnique;
    private PointCCoA destination;

    public HeuristicStorageClass(ParametersMovingUnique parametersMovingUnique, PointCCoA destination) {
        this.parametersMovingUnique = parametersMovingUnique;
        this.destination = destination;
    }

    @Override
    public Node getNodeWithBestHeuristicScope() {
        return getNodeWithMinimalMass();
    }

    @Override
    public void addNode(Node node, Node neighbor) {
        double heuristicDistance = neighbor.getEstimateDistanceToDestinationHeuristicFunction(destination);
        double heuristicTime = parametersMovingUnique.getTimeTravel(heuristicDistance);
        double realDistanceFromPreviousNode = neighbor.getCoordinate().getDistanceToPoint(node.getCoordinate());
        double realTimeFromPreviousNode = parametersMovingUnique.getTimeTravel(realDistanceFromPreviousNode);
        //double scoreHeuristic = neighbor.getActualTimeTravelFromStart() + heuristicTime; //the original version from the creator of the algorithm AStar
        double scoreHeuristic = realTimeFromPreviousNode + heuristicTime;
        fScoreHeuristic.put(neighbor, scoreHeuristic);
    }

    @Override
    public void addNode(Node node) {
        double heuristicDistance = node.getEstimateDistanceToDestinationHeuristicFunction(destination);
        double heuristicTime = parametersMovingUnique.getTimeTravel(heuristicDistance);
        double scoreHeuristic = node.getActualTimeTravelFromStart() + heuristicTime; //the original version from the creator of the algorithm AStar
        fScoreHeuristic.put(node, scoreHeuristic);
    }

    @Override
    public void removeNode(Node node) {
        fScoreHeuristic.remove(node);
    }

    private Node getNodeWithMinimalMass() {

        Node nodeWithMinScopes = null;
        double minScopes = Double.MAX_VALUE;


        for (Map.Entry<Node, Double> entry : fScoreHeuristic.entrySet()) {
            Double scopeOfCurrentNode = entry.getValue();

            if (scopeOfCurrentNode < minScopes) {
                nodeWithMinScopes = entry.getKey();
                minScopes = scopeOfCurrentNode;
            }

        }

        return nodeWithMinScopes;
    }
}
