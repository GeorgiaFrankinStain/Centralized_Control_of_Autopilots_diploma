package com.alamutra.ccoa.Core.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Core.Logic.IndexLayer;
import com.alamutra.ccoa.Core.Logic.IndexLayerClass;
import com.alamutra.ccoa.Core.Logic.MovingBody.ParametersMoving;
import com.alamutra.ccoa.Core.Logic.MovingBody.PathCCoA;
import com.alamutra.ccoa.Core.Logic.Position;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FootprintsSpaceTimeClass implements FootprintsSpaceTime {
    private IndexLayer defaultIndexLayer = new IndexLayerClass(0);
    private Map<IndexLayer, LayerFootprintSpaceTime> layers =
            new TreeMap<IndexLayer, LayerFootprintSpaceTime>();


    public FootprintsSpaceTimeClass() {
        addLayer(defaultIndexLayer);
    }

    @Override
    public List<Footprint> getRenderingFootprintsFromWhen(PolygonCCoA areaFind, double time, IndexLayer indexLayer) {
        return this.layers.get(indexLayer).getRenderingFootprintsFromWhen(areaFind, time);
    }

    @Override
    public List<Footprint> getRenderingFootprintsFromWhenDefaultLayer(PolygonCCoA areaFind, double time) {
        return this.getRenderingFootprintsFromWhen(areaFind, time, this.defaultIndexLayer);
    }


    @Override
    public void addFootprintsPath(
            ParametersMoving parametersMoving,
            PathCCoA pathCCoA,
            double startTime,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption {
        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(indexLayer);
        if (layerFootprintSpaceTime == null) {
            this.addLayer(indexLayer);
        }

        Route route = new RouteClass();

        this.layers.get(indexLayer).addFootprintsPath(
                parametersMoving,
                pathCCoA,
                startTime,
                route
        );
    }

    @Override
    public void addFootprintsPathWithoutEndStandingUntilEndTime(
            ParametersMoving parametersMoving,
            PathCCoA pathCCoA,
            double startTime,
            IndexLayer indexLayer) throws СrashIntoAnImpassableObjectExeption {

        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(indexLayer);
        if (layerFootprintSpaceTime == null) {
            this.addLayer(indexLayer);
        }

        Route route = new RouteClass();

        this.layers.get(indexLayer).addFootprintsPathWithoutEndStandingUntilEndTime(
                parametersMoving,
                pathCCoA,
                startTime,
                route
        );
    }


    @Override
    public void addFootprint(
            Footprint footprint,
            double time,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption {
        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(indexLayer);
        if (layerFootprintSpaceTime == null) {
            this.addLayer(indexLayer);
        }


        this.layers.get(indexLayer).addFootprint(footprint, time);
    }

    @Override
    public void deleteFootprints(int ID) {

    }

    @Override
    public boolean isSeatTaken(
            PolygonCCoA place,
            double time,
            IndexLayer indexLayer
    ) {

        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(indexLayer);
        if (layerFootprintSpaceTime == null) {
            return false;
        }

        return layerFootprintSpaceTime.getIsSeatTaken(place, time);
    }

    @Override
    public boolean getIsSeatTakenSpaceTime(PolygonCCoA place, double fromTime, double toTime, IndexLayer indexLayer) {
        return this.isSeatTaken(place, fromTime, indexLayer) //FIXME
                && this.isSeatTaken(place, toTime, indexLayer)
                && this.isSeatTaken(place, (fromTime + toTime) / 2, indexLayer);
    }

    @Override
    public boolean isPathMovingObjectEnteringCorridor(ParametersMoving parametersMoving, Corridor corridor, IndexLayer indexLayer) {

        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(indexLayer);
        if (layerFootprintSpaceTime == null) {
            return false;
        }

        return layerFootprintSpaceTime.isPathMovingObjectEnteringCorridor(parametersMoving, corridor);
    }

    @Override
    public Double averageTimeMovingToNextPointOfPath() {
        for (Map.Entry entry : this.layers.entrySet()) {
            LayerFootprintSpaceTime value = (LayerFootprintSpaceTime) entry.getValue();
            Double localResult = value.getAverageTimeMovingToNextPointOfPath();
            if (localResult != null) {
                return localResult;
            }
        }

        return null;
    }

    @Override
    public Double totalTimeAllMoving() {
        for (Map.Entry entry : this.layers.entrySet()) {
            LayerFootprintSpaceTime value = (LayerFootprintSpaceTime) entry.getValue();
            Double localResult = value.totalTimeAllMoving();
            if (localResult != null) {
                return localResult;
            }
        }

        return null;
    }

    @Override
    public Double getTimeAddingLastFootprints(IndexLayer indexLayer) {
        LayerFootprintSpaceTime layerFootprintSpaceTime = this.layers.get(new IndexLayerClass(0));
        if (layerFootprintSpaceTime == null) {
            return null;
        }

        return layerFootprintSpaceTime.getTimeAddingLastFootprints();
    }

    @Override
    public Position getPositionInDefaultLevel(ParametersMoving parametersMovingWithID, double time) {
        //FIXME one machine with some ID in one moment time
        LayerFootprintSpaceTime layerFootprintSpaceTime = this.layers.get(new IndexLayerClass(0));
        if (layerFootprintSpaceTime == null) {
            return null;
        }

        return layerFootprintSpaceTime.getPosition(parametersMovingWithID, time);
    }


    //==== <start> <Private_Methods> =======================================================================
    private void addLayer(IndexLayer indexLayer) { //FIXME CODESTYLE
        LayerFootprintSpaceTime newLayerFootprintSpaceTime =
                new MultiMapLayerFootprintSpaceTimeClass();
        this.layers.put(indexLayer, newLayerFootprintSpaceTime);
    }
    //==== <end> <Private_Methods> =======================================================================
}

