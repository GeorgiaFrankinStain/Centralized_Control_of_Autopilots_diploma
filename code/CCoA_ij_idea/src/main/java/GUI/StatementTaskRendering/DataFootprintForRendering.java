package GUI.StatementTaskRendering;

import Logic.MovingObjects.ParametersMoving;
import Logic.Position;

public interface DataFootprintForRendering {
    public int getIdMovingObject();
    public Position getLocalOriginForPointMovingObject();
    public String getType();
    public ParametersMoving getMovingObject();
}
