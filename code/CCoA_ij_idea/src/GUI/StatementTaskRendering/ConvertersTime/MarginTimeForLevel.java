package GUI.StatementTaskRendering.ConvertersTime;

import GUI.StatementTaskRendering.ConverterTime;
import Logic.LevelLayer;

public interface MarginTimeForLevel {
    public void setLevel(LevelLayer levelLayer, double marginTime);

    public ConverterTime getConverterTime();
}
