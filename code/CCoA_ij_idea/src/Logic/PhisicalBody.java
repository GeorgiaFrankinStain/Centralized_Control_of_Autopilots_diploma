package Logic;

import GUI.StatementTaskRendering.TypeLandscapeBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PolygonExtended;

import java.awt.*;

public interface PhisicalBody {
    public PolygonExtended getPolygonExtended();

    public String getType();

    public int getID();

    public int getLevel();
}
