package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Point;
import Logic.FootprintSpaceTime.PointClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void getNeighboringNodes() {
        {
            /**
             * (-10, -10)
             *      +---------+
             *      | - - - - |
             *      | - -c- - |
             *      | - - - - |
             *      +---------+ (10, 10)
             *
             * width square machine 20
             * c - point (0, 0)
             *
             */
            Point centerMachine = new PointClass(0, 0);
            Point cooridinateMachine = centerMachine;
            double widthSquareMachine = 20;
            Point farthestPointOfCar = new PointClass(widthSquareMachine / 2, widthSquareMachine / 2);
            double radiusMachine = farthestPointOfCar.getDistanceToPoint(centerMachine);

            /**
             *
             * LINK_dBKSoC (link (find text) on documentation in other place)
             */
            double dimension = 5;
            NetworkNodes networkNodes = new SquareNetworkNodes(dimension);

            Node node = networkNodes.createNode(cooridinateMachine, radiusMachine);
            List<Node> actualAllNeigbors = node.getNeighboringNodes(radiusMachine);


            List<Node> expectedAllNeighbors = new ArrayList<Node>();
            Node x1 = new NodeClass(
                    networkNodes,
                    new PointClass(20, 0)
            );
            expectedAllNeighbors.add(x1);
            Node x2 = new NodeClass(
                    networkNodes,
                    new PointClass(20, 20)
            );
            expectedAllNeighbors.add(x2);
            Node x3 = new NodeClass(
                    networkNodes,
                    new PointClass(0, 20)
            );
            expectedAllNeighbors.add(x3);
            Node x4 = new NodeClass(
                    networkNodes,
                    new PointClass(-20, 20)
            );
            expectedAllNeighbors.add(x4);
            Node x5 = new NodeClass(
                    networkNodes,
                    new PointClass(-20, 0)
            );
            expectedAllNeighbors.add(x5);
            Node x6 = new NodeClass(
                    networkNodes,
                    new PointClass(-20, -19)
//                    new PointClass(-20, -20)
            );
            expectedAllNeighbors.add(x6);
            Node x7 = new NodeClass(
                    networkNodes,
                    new PointClass(0, -20)
            );
            expectedAllNeighbors.add(x7);
            Node x8 = new NodeClass(
                    networkNodes,
                    new PointClass(19, -20)
//                    new PointClass(20, -20)
            );
            expectedAllNeighbors.add(x8);


            assertEquals(expectedAllNeighbors, actualAllNeigbors);

            //FIXME add test equals link on object, when create equas nodes in equals coordinate (link on one object)
        }
        {
            /**
             * (-10, -10)
             *      +---------+
             *      | - - - - |
             *      | - -c- - |
             *      | - - - - |
             *      +---------+ (10, 10)
             *
             * width square machine 20
             * c - point (0, 0)
             *
             */
            Point centerMachine = new PointClass(0, 0);
            Point cooridinateMachine = new PointClass(1, 1);
            double widthSquareMachine = 20;
            Point farthestPointOfCar = new PointClass(widthSquareMachine / 2, widthSquareMachine / 2);
            double radiusMachine = farthestPointOfCar.getDistanceToPoint(centerMachine);

            /**
             *
             * LINK_dBKSoC (link (find text) on documentation in other place)
             */
            double dimension = 5;
            NetworkNodes networkNodes = new SquareNetworkNodes(dimension);

            Node node = networkNodes.createNode(cooridinateMachine, radiusMachine);
            List<Node> actualAllNeigbors = node.getNeighboringNodes(radiusMachine);


            List<Node> expectedAllNeighbors = new ArrayList<Node>();
            Node x1 = new NodeClass(
                    networkNodes,
                    new PointClass(20, 0)
            );
            expectedAllNeighbors.add(x1);
            Node x2 = new NodeClass(
                    networkNodes,
                    new PointClass(20, 20)
            );
            expectedAllNeighbors.add(x2);
            Node x3 = new NodeClass(
                    networkNodes,
                    new PointClass(0, 20)
            );
            expectedAllNeighbors.add(x3);
            Node x4 = new NodeClass(
                    networkNodes,
                    new PointClass(0, 0)
            );
            expectedAllNeighbors.add(x4);


            assertEquals(expectedAllNeighbors, actualAllNeigbors);

            //FIXME add test equals link on object, when create equas nodes in equals coordinate (link on one object)
        }
    }

    @Test
    public void getCoveredDistanceFrom() {
        //FIXME по хорошему из соображений ООП надо, чтобы информация об узле хранилась в узле, а не сторонних хранилищах, но для этого надо реалиовать, чтобы сеть выдалвал на одинаковые узлы, одинаковые координаты
    }

    @Test
    public void getEstimatedToDestination() {
    }

    @Test
    public void getCoordinate() {
    }
}