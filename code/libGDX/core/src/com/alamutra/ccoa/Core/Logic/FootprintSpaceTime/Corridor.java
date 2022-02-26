package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import java.util.List;

public interface Corridor {
    public boolean isCoverPolygon(double time, PolygonCCoA polygon);
    public List<Double> timestampsVertexCorridor();
    public String toString();
}
