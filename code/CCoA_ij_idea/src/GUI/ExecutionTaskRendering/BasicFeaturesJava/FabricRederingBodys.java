package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.StatementTaskRendering.TypeLandscapeBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PolygonExtended;

public interface FabricRederingBodys {
    RenderingBody getMachineRenderingBody(TypeMachinesBody typeMachinesBody);
    RenderingBody getZonaRenderingBody(TypeLandscapeBody typeLandscapeBody, PolygonExtended area);
    //TODO user machines (user behavior machines)
    //TODO user image
}
