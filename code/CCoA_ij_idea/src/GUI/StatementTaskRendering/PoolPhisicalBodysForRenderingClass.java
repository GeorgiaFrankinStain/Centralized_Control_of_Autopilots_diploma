package GUI.StatementTaskRendering;

import Logic.FootprintSpaceTime.*;

import java.util.*;

public class PoolPhisicalBodysForRenderingClass implements PoolPhisicalBodysForRendering, Iterable<DataFootprintForRendering> {
    private FootprintSpaceTime sourceFootprintSpaceTime;
    private Map<Integer, DataFootprintForRendering> poolDataFootprintForRendering;


    public PoolPhisicalBodysForRenderingClass(FootprintSpaceTime footprintSpaceTime) {
        this.sourceFootprintSpaceTime = footprintSpaceTime;
    }

    @Override
    public void fillYourself(PolygonExtended areaRendering, int gameTime) {
/*      //TODO REALISED
        min
            Complete removal. Create new list.
        max (LINK_GhyJnYIW)
            Recursion ask for a hash, update only in case of changes.
            Create list changed object.
            Create list deleting object. LINK_uVPgVFwt
            */

        List<Footprint> footprints =
                this.sourceFootprintSpaceTime.getRenderingFootprintsFromWhen(areaRendering, gameTime);

        this.poolDataFootprintForRendering = new TreeMap<Integer, DataFootprintForRendering>();
        for (Footprint footprint : footprints) {
            poolDataFootprintForRendering.put(footprint.getIdObject(), (DataFootprintForRendering) footprint);
        }
    }

    @Override
    public DataFootprintForRendering getDataFootprint(int IdObject) {
        return this.poolDataFootprintForRendering.get(IdObject); //FIXME
    }


    @Override
    public Iterator<DataFootprintForRendering> iterator() {
        return new PoolPhisicalBodysIterator(this);
    }


    private class PoolPhisicalBodysIterator implements Iterator<DataFootprintForRendering> {
        private PoolPhisicalBodysForRendering poolPhisicalBodysForRendering;
        private int nextIndexPhisicalBody = 0;

        public PoolPhisicalBodysIterator(PoolPhisicalBodysForRendering poolPhisicalBodysForRendering) {
            this.poolPhisicalBodysForRendering = poolPhisicalBodysForRendering;
        }

        @Override
        public boolean hasNext() {
            int size = PoolPhisicalBodysForRenderingClass.this.poolDataFootprintForRendering.size();
            int endIndex = size - 1;
            return this.nextIndexPhisicalBody <= endIndex;
        }

        @Override
        public DataFootprintForRendering next() {
            int currenIndex = this.nextIndexPhisicalBody++;
            return PoolPhisicalBodysForRenderingClass.this.poolDataFootprintForRendering.get(currenIndex);
        }
    }
}
