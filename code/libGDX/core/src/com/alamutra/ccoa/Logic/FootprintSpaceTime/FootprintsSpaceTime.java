package com.alamutra.ccoa.Logic.FootprintSpaceTime;

import com.alamutra.ccoa.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectExeption;
import com.alamutra.ccoa.Logic.IndexLayer;
import com.alamutra.ccoa.Logic.MovingObjects.ParametersMoving;
import com.alamutra.ccoa.Logic.MovingObjects.PathCCoA;
import com.alamutra.ccoa.Logic.Position;

import java.util.List;

public interface FootprintsSpaceTime {

    public List<Footprint> getRenderingFootprintsFromWhen(
            PolygonCCoA areaFind,
            double time,
            IndexLayer indexLayer
    );
    public List<Footprint> getRenderingFootprintsFromWhenDefaultLayer(
            PolygonCCoA areaFind,
            double time
    );

    public void addFootprint(
            ParametersMoving parametersMoving,
            PathCCoA pathCCoA,
            double startTime,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption;

    public void addFootprint(
            Footprint footprint,
            double time,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectExeption;

    public void deleteFootprints(int ID);

    public boolean getIsSeatTaken(PolygonCCoA place, double time, IndexLayer indexLayer);

    public boolean getIsSeatTakenSpaceTime(
            PolygonCCoA place,
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
