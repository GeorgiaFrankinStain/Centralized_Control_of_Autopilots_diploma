package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.FootprintSpaceTime.Point;
import Logic.MovingObjects.ParametersMoving;

public interface ControllerMachines {
    public void bringCarToEndOfRoad(
            Point from,
            Point to,
            ParametersMoving parametersMoving,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption;
}
