package GUI.StatementTaskRendering.ConvertersTime;

import GUI.StatementTaskRendering.ConverterTime;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.LevelLayer;
import Logic.LevelLayerClass;

public class DemonstartionAlhorith implements ConverterTime {

    MarginTimeForLevel marginTimeForLevel = new MarginTimeForLevelClass();

    public DemonstartionAlhorith(FootprintsSpaceTime footprintsSpaceTime) {
        LevelLayer defaultLevel = new LevelLayerClass(0);
        double averageStep = footprintsSpaceTime.averageTimeMovingToNextPointOfPath();
        double lastTimeAddingFootprint =
                footprintsSpaceTime.getTimeAddingLastFootprints(defaultLevel);
        double fullMarginTime = lastTimeAddingFootprint + averageStep;

        this.marginTimeForLevel.setLevel(defaultLevel, -fullMarginTime);
    }

    @Override
    public double convert(double time, LevelLayer levelLayer) {
        return this.marginTimeForLevel.getConverterTime().convert(time, levelLayer);
    }
}
