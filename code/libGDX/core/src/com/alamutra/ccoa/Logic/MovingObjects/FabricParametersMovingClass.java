package com.alamutra.ccoa.Logic.MovingObjects;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.PointClass;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtended;
import com.alamutra.ccoa.Logic.FootprintSpaceTime.PolygonExtendedClass;
import com.alamutra.ccoa.StatementTaskRendering.TypeMachinesBody;

public class FabricParametersMovingClass implements FabricParametersMoving {
    @Override
    public ParametersMoving getMoving(TypeMachinesBody typeMachinesBody) {

        BuilderParametersMoving builder = this.getBuilderMoving(typeMachinesBody);

        ParametersMoving parametersMoving = builder.getParametersMoving();

        return parametersMoving;
    }

    @Override
    public BuilderParametersMoving getBuilderMoving(TypeMachinesBody typeMachinesBody) {

        if (typeMachinesBody == TypeMachinesBody.TEST_SQUARE_20) {
            PolygonExtended formMachine = new PolygonExtendedClass();
            formMachine.addPoint(new PointClass(0, 0));
            formMachine.addPoint(new PointClass(20, 0));
            formMachine.addPoint(new PointClass(20, 20));
            formMachine.addPoint(new PointClass(0, 20));


            BuilderParametersMoving builder = new BuilderParametersMovingClass();
            builder.setSpeed(10); //~=40 kilometr / hour
            builder.setShape(formMachine);
            builder.setTypeMachinesBody(typeMachinesBody);

            return builder;
        } else if (typeMachinesBody == TypeMachinesBody.WALL_CAR) {
            PolygonExtended formMachine = new PolygonExtendedClass();

            formMachine.addPoint(new PointClass(0, 0));
            formMachine.addPoint(new PointClass(500, 0));
            formMachine.addPoint(new PointClass(500, 50));
            formMachine.addPoint(new PointClass(0, 50));


            BuilderParametersMoving builder = new BuilderParametersMovingClass();
            builder.setSpeed(0);
            builder.setShape(formMachine);
            builder.setTypeMachinesBody(typeMachinesBody);

            return builder;
        } else if (typeMachinesBody == TypeMachinesBody.WALL_SQUARE) {

            PolygonExtended formMachine = new PolygonExtendedClass();

            formMachine.addPoint(new PointClass(0, 0));
            formMachine.addPoint(new PointClass(50, 0));
            formMachine.addPoint(new PointClass(50, 50));
            formMachine.addPoint(new PointClass(0, 50));

            BuilderParametersMoving builder = new BuilderParametersMovingClass();
            builder.setSpeed(0);
            builder.setShape(formMachine);
            builder.setTypeMachinesBody(typeMachinesBody);

            return builder;
        }


        assert(false);
        return null;


    }
}

