package com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;

public interface DataFootprintForRendering {
    public int getIdMovingObject();
    public SkinsCapacitor getSkin();
    public ParametersMovingUnique getMovingObject();


    public double getRotationDegree();

    public PointCCoA getCoordinates();

    public double getRotation();
}
