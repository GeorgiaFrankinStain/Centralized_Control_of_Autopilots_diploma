package Logic;

import GUI.StatementTaskRendering.TypeMachinesBody;
import Logic.FootprintSpaceTime.PointClass;
import Logic.FootprintSpaceTime.PolygonExtended;
import Logic.FootprintSpaceTime.PolygonExtendedClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.MovingObjectClass;

public class FabricMovingObjectsClass implements FabricMovingObjects {
    @Override
    public MovingObject getMachine(TypeMachinesBody typeMachinesBody) {

        if (typeMachinesBody == TypeMachinesBody.TEST_SQUARE_20) {
            PolygonExtended formMachine = new PolygonExtendedClass(); //FIXME IMITATION
            formMachine.addPoint(new PointClass(0, 0));
            formMachine.addPoint(new PointClass(20, 0));
            formMachine.addPoint(new PointClass(20, 20));
            formMachine.addPoint(new PointClass(0, 20));

            MovingObject machine = new MovingObjectClass(formMachine, typeMachinesBody);

            machine.setSpeed(10); //~=40 kilometr / hour


            return machine;
        } else if (typeMachinesBody == TypeMachinesBody.WALL_CAR) {
            PolygonExtended formMachine = new PolygonExtendedClass();

            formMachine.addPoint(new PointClass(0, 0));
            formMachine.addPoint(new PointClass(500, 0));
            formMachine.addPoint(new PointClass(500, 50));
            formMachine.addPoint(new PointClass(0, 50));


            MovingObject machine = new MovingObjectClass(formMachine, typeMachinesBody);

            machine.setSpeed(0);

            return machine;
        } else if (typeMachinesBody == TypeMachinesBody.WALL_SQUARE) {

            PolygonExtended formMachine = new PolygonExtendedClass();

            formMachine.addPoint(new PointClass(0, 0));
            formMachine.addPoint(new PointClass(50, 0));
            formMachine.addPoint(new PointClass(50, 50));
            formMachine.addPoint(new PointClass(0, 50));


            MovingObject machine = new MovingObjectClass(formMachine, typeMachinesBody);

            machine.setSpeed(0);

            return machine;
        }


        assert(false);
        return null;


    }
}
