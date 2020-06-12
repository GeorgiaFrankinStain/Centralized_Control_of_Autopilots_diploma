package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Point;

import java.util.List;

public class NodeClass implements Node {//FIXME NOW add equals, hashcode
    private NetworkNodes networkNodes;
    private Point coordinat;
    private Double timeTravelFromStart = null;

    public NodeClass(NetworkNodes networkNodes, Point coordinat) {
        this.networkNodes = networkNodes;
        this.coordinat = coordinat;
    }

    @Override
    public List<Node> getNeighboringNodes(double radiusMovingObject) {
        double dimensionNetworkNodes = networkNodes.getDimension(); //radius;
        return networkNodes.getNeightboringNodes(coordinat, radiusMovingObject);
    }

    @Override
    public double getCoveredDistanceFrom(Point from) {
        return from.getDistanceToPoint(this.coordinat);
    }

    @Override
    public double getEstimatedToDestination(Point to) {
        return to.getDistanceToPoint(this.coordinat); //FIXME FIRST move in AreasBenchmarkPaths
    }

    @Override
    public Point getCoordinate() {
        return this.coordinat;
    }

    @Override
    public void setTimeTravelFromStart(Double timeTravelFromStart) {
        this.timeTravelFromStart = timeTravelFromStart;
    }

    @Override
    public Double getTimeTravelFromStart() {
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
        result = prime * result + prime;
        return result;
    }

    @Override
    public String toString() {
        return this.getCoordinate().toString();
    }
}
