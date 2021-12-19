package com.alamutra.ccoa.GUI.StatementTaskRendering.ConvertersTime;

import com.alamutra.ccoa.GUI.StatementTaskRendering.ConverterTime;
import com.alamutra.ccoa.Logic.IndexLayer;

public interface MarginTimeForLevel {
    public void setLevel(IndexLayer indexLayer, double marginTime);

    public ConverterTime getConverterTime();
}
