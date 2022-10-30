package com.alamutra.ccoa.PercistanceDataAccessObjects;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;

import java.util.List;

public interface AdderMarks {
    public void addMarksIn(FootprintsSpaceTime footprintsSpaceTime);

    public List<Order> getOrders();
}
