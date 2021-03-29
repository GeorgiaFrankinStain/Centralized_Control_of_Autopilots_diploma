package GUI.StatementTaskRendering.ConvertersTime;

import GUI.StatementTaskRendering.ConverterTime;
import Logic.IndexLayer;

public interface MarginTimeForLevel {
    public void setLevel(IndexLayer indexLayer, double marginTime);

    public ConverterTime getConverterTime();
}
