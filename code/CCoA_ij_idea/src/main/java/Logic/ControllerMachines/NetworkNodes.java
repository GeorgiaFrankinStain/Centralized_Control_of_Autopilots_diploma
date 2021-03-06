package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Point;
import Logic.MovingObjects.ParametersMoving;

import java.util.List;

public interface NetworkNodes {
    public Node createFirstNode(Point coordinat, double radius, double timeTravelFromStart);
    public void cleanInfoAbout(Node node); ////FIXME bad solution architecture

    public void addNode(Node node);
    public List<Node> getNeightboringNodes(Node currentNode, Point coordinat, double radius, ParametersMoving parametersMoving); //FIXME надо, чтобы на одинаковые координаты, сеть возвращала уже созданные узлы, с хранящейся в них информацией соотвественно
}