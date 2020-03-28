package GUI.ExecutionTaskRendering.BasicFeaturesJava;

import GUI.StatementTaskRendering.TypeLandscapeBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PointClass;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PolygonExtendedClass;

public class FabricRenderingBodysClass implements FabricRederingBodys {
    @Override
    public RenderingBody getMachineRenderingBody(TypeMachinesBody typeMachinesBody) {
        PolygonExtended test = new PolygonExtendedClass(); //FIXME IMITATION
        test.setPoint(new PointClass(50, 50));
        test.setPoint(new PointClass(100, 50));
        test.setPoint(new PointClass(100, 200));
        test.setPoint(new PointClass(50, 100));


        RenderingBody testBody = new RenderingBodyClass(test);


        return testBody;
    }

    @Override
    public RenderingBody getZonaRenderingBody(TypeLandscapeBody typeLandscapeBody, PolygonExtended area) {
        return null; //FIXME
    }
}
