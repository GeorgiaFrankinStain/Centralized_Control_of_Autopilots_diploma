package com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.AStar;

import com.alamutra.CCoAWeb.Core.ModelLogic.ControllerMachines.Node;

public interface HeuristicStorage {
    public Node getNodeWithBestHeuristicScope();
    public void addNode(Node node, Node neighbor);
    public void addNode(Node node);
    public void removeNode(Node node);
}
