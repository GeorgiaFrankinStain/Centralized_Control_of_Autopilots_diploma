package com.alamutra.ccoa.Logic.ControllerMachines;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Point;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointClass;
import com.alamutra.ccoa.Logic.GlobalVariable;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;

import java.util.*;

public class SquareNetworkNodes implements NetworkNodes {
    /*
     * //FIXME добавить фабрику, у которой все объекты в системе будут запрашивать сетку,
     *    создание узлов (чтобы в них можно было хранить данные о конкретном движущимся оъекте во время работы А*)
     *    должно быть привязанным к двжущемуся объекту (внутри должна храниться перменная), но в тоже время это все должно
     *    координироваться с размерностью сетки, как-то округляться*/

    private Node nodeWithMinimalScoreToEnd = null;
    private double angle90grad = Math.PI / 2;

    private Map<Node, Node> storageNodes = new HashMap<Node, Node>();

    public SquareNetworkNodes() {
    }

    @Override
    public Node createFirstNode(Point coordinat, double radius, double timeTravelFromStart) {
        Node firstNode = new NodeClass(this, coordinat, timeTravelFromStart);
        this.putInStorage(firstNode, firstNode);
        return firstNode;
    }
    @Override
    public void cleanInfoAbout(Node node) {
        this.storageNodes.remove(node);
    }



    @Override
    public void addNode(Node node) {
        this.putInStorage(node, node);
    }

    /**
     * LINK_dBKSoC (link (find text) on documentation in other place)
     * <p>
     * <p>
     * +---+
     * | X |    -   this is machine with center X
     * +---+
     * <p>
     * x - point in reslut list neighboring
     * X - center, coordinate machine (no neighbor)
     * + - machines edges
     * <p>
     * <p>
     * CASE 1: center machines coincides with top left nodes square of network nodes
     * <p>
     * Neigboring in case: top left nodes of neighboring square of networknodes
     * <p>
     * x4------------x3------------x2
     * |                           |
     * |                           |
     * |      +-------------+      |
     * |      |             |      |
     * |      |             |      |
     * x5     |      X      |      x1
     * |      |             |      |
     * |      |             |      |
     * |      +-------------+      |
     * |                           |
     * |                           |
     * x6------------x7------------x8
     * <p>
     * <p>
     * <p>
     * CASE 2: center machines coincides with side square of network nodes
     * <p>
     * ---------------------------------------------------------
     * |             |             |             |             |
     * |             |             |             |             |
     * |             |             |             |             |
     * |             |             |             |             |
     * |             |      +-------------+      |             |
     * |-------------x----- |      x      | -----x-------------|
     * |             |      |             |      |             |
     * |             |      |      X      |      |             |
     * |             |      |             |      |             |
     * |             |      |             |      |             |
     * |             |      +-------------+      |             |
     * --------------x-------------x-------------x--------------
     * //FIXME add vertical points
     * <p>
     * CASE 3: center machines no multiple with points square of network nodes
     * <p>
     * x2 - center square of network nodes
     * X - center Machine, point where coordinates of machine are applied
     * <p>
     * -----------------------------
     * |                           |
     * |                           |
     * |                           |
     * |                           |
     * |            +-------------+|
     * |            x2            |x1
     * |            |             ||
     * |            |      X      ||
     * |            |             ||
     * |            |             ||
     * |            +-------------+|
     * -------------x3------------x0
     */
    @Override
    public List<Node> getNeightboringNodes(Node currentNode, Point coordinat, double radius, ParametersMoving parametersMoving) {


        //FIXME add test on minus radius


        double halfSideSquare = radius * Math.sin(angle90grad / 2);
        double sideSquare = halfSideSquare * 2;
        Point positionMovingObject = coordinat;


        //FIXME add limitation map (height, width)


        //FIXME NOW tested on access

        //FIXME TEST ADD, where prohod exist in no throught node

        double xMod = positionMovingObject.getX() % sideSquare;
        boolean xMultipleSideSquare = GlobalVariable.equalsNumber(xMod, 0.0);

        double yMod = positionMovingObject.getX() % sideSquare;
        boolean yMultipleSideSquare = GlobalVariable.equalsNumber(yMod, 0.0);


        boolean currentPositionOnNode = xMultipleSideSquare && yMultipleSideSquare;
        boolean currentPositionOnSideSquare = xMultipleSideSquare || yMultipleSideSquare;


        if (currentPositionOnNode) {
            return coincidesCenterMachineWith(
                    positionMovingObject,
                    sideSquare,
                    coordinat,
                    radius,
                    parametersMoving,
                    currentNode
            );
        }/* else if (currentPositionOnSideSquare) {
            //FIXME
        }*/ else {
            return centerMachinesNoMultipleWithPointsSquare(
                    positionMovingObject,
                    sideSquare,
                    coordinat,
                    radius,
                    parametersMoving,
                    currentNode
            );
        }


//        assert(false);
    }


    private List<Node> coincidesCenterMachineWith(
            Point positionMovingObject,
            double sideSquare,
            Point coordinatAndCenterMachine,
            double radius,
            ParametersMoving parametersMoving,
            Node currentNode
    ) {

        double xMod = positionMovingObject.getX() % sideSquare;
        double xTopLeftNode = positionMovingObject.getX() - xMod;

        double yMod = positionMovingObject.getY() % sideSquare;
        double yTopLeftNode = positionMovingObject.getY() - yMod;

        Point firstDiagonalPoint = new PointClass(
                coordinatAndCenterMachine.getX() + sideSquare,
                coordinatAndCenterMachine.getY() + sideSquare
        );
        Point firstNode = new PointClass(coordinatAndCenterMachine.getX() + sideSquare, coordinatAndCenterMachine.getY());


        List<Node> listNeightborint = new ArrayList<Node>();

        for (int i = 0; i < 4; i++) {
            Point coordinateNeighbor = firstNode.getRotateRelative(coordinatAndCenterMachine, angle90grad * i);
            double distanceToNeighbor = coordinateNeighbor.getDistanceToPoint(coordinatAndCenterMachine);
            double timeAdding = currentNode.getTimeTravelFromStart() + parametersMoving.timeTravel(distanceToNeighbor);
            listNeightborint.add(new NodeClass(
                    this,
                    coordinateNeighbor,
                    timeAdding
            ));


            Point coordinateNeighborDiagonal =
                    firstDiagonalPoint.getRotateRelative(coordinatAndCenterMachine, angle90grad * i);
            double distanceToNeighborDiagonal =
                    coordinateNeighborDiagonal.getDistanceToPoint(coordinatAndCenterMachine);

            double timeAddingDiagonal =
                    currentNode.getTimeTravelFromStart() + parametersMoving.timeTravel(distanceToNeighborDiagonal);
            listNeightborint.add(new NodeClass(
                    this,
                    coordinateNeighborDiagonal,
                    timeAddingDiagonal
            ));
        }


        return listNeightborint;
    }

    private List<Node> centerMachinesCoincidesWithSideSquare() {
        return null;
    }

    private List<Node> centerMachinesNoMultipleWithPointsSquare(
            Point positionMovingObject,
            double sideSquare,
            Point coordinatAndCenterMachine,
            double radius,
            ParametersMoving parametersMoving,
            Node currentNode
    ) {
        double xMod = positionMovingObject.getX() % sideSquare;
        double xTopLeftNode = positionMovingObject.getX() - xMod;

        double yMod = positionMovingObject.getY() % sideSquare;
        double yTopLeftNode = positionMovingObject.getY() - yMod;

        Point topLeftNode = new PointClass(xTopLeftNode, yTopLeftNode);


        List<Node> resListNeightborint = new ArrayList<Node>();

        {
            Point coordinateNeighbor = new PointClass(topLeftNode.getX() + sideSquare, topLeftNode.getY());
            double distanceToNeighbor = coordinatAndCenterMachine.getDistanceToPoint(coordinateNeighbor);
            double timeAdding = currentNode.getTimeTravelFromStart() + parametersMoving.timeTravel(distanceToNeighbor);
            Node addingInStorageNode = this.addingInStorageIfNoElement(
                    new NodeClass(
                            this,
                            coordinateNeighbor,
                            timeAdding
                    )
            );
            resListNeightborint.add(addingInStorageNode);
        }
        {
            Point coordinateNeighbor =
                    new PointClass(topLeftNode.getX() + sideSquare, topLeftNode.getY() + sideSquare);
            double distanceToNeighbor = coordinatAndCenterMachine.getDistanceToPoint(coordinateNeighbor);
            double timeAdding = currentNode.getTimeTravelFromStart() + parametersMoving.timeTravel(distanceToNeighbor);
            Node addingInStorageNode = this.addingInStorageIfNoElement(
                    new NodeClass(
                            this,
                            coordinateNeighbor,
                            timeAdding
                    )
            );
            resListNeightborint.add(addingInStorageNode);
        }
        {
            Point coordinateNeighbor =
                    new PointClass(topLeftNode.getX(), topLeftNode.getY() + sideSquare);
            double distanceToNeighbor = coordinatAndCenterMachine.getDistanceToPoint(coordinateNeighbor);
            double timeAdding = currentNode.getTimeTravelFromStart() + parametersMoving.timeTravel(distanceToNeighbor);
            Node addingInStorageNode = this.addingInStorageIfNoElement(
                    new NodeClass(
                            this,
                            coordinateNeighbor,
                            timeAdding
                    )
            );
            resListNeightborint.add(addingInStorageNode);
        }
        {
            Point coordinateNeighbor =
                    topLeftNode;
            double distanceToNeighbor = coordinatAndCenterMachine.getDistanceToPoint(coordinateNeighbor);
            double timeAdding = currentNode.getTimeTravelFromStart() + parametersMoving.timeTravel(distanceToNeighbor);
            Node addingInStorageNode = this.addingInStorageIfNoElement(
                    new NodeClass(
                            this,
                            coordinateNeighbor,
                            timeAdding
                    )
            );
            resListNeightborint.add(addingInStorageNode);
        }

        return resListNeightborint;
    }

    private Node addingInStorageIfNoElement(Node justCreatedNode) {
        boolean previouslyCreated = this.storageNodes.containsKey(justCreatedNode);
        if (previouslyCreated) {
            return this.storageNodes.get(justCreatedNode);
        } else {
//            this.storageNodes.put(justCreatedNode, justCreatedNode);
            this.putInStorage(justCreatedNode, justCreatedNode);
            return justCreatedNode;
        }
    }


    private void putInStorage(Node key, Node value) {

        this.storageNodes.put(key, value);
    }

}
