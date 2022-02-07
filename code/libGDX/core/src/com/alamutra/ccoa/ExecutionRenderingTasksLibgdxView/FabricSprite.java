package com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.SettingRenderingTasks.DataFootprintForRendering;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeLandscapeBody;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;

public interface FabricSprite {
    public SpriteWrapper getRenderingFootprint(DataFootprintForRendering dataFootprintForRendering);
    public SpriteWrapper getRenderingFootprint(TypeMachinesBody typeMachinesBody);
    public SpriteWrapper getRenderingFootprint(TypeLandscapeBody typeLandscapeBody, PolygonCCoA area);
}
