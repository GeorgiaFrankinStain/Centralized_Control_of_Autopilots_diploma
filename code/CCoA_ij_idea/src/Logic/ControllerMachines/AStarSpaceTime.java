package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.*;
import Logic.LevelLayerClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import Logic.MovingObjects.PathClass;

import java.util.*;

public class AStarSpaceTime implements AlhorithmFastFindPath {
    private NetworkNodes networkNodes;
    private FootprintsSpaceTime footprintsSpaceTime;

    public AStarSpaceTime(NetworkNodes networkNodes, FootprintsSpaceTime footprintsSpaceTime) {
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
    public Path getPath(Point start, Point destination, double radiusMovingObject, MovingObject movingObject) {

        Set<Node> closedNodes = new HashSet<Node>();
        Set<Node> opened = new HashSet<Node>();
        Map<Node, Double> f_score = new HashMap<Node, Double>();
        Map<Node, Node> keyNodeCameFrom = new HashMap<Node, Node>();

        Map<Node, Double> gScopeRealBestKnownDistanceFromStart = new HashMap<Node, Double>();

        Node startNode = this.networkNodes.createNode(start, radiusMovingObject); //FIXME FIRST radius and lenght definition


        gScopeRealBestKnownDistanceFromStart.put(startNode, 0.0);

        double hevristic = startNode.getEstimatedToDestination(destination);
        double g = 0.0;
        double f = g + hevristic;
        f_score.put(startNode, f);
        opened.add(startNode);
        System.out.println(startNode + " " + f);


        int j = 0;
        while (opened.size() > 0) {
            System.out.println("==================================");
            j++;
/*            if (j > 40) {
                System.out.println("break cicle general");
                break;
            }*/

            Node currentNode = getNodeWithMinialMassAndDeleteFromQueue(f_score); //FIXME 2 function in one
            System.out.println("currentNode: " + currentNode);


            boolean isDestinationNode = currentNode.getCoordinate().equals(destination);
            System.out.println("isDestinationNode: " + isDestinationNode);
            if (isDestinationNode) {
                return reconstructPath(keyNodeCameFrom, currentNode);
            }

            System.out.println("closedBefore: " + closedNodes);
            System.out.println("openedBefore: " + opened);
            opened.remove(currentNode);
            f_score.remove(currentNode);
            closedNodes.add(currentNode);
            System.out.println("closedNodes: " + closedNodes);
            System.out.println("opened: " + opened);

            List<Node> allNeightbors = currentNode.getNeighboringNodes(radiusMovingObject);

            System.out.println("allNeightbors: " + allNeightbors);

            int i = 0;
            for (Node neighbor : allNeightbors) {
                System.out.println(" - - - - - - - - - - - - - - - - ");
                i++;
/*                if (i > 40) {
                    System.out.println("break cicle neighbor");
                    break;

                }*/


                double realDistanceFromStartToCurrentNode = gScopeRealBestKnownDistanceFromStart.get(currentNode);
                double realDistanceToNeighbor = currentNode.getCoordinate().getDistanceToPoint(neighbor.getCoordinate());//FIXME глубоки вызовы
                double realDistanceToNeightborFromStartTroughtCurrentNode =
                        realDistanceFromStartToCurrentNode
                                + realDistanceToNeighbor;


                if (closedNodes.contains(neighbor)) {
                    System.out.println("neigboring in closed list, continue");
                    continue;
                }
                if (!this.neightborNodeIsAccess(
                        neighbor,
                        currentNode,
                        radiusMovingObject,
                        movingObject.timeTravel(realDistanceFromStartToCurrentNode),
                        movingObject.timeTravel(realDistanceToNeightborFromStartTroughtCurrentNode)
                )) {
                    System.out.println("no access to neighbor, continue");
                    continue;
                }



                System.out.println("realDistanceToNeightborFromStartTroughtCurrentNode: " + realDistanceToNeightborFromStartTroughtCurrentNode);
                System.out.println("gScope " + gScopeRealBestKnownDistanceFromStart.get(currentNode));
                System.out.println("distance " + currentNode.getCoordinate().getDistanceToPoint(neighbor.getCoordinate()));

                boolean thisIsNotAStudiedNeighbor = !opened.contains(neighbor);
                System.out.println("thisIsNotAStudiedNeighbor: " + thisIsNotAStudiedNeighbor);
                boolean findMoreBestSolution;


                if (gScopeRealBestKnownDistanceFromStart.get(neighbor) == null) {
                    findMoreBestSolution = false;
                } else {
                    findMoreBestSolution =
                            realDistanceToNeightborFromStartTroughtCurrentNode < gScopeRealBestKnownDistanceFromStart.get(neighbor);
                }
                System.out.println("findMoreBestSolution: " + findMoreBestSolution);
                if (thisIsNotAStudiedNeighbor || findMoreBestSolution) {
                    keyNodeCameFrom.put(neighbor, currentNode);

                    System.out.println("neighbor " + neighbor + " cameFrom " + currentNode);

                    gScopeRealBestKnownDistanceFromStart.put(neighbor, realDistanceToNeightborFromStartTroughtCurrentNode);
                    System.out.println("neighbor "
                                               + neighbor
                                               + " realDistanceToNeightborFromStartTroughtCurrentNode "
                                               + realDistanceToNeightborFromStartTroughtCurrentNode);

                    f_score.put(neighbor,
                            realDistanceToNeightborFromStartTroughtCurrentNode + neighbor.getEstimatedToDestination(destination));
                    System.out.println("neighbor " + neighbor
                                               + "real  " + realDistanceToNeightborFromStartTroughtCurrentNode
                                               + " estimated" + neighbor.getEstimatedToDestination(destination));


                    if (!opened.contains(neighbor)) {
                        System.out.println("neightbor not contains in opened; add it in");
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
            double timeStanding
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
                new LevelLayerClass(0)
        );

        System.out.println("++++ ++++ " + currentNode + " " + neighborNode + " ++++ " + occupiedPlace);

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

        System.out.println("spaceOccupiedDuringTheProcess " + rectangle);

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

    private Node getNodeWithMinialMassAndDeleteFromQueue(Map<Node, Double> map) { //FIXME FIRST add test
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
