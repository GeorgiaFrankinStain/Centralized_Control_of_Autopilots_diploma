package com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayer;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayerClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.ParametersMovingUnique;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.PathCCoA;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.Position;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.HistoryChanges;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.*;

public class FootprintsSpaceTimeClass implements FootprintsSpaceTime, HistoryChanges {
    private IndexLayer defaultIndexLayer = new IndexLayerClass(0);
    private Map<IndexLayer, LayerFootprintSpaceTime> layers = new TreeMap<IndexLayer, LayerFootprintSpaceTime>();

    private int lastIssuedId = 3; //LINK_ldKzPu (text find) before builder numerator for test start from 3
    private Set<Integer> setIdMovingObjects = new HashSet<>();


    public FootprintsSpaceTimeClass() {
        addLayer(defaultIndexLayer);
    }

    @Override
    public List<Footprint> getRenderingFootprintsWhen(double time) {
        List<Footprint> footprints = new ArrayList<>();
        for (LayerFootprintSpaceTime layer : this.layers.values()) {
            footprints.addAll(layer.getRenderingFootprintsWhen(time));
        }
        return footprints;
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
            ParametersMovingUnique parametersMovingUnique,
            PathCCoA pathCCoA,
            double startTime,
            IndexLayer indexLayer
    ) throws СrashIntoAnImpassableObjectException {
        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(indexLayer);
        if (layerFootprintSpaceTime == null) {
            this.addLayer(indexLayer);
        }

        this.setIdMovingObjects.add(parametersMovingUnique.getID());

        Route route = new RouteClass();

        this.layers.get(indexLayer).addFootprintsPath(
                parametersMovingUnique,
                pathCCoA,
                startTime,
                route
        );
    }

    @Override
    public void addFootprintsPathWithoutEndStandingUntilEndTime(
            ParametersMovingUnique parametersMovingUnique,
            PathCCoA pathCCoA,
            double startTime,
            IndexLayer indexLayer) throws СrashIntoAnImpassableObjectException {

        this.setIdMovingObjects.add(parametersMovingUnique.getID());

        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(indexLayer);
        if (layerFootprintSpaceTime == null) {
            this.addLayer(indexLayer);
        }

        Route route = new RouteClass();

        this.layers.get(indexLayer).addFootprintsPathWithoutEndStandingUntilEndTime(
                parametersMovingUnique,
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
    ) throws СrashIntoAnImpassableObjectException {
        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(indexLayer);
        if (layerFootprintSpaceTime == null) {
            this.addLayer(indexLayer);
        }

        this.setIdMovingObjects.add(footprint.getMovingObject().getID());


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
                || this.isSeatTaken(place, toTime, indexLayer)
                || this.isSeatTaken(place, (fromTime + toTime) / 2, indexLayer);
    }

    @Override
    public boolean isPathMovingObjectEnteringCorridor(ParametersMovingUnique parametersMovingUnique, Corridor corridor, IndexLayer indexLayer) {

        LayerFootprintSpaceTime layerFootprintSpaceTime =
                this.layers.get(indexLayer);
        if (layerFootprintSpaceTime == null) {
            return false;
        }

        return layerFootprintSpaceTime.isPathMovingObjectEnteringCorridor(parametersMovingUnique, corridor);
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
    public Position getPositionInDefaultLevel(ParametersMovingUnique parametersMovingUniqueWithID, double time) {
        //FIXME one machine with some ID in one moment time
        LayerFootprintSpaceTime layerFootprintSpaceTime = this.layers.get(new IndexLayerClass(0));
        if (layerFootprintSpaceTime == null) {
            return null;
        }

        return layerFootprintSpaceTime.getPosition(parametersMovingUniqueWithID, time);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootprintsSpaceTimeClass that = (FootprintsSpaceTimeClass) o;
        return layers.equals(that.layers);
    }

    @Override
    public boolean equalsWithoutUniqueId(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FootprintsSpaceTimeClass that = (FootprintsSpaceTimeClass) obj;

        if (that.layers.size() != this.layers.size()) {
            return false;
        }

        Iterator<Map.Entry<IndexLayer, LayerFootprintSpaceTime>> iterator = that.layers.entrySet().iterator();
        for (Map.Entry<IndexLayer, LayerFootprintSpaceTime> layerEntryPair : layers.entrySet()) {

            Map.Entry<IndexLayer, LayerFootprintSpaceTime> otherEntryPair = iterator.next();

            IndexLayer otherIndexLayer = otherEntryPair.getKey();
            IndexLayer thisIndexLayer = layerEntryPair.getKey();

            if (!otherIndexLayer.equals(thisIndexLayer)) {
                return false;
            }

            LayerFootprintSpaceTime otherLayer = otherEntryPair.getValue();
            LayerFootprintSpaceTime thisLayer = layerEntryPair.getValue();

            if (!otherLayer.equalsWithoutUniqueId(thisLayer)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getIdForNewParametersMovingUnique() { //FIXME add tests

        while (this.setIdMovingObjects.contains(lastIssuedId)) {
            this.lastIssuedId++;
        }
        return this.lastIssuedId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(layers);
    }

    @Override
    public JsonObject getElbowDTOToEndCalculateExistJSONFrom(Double fromTime) {
        List<Footprint> allFirstFootprintsFromTime = this.getRenderingFootprintsWhen(fromTime);
        //get footprint approximation from fromTime

        JsonArray elbowOrdersArray = new JsonArray();

        for (Footprint movingObjectFirstFootprint : allFirstFootprintsFromTime) {


            ParametersMovingUnique parametersMovingUnique = movingObjectFirstFootprint.getMovingObject();
            JsonObject elbowOrdersJson = getJsonWithParametersUniqueMoving(parametersMovingUnique);

            JsonArray routeTimeSpaceCoordinates = getJsonItemArrayRoute(movingObjectFirstFootprint);

            elbowOrdersJson.add("timeSpaceCoordinates", routeTimeSpaceCoordinates);

            elbowOrdersArray.add(elbowOrdersJson);
        }


        JsonObject result = new JsonObject();

        result.add("elbow_moving_objects", elbowOrdersArray);


        return result;

    }

    //==== <start> <Private_Methods> =======================================================================

    private JsonArray getJsonItemArrayRoute(Footprint movingObjectFirstFootprint) {
        double timeMoment = 0;
        Footprint currentFootprint = movingObjectFirstFootprint;

        JsonArray timeSpaceCoordinatesJson = new JsonArray();

        while (true) {

            timeSpaceCoordinatesJson.add(getTimeSpaceCoordinate(currentFootprint, timeMoment));


            boolean isNeedAddLastPointInEndlessFuture = currentFootprint.isStanding();
            if (isNeedAddLastPointInEndlessFuture) {
                timeSpaceCoordinatesJson.add(getTimeSpaceCoordinate(currentFootprint, GlobalVariable.MAX_TIME_STANDING));
            }


            timeMoment += currentFootprint.getTimeToNextFootprint();
            currentFootprint = currentFootprint.getNextFootprint();

            if (currentFootprint == null) {
                break;
            }
        }

        return timeSpaceCoordinatesJson;
    }

    private JsonObject getTimeSpaceCoordinate(Footprint currentFootprint, double timeMoment) {

        JsonObject pointRoute = new JsonObject();

        pointRoute.add("t", doubleToJsonElement(timeMoment));
        int imitationDefaultLevel = 0;
        pointRoute.add("layer", intToJsonElement(imitationDefaultLevel));
        pointRoute.add("x", doubleToJsonElement(currentFootprint.getCoordinat().getX()));
        pointRoute.add("y", doubleToJsonElement(currentFootprint.getCoordinat().getY()));
        double imitationAngle = 0;
        pointRoute.add("angle", doubleToJsonElement(imitationAngle));

        return pointRoute;
    }

    private JsonObject getJsonWithParametersUniqueMoving(ParametersMovingUnique parametersMovingUnique) {
        JsonObject parametersJson = new JsonObject();

        parametersJson.add("id_moving_unique_object", intToJsonElement(parametersMovingUnique.getID()));
        parametersJson.add("appearanceType", getTypeMachineBody(parametersMovingUnique));
        parametersJson.add("appearancePolygonForm", getJsonFormMachine(parametersMovingUnique));

        return parametersJson;
    }
    private JsonElement getTypeMachineBody(ParametersMovingUnique parametersMovingUnique) {
        if (parametersMovingUnique.getTypeMachinesBody() == TypeMachinesBody.TEST_SQUARE_20) {
            return JsonParser.parseString(TypeMachinesBody.TEST_SQUARE_20.toString());
        } else {
            return JsonParser.parseString("non-uniform");
        }
    }

    private JsonElement intToJsonElement(int number) {
        String numberString = String.valueOf(number);
        return JsonParser.parseString(numberString);
    }

    private JsonArray getJsonFormMachine(ParametersMovingUnique parametersMovingUnique) {
        JsonArray formJsonArray = new JsonArray();

        PolygonCCoA polygonForm = parametersMovingUnique.getShape();
        for (int i = 0; i < polygonForm.getCountPoints(); i++) {
            PointCCoA currentPoint = polygonForm.getPoint(i);

            JsonObject pointJson = new JsonObject();
            pointJson.add("x", doubleToJsonElement(currentPoint.getX()));
            pointJson.add("y", doubleToJsonElement(currentPoint.getY()));

            formJsonArray.add(pointJson);
        }

        return formJsonArray;
    }


    private JsonElement doubleToJsonElement(double number) {
        String numberString = String.valueOf(number);
        return JsonParser.parseString(numberString);
    }

    private void addLayer(IndexLayer indexLayer) { //FIXME CODESTYLE
        LayerFootprintSpaceTime newLayerFootprintSpaceTime =
                new MultiMapLayerFootprintSpaceTimeClass();
        this.layers.put(indexLayer, newLayerFootprintSpaceTime);
    }
    //==== <end> <Private_Methods> =======================================================================
}

