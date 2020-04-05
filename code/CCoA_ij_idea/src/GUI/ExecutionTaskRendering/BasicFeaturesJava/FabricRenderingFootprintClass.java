package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprints.Machine;
import GUI.StatementTaskRendering.DataFootprintForRendering;
import GUI.StatementTaskRendering.TypeLandscapeBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PolygonExtended;
import javafx.scene.layout.Pane;

public class FabricRenderingFootprintClass implements FabricRendringFootprint {

    @Override
    public Pane getRenderingFootprint(DataFootprintForRendering dataFootprintForRendering) {
        String type = dataFootprintForRendering.getType();
        if (type.equals("PASSENGER_CAR")) {
            return new Machine(dataFootprintForRendering);
        } else {
            assert(false);
            return null;
        }
    }

    @Override
    public Pane getRenderingFootprint(TypeMachinesBody typeMachinesBody) {
        return null;
    }

    @Override
    public Pane getRenderingFootprint(TypeLandscapeBody typeLandscapeBody, PolygonExtended area) {
        return null;
    }
}
