package com.alamutra.CCoAWeb.Core.SettingRenderingTasks.ConvertersTime;

import com.alamutra.CCoAWeb.Core.Logic.IndexLayer;
import com.alamutra.CCoAWeb.Core.SettingRenderingTasks.ConverterTime;

import java.util.HashMap;
import java.util.Map;

public class MarginTimeForLevelClass implements MarginTimeForLevel, ConverterTime {

    private Map<IndexLayer, Double> marginsTime = new HashMap<IndexLayer, Double>();



    @Override
    public double convert(double time, IndexLayer indexLayer) {
        double margin = 0;
        if (this.marginsTime.containsKey(indexLayer)) {
            margin = this.marginsTime.get(indexLayer);
        }

        return margin + time;
    }

    @Override
    public void setLevel(IndexLayer indexLayer, double marginTime) {
        this.marginsTime.put(indexLayer, marginTime);
    }

    @Override
    public ConverterTime getConverterTime() {
        return this;
    }
}
