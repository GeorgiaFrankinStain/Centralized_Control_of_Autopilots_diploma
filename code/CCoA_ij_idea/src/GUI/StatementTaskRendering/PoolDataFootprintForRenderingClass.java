package GUI.StatementTaskRendering;

import Logic.FootprintSpaceTime.*;

import java.util.*;

public class PoolDataFootprintForRenderingClass implements PoolDataFootprintForRendering, Iterable<DataFootprintForRendering> {
    private FootprintsSpaceTime sourceFootprintsSpaceTime;
    private Map<Integer, DataFootprintForRendering> poolDataFootprintForRendering =
            new HashMap<Integer, DataFootprintForRendering>();


    public PoolDataFootprintForRenderingClass(FootprintsSpaceTime footprintsSpaceTime) {
        this.sourceFootprintsSpaceTime = footprintsSpaceTime;
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
                this.sourceFootprintsSpaceTime.getRenderingFootprintsFromWhen(areaRendering, gameTime);

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
        return new PoolDataFootprintForRenderingIterator(this);
    }


    private class PoolDataFootprintForRenderingIterator implements Iterator<DataFootprintForRendering> {
        private PoolDataFootprintForRendering poolDataFootprintForRendering;
        private Iterator iteratorMap;

        public PoolDataFootprintForRenderingIterator(PoolDataFootprintForRendering poolDataFootprintForRendering) {
            this.poolDataFootprintForRendering = poolDataFootprintForRendering;

            
            Set entrySet = PoolDataFootprintForRenderingClass.this.poolDataFootprintForRendering.entrySet();
            // Obtaining an iterator for the entry set
            this.iteratorMap = entrySet.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.iteratorMap.hasNext();
        }

        @Override
        public DataFootprintForRendering next() {
                Map.Entry me = (Map.Entry) this.iteratorMap.next();
                return (DataFootprintForRendering) me.getValue();
        }
    }
}
