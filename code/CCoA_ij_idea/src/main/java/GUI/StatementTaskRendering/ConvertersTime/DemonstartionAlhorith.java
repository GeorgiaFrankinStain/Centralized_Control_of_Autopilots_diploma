package GUI.StatementTaskRendering.ConvertersTime;

import GUI.StatementTaskRendering.ConverterTime;
import Logic.FootprintSpaceTime.FootprintsSpaceTime;
import Logic.IndexLayer;
import Logic.IndexLayerClass;

public class DemonstartionAlhorith implements ConverterTime {

    MarginTimeForLevel marginTimeForLevel = new MarginTimeForLevelClass();

    public DemonstartionAlhorith(FootprintsSpaceTime footprintsSpaceTime) {
        IndexLayer defaultLevel = new IndexLayerClass(0);
        double averageStep = footprintsSpaceTime.averageTimeMovingToNextPointOfPath();
        double lastTimeAddingFootprint =
                footprintsSpaceTime.getTimeAddingLastFootprints(defaultLevel);
        double fullMarginTime = lastTimeAddingFootprint + averageStep;
        this.marginTimeForLevel.setLevel(defaultLevel, -fullMarginTime);

        IndexLayer level1 = new IndexLayerClass(1);
        this.marginTimeForLevel.setLevel(level1, 0);
    }

    @Override
    public double convert(double time, IndexLayer indexLayer) {
        return this.marginTimeForLevel.getConverterTime().convert(time, indexLayer);
    }
}