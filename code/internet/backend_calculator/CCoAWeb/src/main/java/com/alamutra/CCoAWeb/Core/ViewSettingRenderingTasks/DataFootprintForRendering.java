package com.alamutra.CCoAWeb.Core.ViewSettingRenderingTasks;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.ParametersMovingUnique;

public interface DataFootprintForRendering {
    public int getIdMovingObject();
    public SkinsCapacitor getSkin();
    public ParametersMovingUnique getMovingObject();


    public double getRotationDegree();

    public PointCCoA getCoordinates();

    public double getRotation();
}
