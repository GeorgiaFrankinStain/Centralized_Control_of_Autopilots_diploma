package com.alamutra.CCoAWeb.PercistanceDataAccessObjects;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;

import java.util.List;

public interface AdderMarks {
    public void addMarksIn(FootprintsSpaceTime footprintsSpaceTime);

    public List<Order> getOrders();
}
