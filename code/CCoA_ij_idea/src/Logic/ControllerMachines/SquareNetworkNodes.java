package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PointClass;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

public class SquareNetworkNodes implements NetworkNodes {

    private double angle90grad = Math.PI / 2;
    private double dimension; //FIXME dimension no use

    public SquareNetworkNodes(double dimension) {
        this.dimension = dimension;
    }

    @Override
    public Node createNode(Point coordinat, double radius) {
        return new NodeClass(this, coordinat);
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
    public List<Node> getNeightboringNodes(Point coordinat, double radius) {


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
                    radius
            );
        }/* else if (currentPositionOnSideSquare) {
            //FIXME
        }*/ else {
            return centerMachinesNoMultipleWithPointsSquare(
                    positionMovingObject,
                    sideSquare,
                    coordinat,
                    radius
            );
        }


//        assert(false);
    }

    @Override
    public double getDimension() {
        return this.dimension;
    }

    private List<Node> coincidesCenterMachineWith(
            Point positionMovingObject,
            double sideSquare,
            Point coordinatAndCenterMachine,
            double radius
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
            listNeightborint.add(new NodeClass(
                    this,
                    firstNode.getRotateRelative(coordinatAndCenterMachine, angle90grad * i)
            ));
            listNeightborint.add(new NodeClass(
                    this,
                    firstDiagonalPoint.getRotateRelative(coordinatAndCenterMachine, angle90grad * i)
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
            double radius
    ) {

        double xMod = positionMovingObject.getX() % sideSquare;
        double xTopLeftNode = positionMovingObject.getX() - xMod;

        double yMod = positionMovingObject.getY() % sideSquare;
        double yTopLeftNode = positionMovingObject.getY() - yMod;

        Point topLeftNode = new PointClass(xTopLeftNode, yTopLeftNode);




        List<Node> listNeightborint = new ArrayList<Node>();

        listNeightborint.add(new NodeClass(
                this,
                new PointClass(topLeftNode.getX() + sideSquare, topLeftNode.getY())
        ));
        listNeightborint.add(new NodeClass(
                this,
                new PointClass(topLeftNode.getX() + sideSquare, topLeftNode.getY() + sideSquare)
        ));
        listNeightborint.add(new NodeClass(
                this,
                new PointClass(topLeftNode.getX(), topLeftNode.getY() + sideSquare)
        ));
        listNeightborint.add(new NodeClass(
                this,
                topLeftNode
        ));

        return listNeightborint;
    }


}
