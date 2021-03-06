package Logic.FootprintSpaceTime;

import Logic.FootprintSpaceTime.Exeption.СrashIntoAnImpassableObjectExeption;
import Logic.IndexLayer;
import Logic.MovingObjects.ParametersMoving;
import Logic.MovingObjects.Path;
import Logic.Position;

import java.util.List;

public interface FootprintsSpaceTime {

    public List<Footprint> getRenderingFootprintsFromWhen(
            PolygonExtended areaFind,
            double time,
            IndexLayer indexLayer
    );
    public List<Footprint> getRenderingFootprintsFromWhenDefaultLayer(
            PolygonExtended areaFind,
            double time
    );

    public void addFootprint(
            int idTrack,
            ParametersMoving parametersMoving,
            Path path,
            double startTime,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption;

    public void addFootprint(
            Footprint footprint,
            double time,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption;

    public void deleteFootprints(int ID);

    public boolean getIsSeatTaken(PolygonExtended place, double time, IndexLayer indexLayer);

    public boolean getIsSeatTakenSpaceTime(
            PolygonExtended place,
            double fromTime,
            double toTime,
            IndexLayer indexLayer
    );


    public boolean isPathMovingObjectEnteringCorridor(
            ParametersMoving parametersMoving,
            Corridor corridor,
            IndexLayer indexLayer
    );

    public Double averageTimeMovingToNextPointOfPath();

    public Double totalTimeAllMoving();

    public Double getTimeAddingLastFootprints(IndexLayer indexLayer);

    public Position getPositionInDefaultLevel(ParametersMoving parametersMovingWithID, double time);





    /*TODO: влажные мечты: здесь можно сделать получение утромбованной точки размещения полигона и узнавание важности точки*/

    //TODO: critical получить собирательный образ среды (собирательные свойства, влияющих слоев; свойства эти будут переданые в общем виде, ибо мы не знаем на какие расстояния их ПЛАНИРУЮТ (этим и занимаются алгоритмы) вычиывать)

}