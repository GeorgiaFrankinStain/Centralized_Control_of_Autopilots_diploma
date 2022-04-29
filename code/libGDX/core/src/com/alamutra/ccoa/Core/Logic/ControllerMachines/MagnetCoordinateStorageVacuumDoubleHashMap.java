package com.alamutra.ccoa.Core.Logic.ControllerMachines;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.GlobalVariable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;


/*
 * LINK_dSofRx
 * The peculiarity is that the error in re-calculating the center of the hexagon from different coordinates is less
 * than a millimeter, and the distance between the centers of the hexagons is several orders of magnitude greater.
 *
 *    interval between magnets (timestamps interval for time; GlobalVariable.DOUBLE_COMPARISON_ACCURACY for xySpace)
 * |<---------->|
 *
 *            1    2                                                               3            4
 * |----------*-|--*---------|------------|------------|------------|------------|-*----------|-*----------|
 *              ^
 *           magnet
 *
 *
 *            1    2                                                               3            4
 * -------|---*----*---|------------|------------|------------|------------|-------*----|-------*----|------------|
 *
 *
 * Node 2 is the error of node 1. Node 1 is issued.
 * Node 4 is a separate new node. Node 4 is issued.
 *
 *
 */
public class MagnetCoordinateStorageVacuumDoubleHashMap implements MagnetCoordinateStorage {
    private static final Logger LOGGER = LogManager.getLogger(MagnetCoordinateStorageVacuumDoubleHashMap.class);

    HashMap<Integer, Node> diapasonsStorage = new HashMap<>();
    HashMap<Integer, Node> offsetDiapasonsStorage = new HashMap<>();

    private double timeStampsInterval;
    private double xyLengthDiapason;
    private Node searchObject;
    private int hashDiapason;
    private int hashOffsetDiapason;

    public MagnetCoordinateStorageVacuumDoubleHashMap(double timeStampsInterval) {
        this.timeStampsInterval = timeStampsInterval;
        this.xyLengthDiapason = getXyLengthDiapason();
    }

    @Override
    public Node getFirstMagnetizedNodeTryAdd(Node tryAddingNode) {
        this.searchObject = null;

        PointCCoA coordinate = tryAddingNode.getCoordinate();
        double time = tryAddingNode.getActualTimeTravelFromStart();

        if (isAlreadyExistCoordinate(coordinate, time)) {
            return this.searchObject;
        } else {
            writeToBothStorages(tryAddingNode);

            return tryAddingNode;
        }
    }

    private void writeToBothStorages(Node newNode) {
        this.diapasonsStorage.put(this.hashDiapason, newNode);
        this.offsetDiapasonsStorage.put(this.hashOffsetDiapason, newNode);
    }

    private boolean isAlreadyExistCoordinate(PointCCoA coordinate, double time) {
        return isOccupiedInDiapasonsStorage(coordinate, time) || isOccupiedInOffsetDiapasonsStorage(coordinate, time);
    }

    private boolean isOccupiedInDiapasonsStorage(PointCCoA coordinate, double time) {
        int x = GlobalVariable.getNumberDiapason(coordinate.getX(), this.xyLengthDiapason);
        int y = GlobalVariable.getNumberDiapason(coordinate.getY(), this.xyLengthDiapason);
        int t = GlobalVariable.getNumberDiapason(time, this.timeStampsInterval);

        this.hashDiapason = hashCodeCubeDiapason(x, y, t);


        this.searchObject = this.diapasonsStorage.get(this.hashDiapason);

        boolean isOccupied = this.searchObject != null;

        LOGGER.debug("x: {}, y: {}, t: {}, hash: {}, coordinate: {}, returnRes: {}",
                x, y, t, this.hashDiapason, coordinate, isOccupied);

        return isOccupied;
    }

    private boolean isOccupiedInOffsetDiapasonsStorage(PointCCoA coordinate, double time) {
        double offsetXY = -this.xyLengthDiapason / 2;
        int x = GlobalVariable.getNumberOffsetDiapason(coordinate.getX(), this.xyLengthDiapason, offsetXY);
        int y = GlobalVariable.getNumberOffsetDiapason(coordinate.getY(), this.xyLengthDiapason, offsetXY);


        double offsetTime = -this.timeStampsInterval / 2;
        int t = GlobalVariable.getNumberOffsetDiapason(time, this.timeStampsInterval, offsetTime);

        this.hashOffsetDiapason = hashCodeCubeDiapason(x, y, t);


        Node value = this.offsetDiapasonsStorage.get(this.hashOffsetDiapason);

        boolean isWeWereLuckyToGetIntoTheHalfRangeNearTheOriginalNode = this.searchObject != null && value != null;
        if (isWeWereLuckyToGetIntoTheHalfRangeNearTheOriginalNode) {
            justInCaseLetSCheckThatAsPlannedOneNodeIsWrittenToBothRepositories(value);
        }

        this.searchObject = value;

        boolean isOccupied = value != null;

        LOGGER.debug("x: {}, y: {}, t: {}, hash: {}, coordinate: {}, returnRes: {}",
                x, y, t, this.hashOffsetDiapason, coordinate, isOccupied);

        return isOccupied;
    }

    private void justInCaseLetSCheckThatAsPlannedOneNodeIsWrittenToBothRepositories(Node value) {
        assert (this.searchObject.equals(value));
    }

    private int hashCodeCubeDiapason(long x, long y, long t) {
        int twoPow32 = 2147483647;
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (x % twoPow32);
        result = prime * result + (int) (y % twoPow32);
        result = prime * result + (int) (t % twoPow32);
        result = prime * result + prime;

        return result;
    }


    private double getXyLengthDiapason() {
        return GlobalVariable.DOUBLE_COMPARISON_ACCURACY * 4;
    }

    private boolean isIncludeInDiapason(double coordinate, double startDiapason, double lengthDiapason) {
        double endDiapason = startDiapason + lengthDiapason;
        return startDiapason <= coordinate && coordinate < endDiapason;
    }
}
