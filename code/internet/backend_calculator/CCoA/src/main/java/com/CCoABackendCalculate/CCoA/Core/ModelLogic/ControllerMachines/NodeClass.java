package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;

import java.util.List;

public class NodeClass implements Node {//FIXME NOW add equals, hashcode
    private NetworkNodes networkNodes;
    private PointCCoA coordinates;
    private double timeTravelFromStart;

    private long xMagnetizedId;
    private long yMagnetizedId;
    private long tMagnetizedId;


    public NodeClass(NetworkNodes networkNodes, PointCCoA coordinates, double timeTravelFromStart) {
        this.networkNodes = networkNodes;
        this.coordinates = coordinates.clone();
        this.timeTravelFromStart = timeTravelFromStart;

        this.xMagnetizedId = magnetized(coordinates.getX());
        this.yMagnetizedId = magnetized(coordinates.getY());
        this.tMagnetizedId = magnetized(timeTravelFromStart);
    }

    @Override
    public List<Node> getNeighboringNodes() {
        return networkNodes.getNeighboringNodes(this);
    }

    @Override
    public double getEstimateDistanceToDestinationHeuristicFunction(PointCCoA to) {
        return to.getDistanceToPoint(this.coordinates); //FIXME FIRST move in AreasBenchmarkPaths
    }

    @Override
    public PointCCoA getCoordinate() {
        return this.coordinates;
    }


    @Override
    public Double getActualTimeTravelFromStart() {
        return this.timeTravelFromStart;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        Node other = (Node) obj;

        return this.hashCode() == other.hashCode();
    }


    @Override
    public int hashCode() {
        int twoPow32 = 2147483647;
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.xMagnetizedId % twoPow32);
        result = prime * result + (int) (this.yMagnetizedId % twoPow32);
        result = prime * result + (int) (this.tMagnetizedId % twoPow32);
        result = prime * result + (int) (this.networkNodes.hashCode() % twoPow32);
        result = prime * result + prime;

        return result;
    }

    @Override
    public String toString() {
        return this.getCoordinate().toString();
    }

    private long magnetized(double doubleCoordinate) {
        double roundCoordinate = GlobalVariable.roundHalfUp(doubleCoordinate);
        long magnetizedCoordinate = (long) (roundCoordinate / GlobalVariable.DOUBLE_COMPARISON_ACCURACY);
        return magnetizedCoordinate;
    }
}
