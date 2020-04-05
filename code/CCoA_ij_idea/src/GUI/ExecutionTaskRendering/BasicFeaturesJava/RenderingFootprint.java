package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.StatementTaskRendering.DataFootprintForRendering;
import GUI.StatementTaskRendering.TypeLandscapeBody;


public interface RenderingFootprint {
    void update(long now, DataFootprintForRendering newProperties);
}
