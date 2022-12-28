package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.Hexagon.HexagonTile;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.Square20ParametersMovingUnique;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/*
 * LINK_dSofRx
 */
class MagnetCoordinateStorageVacuumDoubleHashMapTest {

    private ParametersMovingUnique car = new Square20ParametersMovingUnique(13);

    private Node one;
    private Node second;
    private Node actual1;
    private Node actual2;

    private Node node3;
    private Node actual3;

    private Node actual4;
    private Node node4;

    public MagnetCoordinateStorageVacuumDoubleHashMapTest() {

        MagnetCoordinateStorage magnet = new MagnetCoordinateStorageVacuumDoubleHashMap(2.0);

        double degree60 = 1.0472;
        NetworkNodes hexagonTile = new HexagonTile(degree60, car);

        double xOne = 1;

        one = new NodeClass(hexagonTile, new PointCCoAClass(xOne, 0), 0.0);

        double distanceBetweenMagnets = GlobalVariable.LENGTH_DIAPASON_MAGNET;
        double distanceIsLessThanTheDistanceBetweenTheMagnets = distanceBetweenMagnets / 10;
        double xSecond = xOne - distanceIsLessThanTheDistanceBetweenTheMagnets;

        second = new NodeClass(hexagonTile, new PointCCoAClass(xSecond, 0), 0.0);

        actual1 = magnet.getFirstMagnetizedNodeTryAdd(one);

        actual2 = magnet.getFirstMagnetizedNodeTryAdd(second);



        double x3 = 10;
        node3 = new NodeClass(hexagonTile, new PointCCoAClass(x3, 0), 0.0);

        actual3 = magnet.getFirstMagnetizedNodeTryAdd(node3);


        double x4 = x3 + distanceBetweenMagnets;
        node4 = new NodeClass(hexagonTile, new PointCCoAClass(x4, 0), 0.0);
        actual4 = magnet.getFirstMagnetizedNodeTryAdd(node4);


    }

    @Test
    void getFirstMagnetizedNode_xNeighborsOriginalEquals2Pointer() {
        boolean isThisIsTheSameInstance = one == actual2;
        assertTrue(isThisIsTheSameInstance);
    }

    @Test
    void getFirstMagnetizedNode_xNeighborsOriginalEquals2() {
        assertEquals(one, actual2);
    }

    @Test
    void getFirstMagnetizedNode_xNeighborsOriginalEquals2HashCode() {
        assertEquals(one.hashCode(), actual2.hashCode());
    }

    @Test
    void getFirstMagnetizedNode_xNeighbors1Equals2Pointer() {
        boolean isThisIsTheSameInstance = actual1 == actual2;
        assertTrue(isThisIsTheSameInstance);
    }

    @Test
    void getFirstMagnetizedNode_xNeighbors1Equals2() {
        assertEquals(actual1, actual2);
    }

    @Test
    void getFirstMagnetizedNode_xNeighbors1Equals2HashCode() {
        assertEquals(actual1.hashCode(), actual2.hashCode());
    }

    @Test
    void getFirstMagnetizedNode_xNotNeighbors3Equals3Pointer() {
    }

    @Test
    void getFirstMagnetizedNode_xNotNeighbors3Equals3() {
        assertEquals(actual3, node3);
    }

    @Test
    void getFirstMagnetizedNode_xNeighbors3Equals3HashCode() {
        assertEquals(actual3.hashCode(), node3.hashCode());
    }
    @Test
    void getFirstMagnetizedNode_xNotNeighbors4Equals4Pointer() {
        boolean isThisIsTheSameInstance = node4 == actual4;
        assertTrue(isThisIsTheSameInstance);
    }

    @Test
    void getFirstMagnetizedNode_xNotNeighbors4Equals4() {
        assertEquals(actual4, node4);
    }

    @Test
    void getFirstMagnetizedNode_xNeighbors4Equals4HashCode() {
        assertEquals(actual4.hashCode(), node4.hashCode());
    }
    @Test
    void getFirstMagnetizedNode_xNotNeighbors4NotEquals3Pointer() {
        boolean isThisIsTheSameInstance = actual3 == actual4;
        assertFalse(isThisIsTheSameInstance);
    }

    @Test
    void getFirstMagnetizedNode_xNotNeighbors4NotEquals3() {
        assertNotEquals(actual4, actual3);
    }

    @Test
    void getFirstMagnetizedNode_xNeighbors4NotEquals3HashCode() {
        assertNotEquals(actual4.hashCode(), node3.hashCode());
    }
}