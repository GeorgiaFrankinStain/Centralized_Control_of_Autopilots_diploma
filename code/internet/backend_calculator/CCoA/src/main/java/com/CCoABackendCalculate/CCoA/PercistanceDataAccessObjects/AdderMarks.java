package com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;

import java.util.List;

public interface AdderMarks {
    public void addMarksIn(FootprintsSpaceTime footprintsSpaceTime);

    public List<Order> getOrders();
}
