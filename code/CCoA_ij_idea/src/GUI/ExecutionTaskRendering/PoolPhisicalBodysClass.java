package GUI.ExecutionTaskRendering;

import Logic.FootprintSpaceTime.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoolPhisicalBodysClass implements PoolPhisicalBodys, Iterable<PhisicalBody> {
    private FootprintSpaceTime sourceFootprintSpaceTime;
    private List<PhisicalBody> forRenderingPhisicalBody = new ArrayList<PhisicalBody>();

    @Override
    public void fillYourself(PolygonExtended areaRendering, int time) {
//        int[] x = {50, 50, 90, 90, 150, 90, 90};
//        int[] y = {55, 85, 85, 110, 70, 30, 55};
        PolygonExtended test = new PolygonExtendedClass();
        test.setPoint(new PointClass(50, 50));
        test.setPoint(new PointClass(100, 50));
        test.setPoint(new PointClass(100, 100));
        test.setPoint(new PointClass(50, 100));

        PhisicalBody testBody = new PhisicalBodyClass(test);
        forRenderingPhisicalBody.add(testBody);
    }

    @Override
    public PhisicalBody getPhisicalBody(int ID) {
        return null;
    }


    public PoolPhisicalBodysClass(FootprintSpaceTime footprintSpaceTime) {
        this.sourceFootprintSpaceTime = footprintSpaceTime;
    }

    @Override
    public Iterator<PhisicalBody> iterator() {
        return new PoolPhisicalBodysIterator(this);
    }



    private class PoolPhisicalBodysIterator implements Iterator<PhisicalBody> {
        private PoolPhisicalBodys poolPhisicalBodys;
        private int nextIndexPhisicalBody = 0;

        public PoolPhisicalBodysIterator(PoolPhisicalBodys poolPhisicalBodys) {
            this.poolPhisicalBodys = poolPhisicalBodys;
        }

        @Override
        public boolean hasNext() {
            int size = PoolPhisicalBodysClass.this.forRenderingPhisicalBody.size();
            int endIndex = size - 1;
            return this.nextIndexPhisicalBody <= endIndex;
        }

        @Override
        public PhisicalBody next() {
            int currenIndex = this.nextIndexPhisicalBody++;
            return PoolPhisicalBodysClass.this.forRenderingPhisicalBody.get(currenIndex);
        }
    }
}
