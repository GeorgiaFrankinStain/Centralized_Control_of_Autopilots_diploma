package com.alamutra.ccoa.Core.Logic.MovingObjects;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;

public class BuilderParametersMovingClass implements BuilderParametersMoving {
    private double speed;
    private PolygonCCoA shape;
    private TypeMachinesBody typeMachinesBody;

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
    public ParametersMoving getParametersMoving() {
        return new ParametersMovingClass(speed, shape, typeMachinesBody);
    }
}
