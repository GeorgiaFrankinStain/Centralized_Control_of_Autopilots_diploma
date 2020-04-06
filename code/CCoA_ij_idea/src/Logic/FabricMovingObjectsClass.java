package Logic;

import GUI.StatementTaskRendering.TypeLandscapeBody;
import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PointClass;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PolygonExtendedClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.MovingObjectClass;

public class FabricMovingObjectsClass implements FabricMovingObjects {
    @Override
    public MovingObject getMachine(TypeMachinesBody typeMachinesBody) {

        PolygonExtended test = new PolygonExtendedClass(); //FIXME IMITATION
        test.setPoint(new PointClass(0, 0));
        test.setPoint(new PointClass(50, 0));
        test.setPoint(new PointClass(50, 50));
        test.setPoint(new PointClass(0, 50));


        return new MovingObjectClass(test, typeMachinesBody);
    }
}
