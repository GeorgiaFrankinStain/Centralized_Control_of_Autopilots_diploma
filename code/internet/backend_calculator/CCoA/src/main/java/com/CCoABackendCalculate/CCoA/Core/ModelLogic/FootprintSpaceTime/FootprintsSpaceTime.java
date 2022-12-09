package com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.CCoAWeb.Core.ModelLogic.IndexLayer;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.alamutra.CCoAWeb.Core.ModelLogic.MovingBody.PathCCoA;
import com.alamutra.CCoAWeb.Core.ModelLogic.Position;

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

    public void addFootprintsPath(
            ParametersMovingUnique parametersMovingUnique,
            PathCCoA pathCCoA,
            double startTime,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException;


    public void addFootprintsPathWithoutEndStandingUntilEndTime(
            ParametersMovingUnique parametersMovingUnique,
            PathCCoA pathCCoA,
            double startTime,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException;


    public void addFootprint(
            Footprint footprint,
            double time,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException;

    public void deleteFootprints(int ID);

    public boolean isSeatTaken(PolygonCCoA place, double time, IndexLayer indexLayer);

    public boolean getIsSeatTakenSpaceTime(
            PolygonCCoA place,
            double fromTime,
            double toTime,
            IndexLayer indexLayer
    );


    public boolean isPathMovingObjectEnteringCorridor(
            ParametersMovingUnique parametersMovingUnique,
            Corridor corridor,
            IndexLayer indexLayer
    );

    public Double averageTimeMovingToNextPointOfPath();

    public Double totalTimeAllMoving();

    public Double getTimeAddingLastFootprints(IndexLayer indexLayer);

    public Position getPositionInDefaultLevel(ParametersMovingUnique parametersMovingUniqueWithID, double time);

    public boolean equalsWithoutUniqueId(Object obj);




    /*TODO: влажные мечты: здесь можно сделать получение утромбованной точки размещения полигона и узнавание важности точки*/

    //TODO: critical получить собирательный образ среды (собирательные свойства, влияющих слоев; свойства эти будут переданые в общем виде, ибо мы не знаем на какие расстояния их ПЛАНИРУЮТ (этим и занимаются алгоритмы) вычиывать)

}
