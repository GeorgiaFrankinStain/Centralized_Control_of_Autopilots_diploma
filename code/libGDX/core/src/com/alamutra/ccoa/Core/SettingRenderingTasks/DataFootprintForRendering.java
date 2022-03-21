package com.alamutra.ccoa.Core.SettingRenderingTasks;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMovingUnique;

public interface DataFootprintForRendering {
    public int getIdMovingObject();
    public SkinsCapacitor getSkin();
    public ParametersMovingUnique getMovingObject();


    public double getRotationDegree();

    public PointCCoA getCoordinates();

    public double getRotation();
}
