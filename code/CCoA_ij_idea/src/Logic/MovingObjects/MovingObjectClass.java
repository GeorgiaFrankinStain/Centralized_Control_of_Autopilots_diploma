package Logic.MovingObjects;

import Logic.FootprintSpaceTime.*;
import Logic.PathsMachines.PositionClass;
import Logic.Position;

public class MovingObjectClass implements MovingObject {
    public MovingObjectClass() {
    }


    //TODO create track


    @Override
    public void mark(FootprintSpaceTime footprintSpaceTime) {
        //run addSled

        PolygonExtended test = new PolygonExtendedClass(); //FIXME IMITATION
        test.setPoint(new PointClass(50, 50));
        test.setPoint(new PointClass(100, 50));
        test.setPoint(new PointClass(100, 200));
        test.setPoint(new PointClass(50, 100));

        PhisicalBody testBody = new PhisicalBodyClass(test);


        Position position1 = new PositionClass(new PointClass(100, 100), 0);
        footprintSpaceTime.addFootprint(13, 1313, testBody, position1, 1);
        footprintSpaceTime.addFootprint(13, 1313, testBody, position1, 2);
        footprintSpaceTime.addFootprint(13, 1313, testBody, position1, 3);
        footprintSpaceTime.addFootprint(13, 1313, testBody, position1, 4);
    }
}
