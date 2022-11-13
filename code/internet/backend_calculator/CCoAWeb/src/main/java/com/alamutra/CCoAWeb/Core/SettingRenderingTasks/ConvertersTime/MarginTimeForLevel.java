package com.alamutra.CCoAWeb.Core.SettingRenderingTasks.ConvertersTime;

import com.alamutra.CCoAWeb.Core.Logic.IndexLayer;
import com.alamutra.CCoAWeb.Core.SettingRenderingTasks.ConverterTime;

public interface MarginTimeForLevel {
    public void setLevel(IndexLayer indexLayer, double marginTime);

    public ConverterTime getConverterTime();
}
