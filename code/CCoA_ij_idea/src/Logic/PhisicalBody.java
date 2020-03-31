package Logic;

import GUI.StatementTaskRendering.TypeLandscapeBody;
import Logic.FootprintSpaceTime.PolygonExtended;

import java.awt.*;

public interface PhisicalBody {
    public PolygonExtended getPolygonExtended();
    TypeLandscapeBody getTypePhisicalBody();
    int getID();
    int getLevel();
}
