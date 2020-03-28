package GUI.StatementTaskRendering;

import GUI.ExecutionTaskRendering.BasicFeaturesJava.RenderingBody;
import Logic.FootprintSpaceTime.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoolPhisicalBodysForRenderingClass implements PoolPhisicalBodysForRendering, Iterable<RenderingBody> {
    private FootprintSpaceTime sourceFootprintSpaceTime;
    private List<RenderingBody> forRenderingRenderingBody = new ArrayList<RenderingBody>();

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


        List<RenderingBody> testBody = this.sourceFootprintSpaceTime.getPhisicalBodysFromWhen(areaRendering, gameTime);
//        this.forRenderingRenderingBody.add(testBody);
        this.forRenderingRenderingBody = testBody;
    }

    @Override
    public RenderingBody getPhisicalBody(int ID) {
        return null;
    }


    public PoolPhisicalBodysForRenderingClass(FootprintSpaceTime footprintSpaceTime) {
        this.sourceFootprintSpaceTime = footprintSpaceTime;
    }

    @Override
    public Iterator<RenderingBody> iterator() {
        return new PoolPhisicalBodysIterator(this);
    }


    private class PoolPhisicalBodysIterator implements Iterator<RenderingBody> {
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
        public RenderingBody next() {
            int currenIndex = this.nextIndexPhisicalBody++;
            return PoolPhisicalBodysForRenderingClass.this.forRenderingRenderingBody.get(currenIndex);
        }
    }
}
