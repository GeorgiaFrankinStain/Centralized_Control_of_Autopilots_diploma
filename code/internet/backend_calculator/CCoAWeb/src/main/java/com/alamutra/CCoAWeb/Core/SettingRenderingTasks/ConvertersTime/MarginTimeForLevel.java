package com.alamutra.ccoa.Core.SettingRenderingTasks.ConvertersTime;

import com.alamutra.ccoa.Core.Logic.IndexLayer;
import com.alamutra.ccoa.Core.SettingRenderingTasks.ConverterTime;

public interface MarginTimeForLevel {
    public void setLevel(IndexLayer indexLayer, double marginTime);

    public ConverterTime getConverterTime();
}
