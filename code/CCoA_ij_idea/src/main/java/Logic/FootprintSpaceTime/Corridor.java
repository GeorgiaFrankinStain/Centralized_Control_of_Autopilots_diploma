package Logic.FootprintSpaceTime;

import java.util.List;

public interface Corridor {
    public boolean isCoverPolygon(double time, PolygonExtended polygon);
    public List<Double> timestampsVertexCorridor();
}
