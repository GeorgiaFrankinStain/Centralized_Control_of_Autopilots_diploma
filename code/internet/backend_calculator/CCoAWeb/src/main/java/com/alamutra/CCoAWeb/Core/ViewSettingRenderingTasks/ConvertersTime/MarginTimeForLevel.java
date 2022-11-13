package com.alamutra.CCoAWeb.Core.ViewSettingRenderingTasks.ConvertersTime;

import com.alamutra.CCoAWeb.Core.ModelLogic.IndexLayer;
import com.alamutra.CCoAWeb.Core.ViewSettingRenderingTasks.ConverterTime;

public interface MarginTimeForLevel {
    public void setLevel(IndexLayer indexLayer, double marginTime);

    public ConverterTime getConverterTime();
}
