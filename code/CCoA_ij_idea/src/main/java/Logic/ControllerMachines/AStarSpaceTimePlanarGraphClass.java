package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.*;
import Logic.IndexLayerClass;
import Logic.MovingObjects.ParametersMoving;
import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;

import java.util.*;

public class AStarSpaceTimePlanarGraphClass implements AlhorithmFastFindPath {
    private NetworkNodes networkNodes;
    private FootprintsSpaceTime footprintsSpaceTime;

    public AStarSpaceTimePlanarGraphClass(NetworkNodes networkNodes, FootprintsSpaceTime footprintsSpaceTime) {
        this.networkNodes = networkNodes;
        this.footprintsSpaceTime = footprintsSpaceTime;
    }

    /**
     * created on pceudocode (https://gist.github.com/damienstanton/7de65065bf584a43f96a)
     *
     * @param start
     * @param destination
     * @param radiusMovingObject
     * @return
     */
    @Override
    public Path getPath(Point start, Point destination, double radiusMovingObject, ParametersMoving parametersMoving,
                        double timeAdding) {

        Set<Node> closedNodes = new HashSet<Node>();
        Set<Node> opened = new HashSet<Node>();
        Map<Node, Double> f_score = new HashMap<Node, Double>();
        Map<Node, Node> keyNodeCameFrom = new HashMap<Node, Node>();

        Map<Node, Double> gScopeRealBestKnownDistanceFromStart = new HashMap<Node, Double>();

        Node startNode = this.networkNodes.createFirstNode(start, radiusMovingObject, timeAdding); //FIXME FIRST radius and lenght definition


        gScopeRealBestKnownDistanceFromStart.put(startNode, 0.0);

        double hevristic = parametersMoving.timeTravel(startNode.getEstimatedDistanceToDestination(destination));
        double g = 0.0;
        double f = g + hevristic;
        f_score.put(startNode, f);
        opened.add(startNode);


        int j = 0;
        while (opened.size() > 0) {
            j++;

            Node currentNode = getNodeWithMinialMass(f_score); //FIXME 2 function in one


            boolean isDestinationNode = currentNode.getCoordinate().equals(destination);
            if (isDestinationNode) {
                return reconstructPath(keyNodeCameFrom, currentNode);
            }

            opened.remove(currentNode);
            f_score.remove(currentNode);
            closedNodes.add(currentNode);  //FIXME bag не видит появившееся пустое место

            List<Node> allNeightbors = currentNode.getNeighboringNodes(radiusMovingObject, parametersMoving);


            int i = 0;
            for (Node neighbor : allNeightbors) {
                i++;
/*                if (i > 40) {
                    break;

                }*/


                double realDistanceFromStartToCurrentNode = gScopeRealBestKnownDistanceFromStart.get(currentNode);
                double realDistanceToNeighbor = currentNode.getCoordinate().getDistanceToPoint(neighbor.getCoordinate());//FIXME глубоки вызовы
                double realDistanceToNeightborFromStartTroughtCurrentNode =
                        realDistanceFromStartToCurrentNode
                                + realDistanceToNeighbor;

                if (closedNodes.contains(neighbor)) {
                    continue;
                }
                if (!this.neightborNodeIsAccess(
                        neighbor,
                        currentNode,
                        radiusMovingObject,
                        parametersMoving.timeTravel(realDistanceFromStartToCurrentNode),
                        parametersMoving.timeTravel(realDistanceToNeightborFromStartTroughtCurrentNode),
                        parametersMoving
                )) {
                    continue;
                }




                boolean thisIsNotAStudiedNeighbor = !opened.contains(neighbor);
                boolean findMoreBestSolution;


                if (gScopeRealBestKnownDistanceFromStart.get(neighbor) == null) {
                    findMoreBestSolution = false;
                } else {
                    findMoreBestSolution =
                            realDistanceToNeightborFromStartTroughtCurrentNode < gScopeRealBestKnownDistanceFromStart.get(neighbor);
                }
                if (thisIsNotAStudiedNeighbor || findMoreBestSolution) {
                    keyNodeCameFrom.put(neighbor, currentNode);


                    gScopeRealBestKnownDistanceFromStart.put(neighbor, realDistanceToNeightborFromStartTroughtCurrentNode);

//                    double g = realDistanceToNeightborFromStartTroughtCurrentNode + neighbor.getEstimatedDistanceToDestination(destination);
                    double score =
                            neighbor.getTimeTravelFromStart()
                                    + parametersMoving.timeTravel(neighbor.getEstimatedDistanceToDestination(destination));
                    f_score.put(neighbor,
                            score);


                    if (!opened.contains(neighbor)) {
                        opened.add(neighbor);
                    }
                }
            }
        }

        return null;
    }


    private boolean neightborNodeIsAccess(
            Node neighborNode,
            Node currentNode,
            double radiusMovingObject,
            double timeAdding,
            double timeStanding,
            ParametersMoving parametersMoving
    ) {
        PolygonExtended occupiedPlace = spaceOccupiedDuringTheProcess(
                neighborNode,
                currentNode,
                radiusMovingObject
        );


        boolean isAccess = !this.footprintsSpaceTime.getIsSeatTakenSpaceTime(
                occupiedPlace,
                timeAdding,
                timeAdding + timeStanding,
                new IndexLayerClass(0)
        );


        return isAccess;
    }

    private PolygonExtended spaceOccupiedDuringTheProcess(
            Node neighborNode,
            Node currentNode,
            double radiusMovingObject
    ) {
        Point neighborCenter = neighborNode.getCoordinate();
        Point currentCenter = currentNode.getCoordinate();

        double distance = neighborCenter.getDistanceToPoint(currentCenter);

        double widthRectangle = distance + radiusMovingObject; //FIXME cut angle, hexcagon is good, square more bed
        double heightRectangle = radiusMovingObject * 2;

        PolygonExtended rectangle = new PolygonExtendedClass();
        rectangle.addPoint(new PointClass(0, heightRectangle / 2));
        rectangle.addPoint(new PointClass(0, -heightRectangle / 2));
        rectangle.addPoint(new PointClass(widthRectangle, -heightRectangle / 2));
        rectangle.addPoint(new PointClass(widthRectangle, heightRectangle / 2));



        double angle = neighborCenter.getAngleRotareRelative(currentCenter);
        rectangle.rotateRelative(new PointClass(0, 0), angle);

        rectangle.deposeOn(currentCenter);


        return rectangle;
    }

    /*    private <K, V> K getKey(Map<K, V> map, V value) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                if (entry.getValue().equals(value)) {
                    return entry.getKey();
                }
            }
            return null;
        }*/
    private Path reconstructPath(Map<Node, Node> keyNodeCameFrom, Node current) {

        Path path = new PathClass();

        path.addPoint(0, current.getCoordinate());

        while (keyNodeCameFrom.containsKey(current)) {
            current = keyNodeCameFrom.get(current);
            path.addPoint(0, current.getCoordinate());
        }

        return path;
    }

    private Node getNodeWithMinialMass(Map<Node, Double> map) { //FIXME FIRST add test
/*//        Map<String,String> map = new HashMap<>();
        Map.Entry<Double, Node> entry = map.entrySet().iterator().next();
//        String key = entry.getKey();
        map.remove(entry.getKey());

        return entry.getValue();*/

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
}
