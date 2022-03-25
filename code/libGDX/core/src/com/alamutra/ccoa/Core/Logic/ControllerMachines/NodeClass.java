package com.alamutra.ccoa.Core.Logic.ControllerMachines;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;

import java.util.List;

public class NodeClass implements Node {//FIXME NOW add equals, hashcode
    private NetworkNodes networkNodes;
    private PointCCoA coordinat;
    private Double timeTravelFromStart;


    public NodeClass(NetworkNodes networkNodes, PointCCoA coordinat, Double timeTravelFromStart) {
        this.networkNodes = networkNodes;
        this.coordinat = coordinat;
        this.timeTravelFromStart = timeTravelFromStart;
    }

    @Override
    public List<Node> getNeighboringNodes() {
        return networkNodes.getNeighboringNodes(this);
    }

    @Override
    public double getEstimateDistanceToDestinationHeuristicFunction(PointCCoA to) {
        return to.getDistanceToPoint(this.coordinat); //FIXME FIRST move in AreasBenchmarkPaths
    }

    @Override
    public PointCCoA getCoordinate() {
        return this.coordinat;
    }


    @Override
    public Double getActualTimeTravelFromStart() {
        return this.timeTravelFromStart;
    }

    @Override
    public boolean equals(Object obj) { //FIXME ADD_TEST
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        Node other = (Node) obj;

        return this.coordinat.equals(other.getCoordinate()); //FIXME add test networkNodes
    }


    @Override
    public int hashCode() {

        //FIXME hash code from double is good?

        int twoPow32 = 2147483647;
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.coordinat.getY() % twoPow32);
        result = prime * result + (int) (this.coordinat.getX() / twoPow32);
        result = prime * result + (int) (this.timeTravelFromStart / twoPow32);
        result = prime * result + (int) (this.networkNodes.hashCode() / twoPow32);
        result = prime * result + prime;
        return result;
    }

    @Override
    public String toString() {
        return this.getCoordinate().toString();
    }
}

