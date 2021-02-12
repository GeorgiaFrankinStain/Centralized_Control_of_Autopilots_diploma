package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.LevelLayer;
import Logic.LevelLayerClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import GUI.StatementTaskRendering.HistChangesFromWhen;
import Logic.Position;

import java.util.*;

public class FootprintsSpaceTimeClass implements FootprintsSpaceTime, HistChangesFromWhen {
    private LevelLayer defaultLevelLayer = new LevelLayerClass(0);
    private Map<LevelLayer, LayerFootprintSpaceTime> layers =
            new TreeMap<LevelLayer, LayerFootprintSpaceTime>();


    public FootprintsSpaceTimeClass() {
        setLayer(defaultLevelLayer);
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
    public List<Footprint> getRenderingFootprintsFromWhen(PolygonExtended areaFind, double time, LevelLayer levelLayer) {
        return this.layers.get(levelLayer).getRenderingFootprintsFromWhen(areaFind, time);
    }

    @Override
    public List<Footprint> getRenderingFootprintsFromWhenDefaultLayer(PolygonExtended areaFind, double time) {
        return this.getRenderingFootprintsFromWhen(areaFind, time, this.defaultLevelLayer);
    }


    @Override
    public void addFootprint(
            int idTrack,
            MovingObject movingObject,
            Path path,
            double startTime,
            LevelLayer levelLayer
    ) throws СrashIntoAnImpassableObjectExeption {
        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(levelLayer);
        if (layerFootprintSpaceTime == null) {
            this.setLayer(levelLayer);
        }

        this.layers.get(levelLayer).addFootprint(
                idTrack,
                movingObject,
                path,
                startTime
        );
    }


    @Override
    public void addFootprint(
            Footprint footprint,
            double time,
            LevelLayer levelLayer
    ) throws СrashIntoAnImpassableObjectExeption {
        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(levelLayer);
        if (layerFootprintSpaceTime == null) {
            this.setLayer(levelLayer);
        }


        this.layers.get(levelLayer).addFootprint(footprint, time);
    }

    @Override
    public void deleteFootprints(int ID) {

    }

    @Override
    public boolean getIsSeatTaken(
            PolygonExtended place,
            double time,
            LevelLayer levelLayer
    ) {

        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(levelLayer);
        if (layerFootprintSpaceTime == null) {
            return false;
        }

        return layerFootprintSpaceTime.getIsSeatTaken(place, time);
    }

    @Override
    public boolean getIsSeatTakenSpaceTime(PolygonExtended place, double fromTime, double toTime, LevelLayer levelLayer) {
        return this.getIsSeatTaken(place, fromTime, levelLayer) //FIXME
                && this.getIsSeatTaken(place, toTime, levelLayer)
                && this.getIsSeatTaken(place, (fromTime + toTime) / 2, levelLayer);
    }

    @Override
    public boolean isPathMovingObjectEnteringCorridor(MovingObject movingObject, Corridor corridor, LevelLayer levelLayer) {

        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(levelLayer);
        if (layerFootprintSpaceTime == null) {
            return false;
        }

        return layerFootprintSpaceTime.isPathMovingObjectEnteringCorridor(movingObject, corridor);
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
    public Double getTimeAddingLastFootprints(LevelLayer levelLayer) {
        LayerFootprintSpaceTime layerFootprintSpaceTime = this.layers.get(new LevelLayerClass(0));
        if (layerFootprintSpaceTime == null) {
            return null;
        }

        return layerFootprintSpaceTime.getTimeAddingLastFootprints();
    }

    @Override
    public Position getPositionInDefaultLevel(MovingObject movingObjectWithID, double time) {
        //FIXME one machine with some ID in one moment time
        LayerFootprintSpaceTime layerFootprintSpaceTime = this.layers.get(new LevelLayerClass(0));
        if (layerFootprintSpaceTime == null) {
            return null;
        }

        return layerFootprintSpaceTime.getPosition(movingObjectWithID, time);
    }


    //==== <start> <Private_Methods> =======================================================================
    private void setLayer(LevelLayer levelLayer) { //FIXME CODESTYLE
        LayerFootprintSpaceTime layerFootprintSpaceTime =
                new MultiMapLayerFootprintSpaceTimeClass();
        this.layers.put(levelLayer, layerFootprintSpaceTime);
    }
    //==== <end> <Private_Methods> =======================================================================
}
