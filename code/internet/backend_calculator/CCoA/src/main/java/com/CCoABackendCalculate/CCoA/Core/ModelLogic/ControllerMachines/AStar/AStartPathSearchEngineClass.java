package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AStar;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.NetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.Node;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.NodeClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayerClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.PathCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.PathCCoAClass;

import java.util.*;

public class AStartPathSearchEngineClass implements AStartPathSearchEngine {

    private Set<Node> closedNodes = new HashSet<Node>();
    private Set<Node> opened = new HashSet<Node>();
    private HeuristicStorage heuristicStorage;

    private Map<Node, Node> keyNodeCameFrom = new HashMap<Node, Node>();
    private Map<Node, Double> gScopeRealBestKnownDistanceFromStart = new HashMap<Node, Double>();
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

        this.heuristicStorage = new HeuristicStorageClass(this.parametersMovingUnique, this.destination);

        this.networkNodes.createManualNodeAnyTime(destination);

        Node startNode = new NodeClass(this.networkNodes, start, timeAdding);
        gScopeRealBestKnownDistanceFromStart.put(startNode, 0.0); //FIXME not 0.0, replace in timeAdding

        this.heuristicStorage.addNode(startNode);
        opened.add(startNode);
    }

    @Override
    public PathCCoA getPath() {
        while (opened.size() > 0) {
            Node currentNode = this.heuristicStorage.getNodeWithBestHeuristicScope();

            boolean isDestinationNode = currentNode.getCoordinate().equals(destination);
            if (isDestinationNode) {
                return reconstructPath(keyNodeCameFrom, currentNode);
            }

            moveNodeToClosedList(currentNode);

            List<Node> allNeighbors = currentNode.getNeighboringNodes();
            for (Node neighbor : allNeighbors) {
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

    private void moveNodeToClosedList(Node currentNode) {
        opened.remove(currentNode);
        this.heuristicStorage.removeNode(currentNode);
        closedNodes.add(currentNode);
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

                heuristicStorage.addNode(currentNode, neighbor);
            }

            if (thisIsNotAStudiedNeighbor) {
                opened.add(neighbor);
            }
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
