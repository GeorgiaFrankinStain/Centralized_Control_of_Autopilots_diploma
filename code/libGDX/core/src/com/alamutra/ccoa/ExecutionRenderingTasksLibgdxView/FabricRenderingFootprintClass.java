package com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.SettingRenderingTasks.DataFootprintForRendering;
import com.alamutra.ccoa.Core.SettingRenderingTasks.SkinsCapacitor;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeLandscapeBody;
import com.alamutra.ccoa.Core.SettingRenderingTasks.TypeMachinesBody;
import com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView.RenderingFootprints.Machine;
import com.alamutra.ccoa.ExecutionRenderingTasksLibgdxView.RenderingFootprints.WallRectangleCar;

public class FabricRenderingFootprintClass implements FabricSprite {

    private int sumAllMachinePicture = 10;
    private int numberMachinePicture = 0;

    @Override
    public SpriteWrapper getRenderingFootprint(DataFootprintForRendering dataFootprintForRendering) {
        SkinsCapacitor type = dataFootprintForRendering.getSkin();
        if (type.equals(SkinsCapacitor.WALL_CAR)) {
            return new WallRectangleCar();
        } else if (type.equals(SkinsCapacitor.WALL_SQUARE)) {
            return new WallRectangleCar();
        } else if (type.equals(SkinsCapacitor.TEST_SQUARE_20)) {
            this.numberMachinePicture++;
            this.numberMachinePicture %= sumAllMachinePicture;
            return new Machine(numberMachinePicture);
        }

        assert (false); //ERROR: titlePictureNotFounded
        return null;
    }

    @Override
    public SpriteWrapper getRenderingFootprint(TypeMachinesBody typeMachinesBody) {
        return null; //FIXME
    }

    @Override
    public SpriteWrapper getRenderingFootprint(TypeLandscapeBody typeLandscapeBody, PolygonCCoA area) {
        return null; //FIXME
    }

}
