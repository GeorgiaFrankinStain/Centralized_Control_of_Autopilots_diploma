package com.alamutra.ccoa.Core.Logic.MovingBody;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;

public class BuilderParametersMovingUniqueClass implements BuilderParametersMovingUnique {
    private double speed;
    private PolygonCCoA shape;
    private TypeMachinesBody typeMachinesBody = TypeMachinesBody.SIMPLE_CAR;

    @Override
    public void setSpeed(double speed) {
        boolean isValidSpeed = speed >= 0;
        if (!isValidSpeed) {
            throw new IllegalArgumentException("the speed is less than 0");
        }

        this.speed = speed;
    }

    @Override
    public void setShape(PolygonCCoA shape) {
        this.shape = shape.clone();
    }

    @Override
    public void setTypeMachinesBody(TypeMachinesBody typeMachinesBody) {
        this.typeMachinesBody = typeMachinesBody;
    }

    @Override
    public ParametersMovingUnique getParametersMoving() {
        return new ParametersMovingUniqueClass(speed, shape, typeMachinesBody);
    }
}
