package com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PolygonCCoAClass;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;

public class FabricParametersMovingUniqueClass implements FabricParametersMovingUnique {
    @Override
    public ParametersMovingUnique getMoving(TypeMachinesBody typeMachinesBody) {

        BuilderParametersMovingUnique builder = this.getBuilderMoving(typeMachinesBody);

        ParametersMovingUnique parametersMovingUnique = builder.getParametersMoving();

        return parametersMovingUnique;
    }

    @Override
    public BuilderParametersMovingUnique getBuilderMoving(TypeMachinesBody typeMachinesBody) {

        if (typeMachinesBody == TypeMachinesBody.TEST_SQUARE_20) {
            PolygonCCoA formMachine = new PolygonCCoAClass();
            formMachine.addPoint(new PointCCoAClass(0, 0));
            formMachine.addPoint(new PointCCoAClass(20, 0));
            formMachine.addPoint(new PointCCoAClass(20, 20));
            formMachine.addPoint(new PointCCoAClass(0, 20));


            BuilderParametersMovingUnique builder = new BuilderParametersMovingUniqueClass();
            builder.setSpeed(10); //~=40 kilometr / hour
            builder.setShape(formMachine);
            builder.setTypeMachinesBody(typeMachinesBody);

            return builder;
        } else if (typeMachinesBody == TypeMachinesBody.WALL_CAR) {
            PolygonCCoA formMachine = new PolygonCCoAClass();

            formMachine.addPoint(new PointCCoAClass(0, 0));
            formMachine.addPoint(new PointCCoAClass(500, 0));
            formMachine.addPoint(new PointCCoAClass(500, 50));
            formMachine.addPoint(new PointCCoAClass(0, 50));


            BuilderParametersMovingUnique builder = new BuilderParametersMovingUniqueClass();
            builder.setSpeed(0);
            builder.setShape(formMachine);
            builder.setTypeMachinesBody(typeMachinesBody);

            return builder;
        } else if (typeMachinesBody == TypeMachinesBody.WALL_SQUARE) {

            PolygonCCoA formMachine = new PolygonCCoAClass();

            formMachine.addPoint(new PointCCoAClass(0, 0));
            formMachine.addPoint(new PointCCoAClass(50, 0));
            formMachine.addPoint(new PointCCoAClass(50, 50));
            formMachine.addPoint(new PointCCoAClass(0, 50));

            BuilderParametersMovingUnique builder = new BuilderParametersMovingUniqueClass();
            builder.setSpeed(0);
            builder.setShape(formMachine);
            builder.setTypeMachinesBody(typeMachinesBody);

            return builder;
        }


        assert(false);
        return null;


    }
}

