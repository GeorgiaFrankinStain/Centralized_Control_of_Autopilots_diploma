package com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.ConvertersTime;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayer;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.ConverterTime;

public interface MarginTimeForLevel {
    public void setLevel(IndexLayer indexLayer, double marginTime);

    public ConverterTime getConverterTime();
}
