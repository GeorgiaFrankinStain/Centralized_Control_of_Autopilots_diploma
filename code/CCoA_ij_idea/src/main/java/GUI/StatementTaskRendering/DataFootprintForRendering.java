package GUI.StatementTaskRendering;

import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.MovingObjects.MovingObject;
import Logic.Position;

public interface DataFootprintForRendering {
    public int getIdObject();
    public Position getLocalOriginForPointMovingObject();
    public String getType();
    public MovingObject getMovingObject();
}
