package GUI.StatementTaskRendering;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingFootprint;
import Logic.FootprintSpaceTime.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoolPhisicalBodysForRenderingClass implements PoolPhisicalBodysForRendering, Iterable<DataFootprintForRendering> {
    private FootprintSpaceTime sourceFootprintSpaceTime;
    private List<DataFootprintForRendering> forRenderingRenderingBody = new ArrayList<DataFootprintForRendering>();

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

        List<DataFootprintForRendering> dataFootprintForRenderings
                = new ArrayList<DataFootprintForRendering>();
        for (Footprint footprint : footprints) {
            dataFootprintForRenderings.add((DataFootprintForRendering) footprint);
        }

//        this.forRenderingRenderingBody.add(testBody);
        this.forRenderingRenderingBody = dataFootprintForRenderings;
    }


    public PoolPhisicalBodysForRenderingClass(FootprintSpaceTime footprintSpaceTime) {
        this.sourceFootprintSpaceTime = footprintSpaceTime;
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
            int size = PoolPhisicalBodysForRenderingClass.this.forRenderingRenderingBody.size();
            int endIndex = size - 1;
            return this.nextIndexPhisicalBody <= endIndex;
        }

        @Override
        public DataFootprintForRendering next() {
            int currenIndex = this.nextIndexPhisicalBody++;
            return PoolPhisicalBodysForRenderingClass.this.forRenderingRenderingBody.get(currenIndex);
        }
    }
}
