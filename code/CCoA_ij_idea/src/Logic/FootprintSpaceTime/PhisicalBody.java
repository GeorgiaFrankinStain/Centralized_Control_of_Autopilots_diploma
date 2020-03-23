package Logic.FootprintSpaceTime;

import GUI.StatementTaskRendering.TypePhisicalBody;

public interface PhisicalBody {
    PolygonExtended getArea();
    TypePhisicalBody getTypePhisicalBody();
    int getID();
    int getLevel();
}
