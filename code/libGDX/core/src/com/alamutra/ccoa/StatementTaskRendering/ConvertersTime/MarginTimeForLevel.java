package com.alamutra.ccoa.StatementTaskRendering.ConvertersTime;

import com.alamutra.ccoa.Logic.IndexLayer;
import com.alamutra.ccoa.StatementTaskRendering.ConverterTime;

public interface MarginTimeForLevel {
    public void setLevel(IndexLayer indexLayer, double marginTime);

    public ConverterTime getConverterTime();
}
