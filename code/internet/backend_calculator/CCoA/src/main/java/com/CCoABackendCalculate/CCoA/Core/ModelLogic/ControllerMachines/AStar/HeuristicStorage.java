package com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AStar;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.Node;

public interface HeuristicStorage {
    public Node getNodeWithBestHeuristicScope();
    public void addNode(Node node, Node neighbor);
    public void addNode(Node node);
    public void removeNode(Node node);
}
