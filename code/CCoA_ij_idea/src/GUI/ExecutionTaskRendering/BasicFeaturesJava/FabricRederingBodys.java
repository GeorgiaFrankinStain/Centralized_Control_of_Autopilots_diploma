package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.StatementTaskRendering.TypeLandscapeBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.PhisicalBody;

public interface FabricRederingBodys {
    PhisicalBody getMachineRenderingBody(TypeMachinesBody typeMachinesBody);
    PhisicalBody getZonaRenderingBody(TypeLandscapeBody typeLandscapeBody, PolygonExtended area);
    //TODO user machines (user behavior machines)
    //TODO user image
}
