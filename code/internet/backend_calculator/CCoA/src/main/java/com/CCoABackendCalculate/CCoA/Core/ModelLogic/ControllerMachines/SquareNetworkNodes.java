package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SquareNetworkNodes implements NetworkNodes {
    /*
     * //FIXME добавить фабрику, у которой все объекты в системе будут запрашивать сетку,
     *    создание узлов (чтобы в них можно было хранить данные о конкретном движущимся оъекте во время работы А*)
     *    должно быть привязанным к двжущемуся объекту (внутри должна храниться перменная), но в тоже время это все должно
     *    координироваться с размерностью сетки, как-то округляться*/

    private Node nodeWithMinimalScoreToEnd = null;
    private double angle90grad = Math.PI / 2;

    private Map<Node, Node> storageNodes = new HashMap<Node, Node>();
    private ParametersMovingUnique parametersMovingUnique;

    public SquareNetworkNodes(ParametersMovingUnique parametersMovingUnique) {
        assert(parametersMovingUnique != null);
        this.parametersMovingUnique = parametersMovingUnique;
    }

    @Override
    public List<Node> getNeighboringNodes(PointCCoA coordinates, double timeTravelFromStart) {
        Node firstNode = new NodeClass(this, coordinates, timeTravelFromStart);
        this.putInStorage(firstNode, firstNode);
        List<Node> nodes = new ArrayList<>();
        nodes.add(firstNode);
        return nodes;
    }

    /*
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
    public List<Node> getNeighboringNodes(Node currentNode) {


        //FIXME add test on minus radius

        double radius = parametersMovingUnique.getRadius();
        double halfSideSquare = radius * Math.sin(angle90grad / 2);
        double sideSquare = halfSideSquare * 2;
        PointCCoA positionMovingObject = currentNode.getCoordinate();


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
                    currentNode.getCoordinate(),
                    radius,
                    parametersMovingUnique,
                    currentNode
            );
        }/* else if (currentPositionOnSideSquare) {
            //FIXME
        }*/ else {
            return centerMachinesNoMultipleWithPointsSquare(
                    positionMovingObject,
                    sideSquare,
                    currentNode.getCoordinate(),
                    radius,
                    parametersMovingUnique,
                    currentNode
            );
        }


//        assert(false);
    }

    @Override
    public Node createNode(PointCCoA coordinates, double timeTravelFromStart) {
        return new NodeClass(this, coordinates, timeTravelFromStart);
    }

    @Override
    public void createManualNodeAnyTime(PointCCoA coordinates) {

    }


    private List<Node> coincidesCenterMachineWith(
            PointCCoA positionMovingObject,
            double sideSquare,
            PointCCoA coordinatAndCenterMachine,
            double radius,
            ParametersMovingUnique parametersMovingUnique,
            Node currentNode
    ) {

        double xMod = positionMovingObject.getX() % sideSquare;
        double xTopLeftNode = positionMovingObject.getX() - xMod;

        double yMod = positionMovingObject.getY() % sideSquare;
        double yTopLeftNode = positionMovingObject.getY() - yMod;

        PointCCoA firstDiagonalPointCCoA = new PointCCoAClass(
                coordinatAndCenterMachine.getX() + sideSquare,
                coordinatAndCenterMachine.getY() + sideSquare
        );
        PointCCoA firstNode = new PointCCoAClass(coordinatAndCenterMachine.getX() + sideSquare, coordinatAndCenterMachine.getY());


        List<Node> listNeightborint = new ArrayList<Node>();

        for (int i = 0; i < 4; i++) {
            PointCCoA coordinateNeighbor = firstNode.getRotateRelative(coordinatAndCenterMachine, angle90grad * i);
            double distanceToNeighbor = coordinateNeighbor.getDistanceToPoint(coordinatAndCenterMachine);
            double timeAdding = currentNode.getActualTimeTravelFromStart() + parametersMovingUnique.getTimeTravel(distanceToNeighbor);
            listNeightborint.add(new NodeClass(
                    this,
                    coordinateNeighbor,
                    timeAdding
            ));


            PointCCoA coordinateNeighborDiagonal =
                    firstDiagonalPointCCoA.getRotateRelative(coordinatAndCenterMachine, angle90grad * i);
            double distanceToNeighborDiagonal =
                    coordinateNeighborDiagonal.getDistanceToPoint(coordinatAndCenterMachine);

            double timeAddingDiagonal =
                    currentNode.getActualTimeTravelFromStart() + parametersMovingUnique.getTimeTravel(distanceToNeighborDiagonal);
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
            PointCCoA positionMovingObject,
            double sideSquare,
            PointCCoA coordinatAndCenterMachine,
            double radius,
            ParametersMovingUnique parametersMovingUnique,
            Node currentNode
    ) {
        double xMod = positionMovingObject.getX() % sideSquare;
        double xTopLeftNode = positionMovingObject.getX() - xMod;

        double yMod = positionMovingObject.getY() % sideSquare;
        double yTopLeftNode = positionMovingObject.getY() - yMod;

        PointCCoA topLeftNode = new PointCCoAClass(xTopLeftNode, yTopLeftNode);


        List<Node> resListNeightborint = new ArrayList<Node>();

        {
            PointCCoA coordinateNeighbor = new PointCCoAClass(topLeftNode.getX() + sideSquare, topLeftNode.getY());
            double distanceToNeighbor = coordinatAndCenterMachine.getDistanceToPoint(coordinateNeighbor);
            double timeAdding = currentNode.getActualTimeTravelFromStart() + parametersMovingUnique.getTimeTravel(distanceToNeighbor);
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
            PointCCoA coordinateNeighbor =
                    new PointCCoAClass(topLeftNode.getX() + sideSquare, topLeftNode.getY() + sideSquare);
            double distanceToNeighbor = coordinatAndCenterMachine.getDistanceToPoint(coordinateNeighbor);
            double timeAdding = currentNode.getActualTimeTravelFromStart() + parametersMovingUnique.getTimeTravel(distanceToNeighbor);
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
            PointCCoA coordinateNeighbor =
                    new PointCCoAClass(topLeftNode.getX(), topLeftNode.getY() + sideSquare);
            double distanceToNeighbor = coordinatAndCenterMachine.getDistanceToPoint(coordinateNeighbor);
            double timeAdding = currentNode.getActualTimeTravelFromStart() + parametersMovingUnique.getTimeTravel(distanceToNeighbor);
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
            PointCCoA coordinateNeighbor =
                    topLeftNode;
            double distanceToNeighbor = coordinatAndCenterMachine.getDistanceToPoint(coordinateNeighbor);
            double timeAdding = currentNode.getActualTimeTravelFromStart() + parametersMovingUnique.getTimeTravel(distanceToNeighbor);
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

