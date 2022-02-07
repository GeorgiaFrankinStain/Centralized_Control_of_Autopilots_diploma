package com.alamutra.ccoa.Core.SettingRenderingTasks;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.ccoa.Core.Logic.MovingObjects.ParametersMoving;

public interface DataFootprintForRendering {
    public int getIdMovingObject();
    public Skins getSkin();
    public ParametersMoving getMovingObject();


    public double getRotationDegree();

    public PointCCoA getCoordinates();

    public double getRotation();
}
