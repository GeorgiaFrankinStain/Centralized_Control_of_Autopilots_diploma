package Logic.FootprintSpaceTime;

import java.util.*;

public class RoundsCorridorClass implements Corridor {
    private TreeMap<Double, Round> corridor = new TreeMap<Double, Round>();


    public RoundsCorridorClass(TreeMap<Double, Round> corridor) {
        assert(corridor.size() > 1);
        this.corridor = corridor;
    }

    @Override
    public boolean isCoverPolygon(double time, PolygonExtended polygon) {
        CheckerPolygonEntering checker = new CheckerPolygonEnteringClass(time, polygon);
        return checker.isPolygonEntering();
    }

    @Override
    public List<Double> timestampsVertexCorridor() { //FIXME add tests
        List<Double> timestampsVertexCorridor = new ArrayList<Double>();

        for (Map.Entry<Double, Round> entry : this.corridor.entrySet()) {
            timestampsVertexCorridor.add(entry.getKey());
        }

        return timestampsVertexCorridor;
    }


    private interface CheckerPolygonEntering {
        public boolean isPolygonEntering();
    }
    private class CheckerPolygonEnteringClass implements CheckerPolygonEntering {
        private double time;
        private PolygonExtended polygon;
        private Round afterNearest = null;
        private Double afterNearestTime = null;
        private Round beforeNearest = null;
        private Double beforeNearestTime = null;

        public CheckerPolygonEnteringClass(double time, PolygonExtended polygon) {
            this.time = time;
            this.polygon = polygon;
        }

        @Override
        public boolean isPolygonEntering() {
            nearestRoundsDetected();

            boolean isTimeNoEnteringCorridor = beforeNearest == null || afterNearest == null;
            if (isTimeNoEnteringCorridor) {
                return false;
            }

            Round approximationRound = approximationRound();

            return polygon.isLiesInsideThe(approximationRound);
        }

        private Round approximationRound() {
            Round approximationRound;
            boolean isApproximationNeeded = beforeNearestTime != time;
            assert(isApproximationNeeded == (beforeNearestTime != afterNearestTime));
            if (isApproximationNeeded) {
                approximationRound = beforeNearest.getApproximation(
                        beforeNearestTime,
                        afterNearest,
                        afterNearestTime,
                        time
                );
            } else {
                approximationRound = beforeNearest;
            }

            return approximationRound;
        }


        private void nearestRoundsDetected() {
            NavigableMap<Double, Round> map = corridor;

            Map.Entry<Double, Round> beforeEntry = map.floorEntry(time);
            if (beforeEntry != null) {
                beforeNearest = beforeEntry.getValue();
                beforeNearestTime = map.floorKey(time);
            }

            Map.Entry<Double, Round> afterEntry = map.ceilingEntry(time);
            if (afterEntry != null) {
                afterNearest = afterEntry.getValue();
                afterNearestTime = map.ceilingKey(time);
            }
        }
    }
}
