package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Point;
import Logic.MovingObjects.MovingObject;

import java.util.List;

public interface NetworkNodes {
    public Node createFirstNode(Point coordinat, double radius, double timeTravelFromStart);
    public Node createFirstNode(Point coordinat, double radius); //FIXME
    public void cleanInfoAbout(Node node); ////FIXME bad solution architecture

    public void addNode(Node node);
    public List<Node> getNeightboringNodes(Node currentNode, Point coordinat, double radius, MovingObject movingObject); //FIXME надо, чтобы на одинаковые координаты, сеть возвращала уже созданные узлы, с хранящейся в них информацией соотвественно
    public double getDimension();
}