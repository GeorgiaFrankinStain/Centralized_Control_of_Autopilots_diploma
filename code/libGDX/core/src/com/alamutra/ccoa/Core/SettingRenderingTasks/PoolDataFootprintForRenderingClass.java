package com.alamutra.ccoa.Core.SettingRenderingTasks;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Footprint;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.PolygonCCoA;
import com.alamutra.ccoa.Core.Logic.IndexLayer;
import com.alamutra.ccoa.Core.Logic.IndexLayerClass;

import java.util.*;
import java.util.function.Consumer;

public class PoolDataFootprintForRenderingClass implements PoolDataFootprintForRendering, Iterable<DataFootprintForRendering> {
    private FootprintsSpaceTime sourceFootprintsSpaceTime;
    private Map<Integer, DataFootprintForRendering> poolDataFootprintForRendering =
            new HashMap<Integer, DataFootprintForRendering>();


    public PoolDataFootprintForRenderingClass(FootprintsSpaceTime footprintsSpaceTime) {
        this.sourceFootprintsSpaceTime = footprintsSpaceTime;
    }

    @Override
    public void fillYourself(PolygonCCoA areaRendering, int currentTime) {
/*      //TODO REALISED
        min
            Complete removal. Create new list.
        max (LINK_GhyJnYIW)
            Recursion ask for a hash, update only in case of changes.
            Create list changed object.
            Create list deleting object. LINK_uVPgVFwt
            */

        poolDataFootprintForRendering.clear();


        ConverterTime converterTime = new ConverterTime() {
            @Override //FIXME COSTIL
            public double convert(double time, IndexLayer levelLayer) {
                return time;
            }
        };


        IndexLayer defaultLevel = new IndexLayerClass(0);
        List<Footprint> footprints =
                this.sourceFootprintsSpaceTime.getRenderingFootprintsFromWhen(
                        areaRendering,
                        converterTime.convert(currentTime, defaultLevel),
                        defaultLevel
                );

/*        LevelLayer alhorithDemonstrationInformationLevel = new LevelLayerClass(1); //FIXME decomment
        footprints.addAll(
                this.sourceFootprintsSpaceTime.getRenderingFootprintsFromWhen(
                        areaRendering,
                        converterTime.convert(currentTime, alhorithDemonstrationInformationLevel),
                        alhorithDemonstrationInformationLevel
                )
        );*/


        for (Footprint footprint : footprints) {
            poolDataFootprintForRendering.put(footprint.getIdMovingObject(), (DataFootprintForRendering) footprint);
        }
    }

    @Override
    public DataFootprintForRendering getDataFootprint(int IdObject) {
        return this.poolDataFootprintForRendering.get(IdObject); //FIXME
    }


    @Override
    public Iterator<DataFootprintForRendering> iterator() {
        return new PoolDataFootprintForRenderingIterator();
    }


    private class PoolDataFootprintForRenderingIterator implements Iterator<DataFootprintForRendering> {
        private Iterator iteratorMap;

        public PoolDataFootprintForRenderingIterator() {
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

        @Override
        public void remove() {
            //FIXME
        }

        @Override
        public void forEachRemaining(Consumer<? super DataFootprintForRendering> action) {
            //FIXME
        }
    }
}

