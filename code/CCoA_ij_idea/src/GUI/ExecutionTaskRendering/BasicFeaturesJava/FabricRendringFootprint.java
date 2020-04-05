package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.StatementTaskRendering.DataFootprintForRendering;
import GUI.StatementTaskRendering.TypeLandscapeBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PolygonExtended;
import javafx.scene.layout.Pane;

public interface FabricRendringFootprint {
    public Pane getRenderingFootprint(DataFootprintForRendering dataFootprintForRendering);
    public Pane getRenderingFootprint(TypeMachinesBody typeMachinesBody);
    public Pane getRenderingFootprint(TypeLandscapeBody typeLandscapeBody, PolygonExtended area);
}
