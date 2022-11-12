package com.alamutra.ccoa.Core.Logic.ControllerMachines.AStar;

import com.alamutra.ccoa.Core.Logic.ControllerMachines.Node;

import java.util.List;

public interface HeuristicStorage {
    public Node getNodeWithBestHeuristicScope();
    public void addNode(Node node, Node neighbor);
    public void addNode(Node node);
    public void removeNode(Node node);
}
