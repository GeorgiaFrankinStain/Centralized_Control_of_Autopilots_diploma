package GUI.StatementTaskRendering;

import Logic.FootprintSpaceTime.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoolPhisicalBodysForRenderingClass implements PoolPhisicalBodysForRendering, Iterable<PhisicalBody> {
    private FootprintSpaceTime sourceFootprintSpaceTime;
    private List<PhisicalBody> forRenderingPhisicalBody = new ArrayList<PhisicalBody>();

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


        List<PhisicalBody> testBody = this.sourceFootprintSpaceTime.getPhisicalBodysFromWhen(areaRendering, gameTime);
//        this.forRenderingPhisicalBody.add(testBody);
        this.forRenderingPhisicalBody = testBody;
    }

    @Override
    public PhisicalBody getPhisicalBody(int ID) {
        return null;
    }


    public PoolPhisicalBodysForRenderingClass(FootprintSpaceTime footprintSpaceTime) {
        this.sourceFootprintSpaceTime = footprintSpaceTime;
    }

    @Override
    public Iterator<PhisicalBody> iterator() {
        return new PoolPhisicalBodysIterator(this);
    }


    private class PoolPhisicalBodysIterator implements Iterator<PhisicalBody> {
        private PoolPhisicalBodysForRendering poolPhisicalBodysForRendering;
        private int nextIndexPhisicalBody = 0;

        public PoolPhisicalBodysIterator(PoolPhisicalBodysForRendering poolPhisicalBodysForRendering) {
            this.poolPhisicalBodysForRendering = poolPhisicalBodysForRendering;
        }

        @Override
        public boolean hasNext() {
            int size = PoolPhisicalBodysForRenderingClass.this.forRenderingPhisicalBody.size();
            int endIndex = size - 1;
            return this.nextIndexPhisicalBody <= endIndex;
        }

        @Override
        public PhisicalBody next() {
            int currenIndex = this.nextIndexPhisicalBody++;
            return PoolPhisicalBodysForRenderingClass.this.forRenderingPhisicalBody.get(currenIndex);
        }
    }
}
