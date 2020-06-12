package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObstacleExeption;
import Logic.LevelLayer;
import Logic.LevelLayerClass;
import Logic.MovingObjects.MovingObject;
import Logic.MovingObjects.Path;
import GUI.StatementTaskRendering.HistChangesFromWhen;
import Logic.Position;

import java.util.*;

public class FootprintsSpaceTimeClass implements FootprintsSpaceTime, HistChangesFromWhen {

    Map<LevelLayer, LayerFootprintSpaceTime> layers =
            new TreeMap<LevelLayer, LayerFootprintSpaceTime>();

    public FootprintsSpaceTimeClass() {
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
    public void addFootprint(
            int idTrack,
            MovingObject movingObject,
            Path path,
            double startTime,
            LevelLayer levelLayer
    ) throws СrashIntoAnImpassableObstacleExeption {
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
    ) throws СrashIntoAnImpassableObstacleExeption {
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
        double averageTime = (fromTime + toTime) / 2;
        return this.getIsSeatTaken(place, averageTime, levelLayer);
    }

    @Override
    public Double averageTimeMovingToNextPointOfPath() {
        for(Map.Entry entry: this.layers.entrySet()) {
            LayerFootprintSpaceTime value = (LayerFootprintSpaceTime) entry.getValue();
            Double localResult = value.getAverageTimeMovingToNextPointOfPath();
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
    public Position getPositionInDefaultLevel(int ID, double time) {
        //FIXME one machine with some ID in one moment time
        LayerFootprintSpaceTime layerFootprintSpaceTime = this.layers.get(new LevelLayerClass(0));
        if (layerFootprintSpaceTime == null) {
            return null;
        }

        return layerFootprintSpaceTime.getPosition(ID, time);
    }


    //==== <start> <Private_Methods> =======================================================================
    private void setLayer(LevelLayer levelLayer) { //FIXME CODESTYLE
        LayerFootprintSpaceTime layerFootprintSpaceTime =
                new LayerFootprintSpaceTimeClass();
        this.layers.put(levelLayer, layerFootprintSpaceTime);
    }
    //==== <end> <Private_Methods> =======================================================================
}
