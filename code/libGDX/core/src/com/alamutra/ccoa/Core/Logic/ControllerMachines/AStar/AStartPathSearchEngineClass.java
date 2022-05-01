package com.alamutra.ccoa.Core.Logic.ControllerMachines.AStar;

import com.alamutra.ccoa.Core.Logic.ControllerMachines.NetworkNodes;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.Node;
import com.alamutra.ccoa.Core.Logic.ControllerMachines.NodeClass;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.IndexLayerClass;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoA;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoAClass;

import java.util.*;

public class AStartPathSearchEngineClass implements AStartPathSearchEngine {

    private Set<Node> closedNodes;
    private Set<Node> opened;
    private Map<Node, Double> fScoreHeuristic;
    private Map<Node, Node> keyNodeCameFrom;
    private Map<Node, Double> gScopeRealBestKnownDistanceFromStart;
    private NetworkNodes networkNodes;
    private PointCCoA destination;
    private ParametersMovingUnique parametersMovingUnique;
    private FootprintsSpaceTime footprintsSpaceTime;

    public AStartPathSearchEngineClass(PointCCoA start,
                                       PointCCoA destination,
                                       ParametersMovingUnique parametersMovingUnique,
                                       double timeAdding,
                                       NetworkNodes networkNodes,
                                       FootprintsSpaceTime footprintsSpaceTime
    ) {
        this.footprintsSpaceTime = footprintsSpaceTime;
        this.parametersMovingUnique = parametersMovingUnique;
        this.destination = destination;
        this.networkNodes = networkNodes;
        closedNodes = new HashSet<Node>();
        opened = new HashSet<Node>();
        fScoreHeuristic = new HashMap<Node, Double>();
        keyNodeCameFrom = new HashMap<Node, Node>();

        gScopeRealBestKnownDistanceFromStart = new HashMap<Node, Double>();

        Node startNode = new NodeClass(this.networkNodes, start, timeAdding);

        this.networkNodes.createManualNodeAnyTime(destination);



        gScopeRealBestKnownDistanceFromStart.put(startNode, 0.0); //FIXME not 0.0, replace in timeAdding

        double heuristic = parametersMovingUnique.getTimeTravel(startNode.getEstimateDistanceToDestinationHeuristicFunction(destination));
        double g = 0.0;
        double f = g + heuristic;
        fScoreHeuristic.put(startNode, f);
        opened.add(startNode);
    }

    @Override
    public PathCCoA getPath() {
        int j = 0;
        while (opened.size() > 0) {
            j++;

            Node currentNode = getNodeWithMinialMass(fScoreHeuristic); //FIXME 2 function in one


            boolean isDestinationNode = currentNode.getCoordinate().equals(destination);
            if (isDestinationNode) {
                return reconstructPath(keyNodeCameFrom, currentNode);
            }

            opened.remove(currentNode);
            fScoreHeuristic.remove(currentNode);
            closedNodes.add(currentNode);  //FIXME bag не видит появившееся пустое место

            List<Node> allNeightbors = currentNode.getNeighboringNodes();


            int i = 0;
            for (Node neighbor : allNeightbors) {
                i++;
/*                if (i > 40) {
                    break;

                }*/
                ProcessedNeighborsOfNearestCoDirectionalDestinationNode processNeighbors =
                        new ProcessedNeighborsOfNearestCoDirectionalDestinationNodeClass(currentNode, neighbor);
                processNeighbors.processed();

            }
        }

        /*
         * //FIXME the algorithm does not know how to determine the absence of a path.
         * If there are uncontrolled objects or buildings around the target
         * forever, then the algorithm will forever look for a way.
         */
        return null;
    }

    private PathCCoA reconstructPath(Map<Node, Node> keyNodeCameFrom, Node current) {

        PathCCoA pathCCoA = new PathCCoAClass();

        pathCCoA.addPoint(0, current.getCoordinate());

        while (keyNodeCameFrom.containsKey(current)) {
            current = keyNodeCameFrom.get(current);
            pathCCoA.addPoint(0, current.getCoordinate());
        }

        return pathCCoA;
    }

    private Node getNodeWithMinialMass(Map<Node, Double> map) {

        Node nodeWithMinScopes = null;
        double minScopes = Double.MAX_VALUE;


        for (Map.Entry<Node, Double> entry : map.entrySet()) {
            Double scopeOfCurrentNode = entry.getValue();

            if (scopeOfCurrentNode < minScopes) {
                nodeWithMinScopes = entry.getKey();
                minScopes = scopeOfCurrentNode;
            }

        }

        return nodeWithMinScopes;
    }

    private interface ProcessedNeighborsOfNearestCoDirectionalDestinationNode {
        public void processed();
    }

    private class ProcessedNeighborsOfNearestCoDirectionalDestinationNodeClass
            implements ProcessedNeighborsOfNearestCoDirectionalDestinationNode {
        private Node currentNode;
        private Node neighbor;

        private double realDistanceFromStartToCurrentNode;
        private double realDistanceToNeighbor;

        private double realDistanceToNeighborFromStartTroughCurrentNode;

        public ProcessedNeighborsOfNearestCoDirectionalDestinationNodeClass(Node currentNode, Node neighbor) {
            this.currentNode = currentNode;
            this.neighbor = neighbor;

            this.realDistanceFromStartToCurrentNode = gScopeRealBestKnownDistanceFromStart.get(currentNode);
            this.realDistanceToNeighbor = currentNode.getCoordinate().getDistanceToPoint(neighbor.getCoordinate());//FIXME глубоки вызовы

            this.realDistanceToNeighborFromStartTroughCurrentNode =
                    realDistanceFromStartToCurrentNode + realDistanceToNeighbor;
        }

        @Override
        public void processed() {

            boolean isMakesSenseToProcessTheNode = isMakesSenseToProcessTheNode();
            if (!isMakesSenseToProcessTheNode) {
                return;
            }

            boolean thisIsNotAStudiedNeighbor = !opened.contains(neighbor);
            boolean isFindMoreBestSolution = isFindMoreBestSolution();
            if (thisIsNotAStudiedNeighbor || isFindMoreBestSolution) {
                keyNodeCameFrom.put(neighbor, currentNode);
                gScopeRealBestKnownDistanceFromStart.put(neighbor, realDistanceToNeighborFromStartTroughCurrentNode);

                putNewHeuristicScopeForNeighbor();

                if (!opened.contains(neighbor)) {
                    opened.add(neighbor);
                }
            }
        }

        private void putNewHeuristicScopeForNeighbor() {
            double scoreHeuristic = neighbor.getActualTimeTravelFromStart()
                    + parametersMovingUnique.getTimeTravel(neighbor.getEstimateDistanceToDestinationHeuristicFunction(destination));
            fScoreHeuristic.put(neighbor, scoreHeuristic);
        }


        private boolean isMakesSenseToProcessTheNode() {

            if (closedNodes.contains(neighbor) || !this.isNeighborNodeIsAccess()) {
                return false;
            }

            return true;
        }

        private boolean isNeighborNodeIsAccess() {
            double timeAdding = parametersMovingUnique.getTimeTravel(realDistanceFromStartToCurrentNode);
            double timeStanding = parametersMovingUnique.getTimeTravel(realDistanceToNeighborFromStartTroughCurrentNode);

            PolygonCCoA occupiedPlace = spaceOccupiedDuringTheProcess();

            boolean isAccess = !footprintsSpaceTime.getIsSeatTakenSpaceTime(
                    occupiedPlace,
                    timeAdding,
                    timeAdding + timeStanding,
                    new IndexLayerClass(0)
            );

            return isAccess;
        }
        private PolygonCCoA spaceOccupiedDuringTheProcess() {
            double angle = angeleRotation();

            PolygonCCoA shapeCar = parametersMovingUnique.getShape();
            shapeCar.rotateRelative(new PointCCoAClass(0, 0), angle);

            PolygonCCoA occupiedPlace = shapeCar.getDeposeOn(currentNode.getCoordinate());
            return occupiedPlace;
        }

        private double angeleRotation() {
            PointCCoA neighborCenter = neighbor.getCoordinate();
            PointCCoA currentCenter = currentNode.getCoordinate();
            double angle = neighborCenter.getAngleRotareRelative(currentCenter);
            return angle;
        }

        private boolean isFindMoreBestSolution() {
            if (gScopeRealBestKnownDistanceFromStart.get(neighbor) == null) {
                return false;
            } else {
                return realDistanceToNeighborFromStartTroughCurrentNode < gScopeRealBestKnownDistanceFromStart.get(neighbor);
            }
        }
    }
}
