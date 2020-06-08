package GUI.StatementTaskRendering.ConvertersTime;

import GUI.StatementTaskRendering.ConverterTime;
import Logic.LevelLayer;

import java.util.HashMap;
import java.util.Map;

public class MarginTimeForLevelClass implements MarginTimeForLevel, ConverterTime {

    private Map<LevelLayer, Double> marginsTime = new HashMap<LevelLayer, Double>();



    @Override
    public double convert(double time, LevelLayer levelLayer) {
        double margin = 0;
        if (this.marginsTime.containsKey(levelLayer)) {
            margin = this.marginsTime.get(levelLayer);
        }

        return margin + time;
    }

    @Override
    public void setLevel(LevelLayer levelLayer, double marginTime) {
        this.marginsTime.put(levelLayer, marginTime);
    }

    @Override
    public ConverterTime getConverterTime() {
        return this;
    }
}
