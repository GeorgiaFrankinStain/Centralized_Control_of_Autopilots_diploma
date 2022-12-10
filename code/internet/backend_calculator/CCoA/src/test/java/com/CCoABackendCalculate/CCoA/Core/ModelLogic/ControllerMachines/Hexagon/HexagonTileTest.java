package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.Hexagon;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.NetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.Node;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.Square20ParametersMovingUnique;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HexagonTileTest {

    private static final Logger LOGGER = LogManager.getLogger(HexagonTileTest.class);

    private ParametersMovingUnique car20 = new Square20ParametersMovingUnique();
    private double degree87 = 1.51844;

    private NetworkNodes networkNodesForCar20 = new HexagonTile(degree87, car20);

    private List<Node> neighborsZone1RectangleX0Y0 = neighborsZone1RectangleX0Y0();

    private List<Node> neighborsZone1RectangleX0Y0() {

        List<Node> expected = new ArrayList<>();

        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-20.0, 23.0940130116019), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-9.999996113248775, 40.41451884327323), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(10.000003886751225, 40.41451884327323), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(19.999996113247263, 23.094006279552175), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(9.99999222649604, 5.773500447880849), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-10.00000777350396, 5.773500447880849), 2.0));

        return expected;
    }

    private void testNodesZone1(List<Node> nodes) {
        int i = 0;
        for (Node actualNode : nodes) {
            Node expectedNode = neighborsZone1RectangleX0Y0.get(i);
            assertTrue(actualNode.equals(expectedNode), "equalsTest i: " + i + " " + actualNode);
            assertTrue(actualNode.hashCode() == expectedNode.hashCode(), "hashCodeTest i: " + i);
            i++;
        }
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone1CoordinateX0Y12() {
        LOGGER.debug("==== start getNeighboringNodes_Layer1Neighbors6Zone1CoordinateX0Y12 ====");
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0, 12), 0);
        testNodesZone1(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone1CoordinateX0Y23CenterZone() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0, 23.0940130116019), 0);
        testNodesZone1(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone1CoordinateX9Y23() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(9, 23.0940130116019), 0);
        testNodesZone1(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone1CoordinateX1Y30() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(1, 30), 0);
        testNodesZone1(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone1CoordinateXm9Y23() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(-9, 23.0940130116019), 0);
        testNodesZone1(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone1CoordinateX0Y31() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0, 31), 0);
        testNodesZone1(nodes);
    }

    private List<Node> neighborsZone2RectangleX0Y0 = neighborsZone2RectangleX0Y0();

    private List<Node> neighborsZone2RectangleX0Y0() {

        List<Node> expected = new ArrayList<>();

        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-10.000001295584077, 40.414518843273804), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(2.591167147514284E-6, 57.73502467494513), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(20.000002591167146, 57.73502467494513), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(29.999994817663186, 40.41451211122408), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(19.999990930911963, 23.09400627955275), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-9.069088036994799E-6, 23.09400627955275), 2.0));

        return expected;
    }

    private void testNodesZone2(List<Node> nodes) {
        int i = 0;
        for (Node actualNode : nodes) {
            Node expectedNode = neighborsZone2RectangleX0Y0.get(i);
            assertTrue(actualNode.equals(expectedNode), "equalsTest i: " + i + " " + actualNode);
            assertTrue(actualNode.hashCode() == expectedNode.hashCode(), "hashCodeTest i: " + i);
            i++;
        }
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone2CoordinateX5Y37() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(5, 37), 0);
        testNodesZone2(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone2CoordinateX01Y37() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0.1, 37), 0);
        testNodesZone2(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone2CoordinateX001Y37() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0.01, 37), 0);
        testNodesZone2(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone2CoordinateX0001Y37() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0.001, 37), 0);
        testNodesZone2(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone2CoordinateX00001Y37() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0.0001, 37), 0);
        testNodesZone2(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone2CoordinateX000001Y37() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0.00001, 37), 0);
        testNodesZone2(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone2CoordinateX0000001Y37() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0.000001, 37), 0);
        testNodesZone2(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone2CoordinateX10Y30() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(10, 45), 0);
        testNodesZone2(nodes);
    }

    private List<Node> neighborsZone3RectangleX0Y0 = neighborsZone3RectangleX0Y0();

    private List<Node> neighborsZone3RectangleX0Y0() {

        List<Node> expected = new ArrayList<>();

        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-2.5911681582613255E-6, 23.0940130116019), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(10.000001295583067, 40.41451884327323), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(30.00000129558307, 40.41451884327323), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(39.99999352207911, 23.094006279552175), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(29.999989635327886, 5.773500447880849), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(9.999989635327886, 5.773500447880849), 2.0));

        return expected;
    }

    private void testNodesZone3(List<Node> nodes) {
        int i = 0;
        for (Node actualNode : nodes) {
            Node expectedNode = neighborsZone3RectangleX0Y0.get(i);
            assertTrue(actualNode.equals(expectedNode), "equalsTest i: " + i + " " + actualNode);
            assertTrue(actualNode.hashCode() == expectedNode.hashCode(), "hashCodeTest i: " + i);
            i++;
        }
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone3CoordinateX11Y20() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(11, 20), 0);
        testNodesZone3(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone3CoordinateX15Y20() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(15, 20), 0);
        testNodesZone3(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone3CoordinateX25Y20() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(25, 20), 0);
        testNodesZone3(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone3CoordinateX29Y20() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(29, 20), 0);
        testNodesZone3(nodes);
    }

    private List<Node> neighborsZone4RectangleX0Y0 = neighborsZone4RectangleX0Y0();

    private List<Node> neighborsZone4RectangleX0Y0() {

        List<Node> expected = new ArrayList<>();

        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-29.999998704415923, 5.773502691896258), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-19.9999948176647, 23.094008523567584), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(5.182335300446539E-6, 23.094008523567584), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(9.999997408831339, 5.773495959846532), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-6.477919884062544E-6, -11.547009871824795), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-20.000006477919882, -11.547009871824795), 2.0));

        return expected;
    }

    private void testNodesZone4(List<Node> nodes) {
        int i = 0;
        for (Node actualNode : nodes) {
            Node expectedNode = neighborsZone4RectangleX0Y0.get(i);
            assertTrue(actualNode.equals(expectedNode), "equalsTest i: " + i + " " + actualNode);
            assertEquals(actualNode.hashCode(), expectedNode.hashCode(), "hashCodeTest i: " + i);
            i++;
        }
    }


    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone4CoordinateX0Y0() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0, 0), 0);
        testNodesZone4(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone4CoordinateX0Y10() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(0, 10), 0);
        testNodesZone4(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone4CoordinateXm10Y0() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(-10, 0), 0);
        testNodesZone4(nodes);
    }



    private List<Node> neighborsZone5RectangleX0Y0 = neighborsZone5RectangleX0Y0();

    private List<Node> neighborsZone5RectangleX0Y0() {

        List<Node> expected = new ArrayList<>();

        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-10.000001295584077, 5.773502691896258), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(2.591167147514284E-6, 23.094008523567584), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(20.000002591167146, 23.094008523567584), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(29.999994817663186, 5.773495959846532), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(19.999990930911963, -11.547009871824795), 2.0));
        expected.add(networkNodesForCar20.createNode(new PointCCoAClass(-9.069088036994799E-6, -11.547009871824795), 2.0));

        return expected;
    }

    private void testNodesZone5(List<Node> nodes) {
        int i = 0;
        for (Node actualNode : nodes) {
            Node expectedNode = neighborsZone5RectangleX0Y0.get(i);
            assertTrue(actualNode.equals(expectedNode), "equalsTest i: " + i + " " + actualNode);
            assertEquals(actualNode.hashCode(), expectedNode.hashCode(), "hashCodeTest i: " + i);
            i++;
        }
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone5CoordinateX5Y5() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(5, 5), 0);
        testNodesZone5(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone5CoordinateX1Y1() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(1, 1), 0);
        testNodesZone5(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone5CoordinateX5Ym2() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(5, -2), 0);
        testNodesZone5(nodes);
    }

    @Test
    void getNeighboringNodes_Layer1Neighbors6Zone5CoordinateX10Ym2() {
        List<Node> nodes = networkNodesForCar20.getNeighboringNodes(new PointCCoAClass(5, -2), 0);
        testNodesZone5(nodes);
    }

    @Test
    void getNeighboringNodes_repeatRectangle() {

        double heightRepeatRectangleForCar20 = 34.64101615137755;
        int multiplierForResultEqualApproximatelyM100 = -4;
        double startYOffset = heightRepeatRectangleForCar20 * multiplierForResultEqualApproximatelyM100;

        double widthRepeatRectangleForCar20 = 20;
        double startXOffset = widthRepeatRectangleForCar20 * multiplierForResultEqualApproximatelyM100;

        long forBreakpoint = 0;

        for (double xVectorOffset = startXOffset; xVectorOffset < 60; xVectorOffset += widthRepeatRectangleForCar20) {
            for (double yVectorOffset = startYOffset; yVectorOffset < 60; yVectorOffset += heightRepeatRectangleForCar20) {
                for (int xPointInsideRectangle = 1; xPointInsideRectangle < 20; xPointInsideRectangle++) {
                    for (int yPointInsideRectangle = 1; yPointInsideRectangle < 33; yPointInsideRectangle++) {
                        PointCCoA offset = new PointCCoAClass(xVectorOffset, yVectorOffset);
                        PointCCoA localCoordinate = new PointCCoAClass(xPointInsideRectangle, yPointInsideRectangle);

                        List<Node> expected = networkNodesForCar20.getNeighboringNodes(localCoordinate, 0.0);

                        PointCCoA offsetCoordinate = localCoordinate.getDeposeOn(offset);
                        List<Node> actual = networkNodesForCar20.getNeighboringNodes(offsetCoordinate, 0.0);

                        int i = 0;
                        for (Node expectedNode : expected) {
                            Node expectedNodeTransposition = recreatedTranspositionNode(expectedNode, offset);
                            Node actualNode = actual.get(i);
                            assertEquals(expectedNodeTransposition, actualNode, "coordinate: " + offsetCoordinate + " offsetVector: " + offset + "i: " + i + " forBreakpoint: " + forBreakpoint);
                            i++;

                            forBreakpoint++;
                        }
                    }
                }
            }
        }
    }


    @Test
    void containsResultManualAddNodesForEndNode()
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        LOGGER.debug("============= start containsResultManualAddNodesForEndNode ==========");

        NetworkNodes forManualNodes = new HexagonTile(degree87, car20);

        List<PointCCoA> coordinates = new ArrayList<>();
        coordinates.add(new PointCCoAClass(-10, 1));
        coordinates.add(new PointCCoAClass(-15, 1));


        coordinates.add(new PointCCoAClass(0, 15));

        coordinates.add(new PointCCoAClass(20, 15));


        coordinates.add(new PointCCoAClass(30, 0));

        coordinates.add(new PointCCoAClass(20, -5));

        coordinates.add(new PointCCoAClass(5, -10));

        for (PointCCoA point : coordinates) {
            forManualNodes.createManualNodeAnyTime(point);
        }

        List<Node> result = forManualNodes.getNeighboringNodes(new PointCCoAClass(10, 1), 0.0);

        assertEquals(6 + coordinates.size(), result.size());
    }

    private Node recreatedTranspositionNode(Node node, PointCCoA vectorTransposition) {
        PointCCoA newCoordinate = vectorTransposition.getDeposeOn(node.getCoordinate());
        return networkNodesForCar20.createNode(newCoordinate, node.getActualTimeTravelFromStart());
    }

    @Test
    void privateTestDeterminationNumberLayers_87() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        double degree87 = 1.51844;
        assert (privateTestDeterminationNumberLayers(degree87, 1));
    }

    @Test
    void privateTestDeterminationNumberLayers_60() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        double degree60 = 1.0472;
        assert (privateTestDeterminationNumberLayers(degree60, 1));
    }

    @Test
    void privateTestDeterminationNumberLayers_30() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        double degree30 = 0.523599;
        assert (privateTestDeterminationNumberLayers(degree30, 1));
    }

    @Test
    void privateTestDeterminationNumberLayers_29() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        double degree29 = 0.506145;
        assert (privateTestDeterminationNumberLayers(degree29, 2));
    }

    @Test
    void privateTestDeterminationNumberLayers_15() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        double degree15 = 0.261800;
        assert (privateTestDeterminationNumberLayers(degree15, 2));
    }

    @Test
    void privateTestDeterminationNumberLayers_14() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        double degree14 = 0.2443467078892;
        assert (privateTestDeterminationNumberLayers(degree14, 3));
    }

    @Test
    void privateTestDeterminationNumberLayers_8() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        double degree8 = 0.13962695276980000014;
        assert (privateTestDeterminationNumberLayers(degree8, 3));
    }

    @Test
    void privateTestDeterminationNumberLayers_4() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        double degree4 = 0.0698132;
        assert (privateTestDeterminationNumberLayers(degree4, 4));
    }

    private boolean privateTestDeterminationNumberLayers(double allowedErrorCoDirectivityRadian, int expected)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        NetworkNodes networkNodes = new HexagonTile(allowedErrorCoDirectivityRadian, car20);
        Method method = HexagonTile.class.getDeclaredMethod("determinationNumberLayers", double.class);
        method.setAccessible(true);
        int actual = (int) method.invoke(networkNodes, allowedErrorCoDirectivityRadian);

        return expected == actual;
    }
}