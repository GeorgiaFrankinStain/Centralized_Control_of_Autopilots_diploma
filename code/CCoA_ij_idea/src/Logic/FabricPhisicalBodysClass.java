package Logic;

import GUI.StatementTaskRendering.TypeLandscapeBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PointClass;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PolygonExtendedClass;

public class FabricPhisicalBodysClass implements FabricPhisicalBodys {
    @Override
    public PhisicalBody getMachineRenderingBody(TypeMachinesBody typeMachinesBody) {
        PolygonExtended test = new PolygonExtendedClass(); //FIXME IMITATION
        test.setPoint(new PointClass(50, 50));
        test.setPoint(new PointClass(100, 50));
        test.setPoint(new PointClass(100, 100));
        test.setPoint(new PointClass(50, 100));


        PhisicalBody testBody = new PhisicalBodyClass(test);


        return testBody;
    }

    @Override
    public PhisicalBody getZonaRenderingBody(TypeLandscapeBody typeLandscapeBody, PolygonExtended area) {
        return null; //FIXME
    }
}
