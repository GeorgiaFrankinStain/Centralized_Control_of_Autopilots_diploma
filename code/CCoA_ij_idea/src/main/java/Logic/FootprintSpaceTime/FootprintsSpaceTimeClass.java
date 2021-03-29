package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.IndexLayer;
import Logic.IndexLayerClass;
import Logic.MovingObjects.ParametersMoving;
import Logic.MovingObjects.Path;
import GUI.StatementTaskRendering.HistChangesFromWhen;
import Logic.Position;

import java.util.*;

public class FootprintsSpaceTimeClass implements FootprintsSpaceTime, HistChangesFromWhen {
    private IndexLayer defaultIndexLayer = new IndexLayerClass(0);
    private Map<IndexLayer, LayerFootprintSpaceTime> layers =
            new TreeMap<IndexLayer, LayerFootprintSpaceTime>();


    public FootprintsSpaceTimeClass() {
        setLayer(defaultIndexLayer);
    }

    @Override
    public PolygonExtended getAreaChangesAfterBefore(int afterTime, int berforeTime) {
        return null;
    }

    @Override
    public int getTimeLastUpdate() {
        return 0;
    }


    @Override
    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaFind, double time, IndexLayer indexLayer) {
        return this.layers.get(indexLayer).getRenderingFootprintsFromWhen(areaFind, time);
    }

    @Override
    public List<Footprint> getRenderingFootprintsFromWhenDefaultLayer(PolygonExtended areaFind, double time) {
        return this.getRenderingFootprintsFromWhen(areaFind, time, this.defaultIndexLayer);
    }


    @Override
    public void addFootprint(
            int idTrack,
            ParametersMoving parametersMoving,
            Path path,
            double startTime,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption {
        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(indexLayer);
        if (layerFootprintSpaceTime == null) {
            this.setLayer(indexLayer);
        }

        this.layers.get(indexLayer).addFootprint(
                parametersMoving,
                path,
                startTime
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
            this.setLayer(indexLayer);
        }


        this.layers.get(indexLayer).addFootprint(footprint, time);
    }

    @Override
    public void deleteFootprints(int ID) {

    }

    @Override
    public boolean getIsSeatTaken(
            PolygonExtended place,
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
    public boolean getIsSeatTakenSpaceTime(PolygonExtended place, double fromTime, double toTime, IndexLayer indexLayer) {
        return this.getIsSeatTaken(place, fromTime, indexLayer) //FIXME
                && this.getIsSeatTaken(place, toTime, indexLayer)
                && this.getIsSeatTaken(place, (fromTime + toTime) / 2, indexLayer);
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
    private void setLayer(IndexLayer indexLayer) { //FIXME CODESTYLE
        LayerFootprintSpaceTime layerFootprintSpaceTime =
                new MultiMapLayerFootprintSpaceTimeClass();
        this.layers.put(indexLayer, layerFootprintSpaceTime);
    }
    //==== <end> <Private_Methods> =======================================================================
}
