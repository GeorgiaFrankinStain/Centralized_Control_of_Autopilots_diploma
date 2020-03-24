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

        //отсееваем, не входящие в зону новой отрисовки


        //отсееваем устаревшие объекты, которые в данный момент должны находиться на другом месте
        //добавляем новые объекты



        //TODO REALISED
        //min
        //    Complete removal. Create new list.
        //max (LINK_GhyJnYIW)
        //    Recursion ask for a hash, update only in case of changes.
        //    Create list changed object.
        //    Create list deleting object. LINK_uVPgVFwt







//        int[] x = {50, 50, 90, 90, 150, 90, 90};
//        int[] y = {55, 85, 85, 110, 70, 30, 55};
        PolygonExtended test = new PolygonExtendedClass();
        test.setPoint(new PointClass(50, 50));
        test.setPoint(new PointClass(100, 50));
        test.setPoint(new PointClass(100, 100));
        test.setPoint(new PointClass(50, 100));

//        PhisicalBody testBody = new PhisicalBodyClass(test);


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
