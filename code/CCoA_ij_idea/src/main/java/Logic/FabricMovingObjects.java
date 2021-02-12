package Logic;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.MovingObjects.MovingObject;

public interface FabricMovingObjects {
    MovingObject getMachine(TypeMachinesBody typeMachinesBody);
    //TODO user machines (user behavior machines)
    //TODO user image
}
