package GUI.ExecutionTaskRendering;

import Logic.FootprintSpaceTime.PhisicalBody;
import Logic.FootprintSpaceTime.PolygonExtended;

import java.util.List;

public interface MapExecutionTaskRendering {
    public void setAreaOfRendering(PolygonExtended areaOfRendering);

    public void setListActualPhisicalBody(List<Integer> listActualPhisicalBody);

    public void setPhisicalBody(PhisicalBody phisicalBody);
}
