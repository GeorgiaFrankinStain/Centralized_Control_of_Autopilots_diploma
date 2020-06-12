package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Point;

import java.util.List;

public interface NetworkNodes {
    public Node createNode(Point coordinat, double radius);
    public List<Node> getNeightboringNodes(Point coordinat, double radius); //FIXME надо, чтобы на одинаковые координаты, сеть возвращала уже созданные узлы, с хранящейся в них информацией соотвественно
    public double getDimension();
}