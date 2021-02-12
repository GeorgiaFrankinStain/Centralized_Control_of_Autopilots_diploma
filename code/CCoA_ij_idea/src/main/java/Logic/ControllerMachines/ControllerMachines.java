package Logic.ControllerMachines;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.FootprintSpaceTime.Point;
import Logic.MovingObjects.MovingObject;

public interface ControllerMachines {
    public void bringCarToEndOfRoad(
            Point from,
            Point to,
            MovingObject movingObject,
            double timeAdding
    ) throws СrashIntoAnImpassableObjectExeption;
}
