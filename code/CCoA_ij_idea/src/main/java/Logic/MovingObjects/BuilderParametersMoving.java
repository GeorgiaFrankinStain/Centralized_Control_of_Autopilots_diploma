package Logic.MovingObjects;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PolygonExtended;

public interface BuilderParametersMoving {
    public void setSpeed(double speed);

    public void setShape(PolygonExtended shape);

    public void setTypeMachinesBody(TypeMachinesBody typeMachinesBody);

    public ParametersMoving getParametersMoving();
}
