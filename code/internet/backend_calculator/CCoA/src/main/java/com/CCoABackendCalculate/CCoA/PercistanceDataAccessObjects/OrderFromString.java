package com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AStar.AStarSpaceTimePlanarGraphClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.AlhorithmFastFindPath;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.FabricNetworkNodes.FabricHexagonNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.ControllerMachines.FabricNetworkNodes.FabricNetworkNodes;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.*;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayer;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.IndexLayerClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.MovingBody.*;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.TypesInLevel;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.TypeMachinesBody;
import com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects.Exception.NotEnoughDataException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class OrderFromString implements Order {

    private PolygonCCoA form;

    private double speed;

    private TypeMachinesBody typeMachinesBody;

    private TypesInLevel typeInLevel = TypesInLevel.OBJECT;

    private PointCCoA startCoordinate;
    private IndexLayer startLayer = new IndexLayerClass(0);
    private double startTime;
    private double startAngle = 0.0;
    private PointCCoA endCoordinate;
    private IndexLayer endLayer;

    private double endAngle;
    private boolean isSetAngleEnd;

    private boolean isStanding;

    private boolean isNeedExtractionDynamicParameters = false;
    private boolean isStandingAfter;

    public OrderFromString(JsonObject orderJsonObject) throws Exception {

        extractionStanding(orderJsonObject);

        extractionParameterMoving(orderJsonObject);

        extractionStart(orderJsonObject);


        if (isNeedExtractionDynamicParameters) {
            extractionEndOperation(orderJsonObject);
        }

        extractionStandingAfter(orderJsonObject);
    }


    public String toJson() {
        return this.toJson();
    }

    @Override
    public void mark(FootprintsSpaceTime footprintsSpaceTime) { //FIXME add tests result adding in FootprintSpaceTime

        //FIXME type_in_level
        ParametersMovingUnique parametersMovingUnique = determinationParametersMovingUnique();

        FabricNetworkNodes fabricNetworkNodes = new FabricHexagonNodes(GlobalVariable.getAccuracyMoving(), parametersMovingUnique);

        AlhorithmFastFindPath fastFinderPath = new AStarSpaceTimePlanarGraphClass(fabricNetworkNodes, footprintsSpaceTime);

        if (isStanding) {
            PathCCoA wallPath = new PathCCoAClass();
            wallPath.addPoint(this.startCoordinate);
            try {
                parametersMovingUnique.mark(footprintsSpaceTime, wallPath, this.startTime, this.startLayer);  //FIXME set start and end layer //FIXME end standing
            } catch (СrashIntoAnImpassableObjectException e) {
            }
        } else {
            PathCCoA actualPath = fastFinderPath.getPath(this.startCoordinate, this.endCoordinate, parametersMovingUnique, this.startTime); //FIXME set start and end layer
            try {
                parametersMovingUnique.mark(footprintsSpaceTime, actualPath, this.startTime, this.startLayer);  //FIXME set start and end layer //FIXME end standing
            } catch (СrashIntoAnImpassableObjectException ex) {
            }
        }
    }

    private ParametersMovingUnique determinationParametersMovingUnique() {
        boolean isStandardParametersMovingObjects = this.typeMachinesBody != null;
        if (isStandardParametersMovingObjects) {
            FabricParametersMovingUnique fabricParametersMovingUnique = new FabricParametersMovingUniqueClass();
            ParametersMovingUnique parametersMovingUnique = fabricParametersMovingUnique.getMoving(this.typeMachinesBody);
            return parametersMovingUnique;
        } else {
            BuilderParametersMovingUnique builder = new BuilderParametersMovingUniqueClass();
            builder.setSpeed(this.speed);
            builder.setShape(this.form);

            return builder.getParametersMoving();
        }
    }

    public PolygonCCoA getForm() {
        return form;
    }

    public double getSpeed() {
        return speed;
    }

    public TypeMachinesBody getTypeMachinesBody() {
        return typeMachinesBody;
    }

    public TypesInLevel getTypeInLevel() {
        return typeInLevel;
    }

    public PointCCoA getStartCoordinate() {
        return startCoordinate;
    }

    public IndexLayer getStartLayer() {
        return startLayer;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getStartAngle() {
        return startAngle;
    }

    public PointCCoA getEndCoordinate() {
        return endCoordinate;
    }

    public IndexLayer getEndLayer() {
        return endLayer;
    }

    public double getEndAngle() {
        return endAngle;
    }

    public boolean isSetAngleEnd() {
        return isSetAngleEnd;
    }

    public boolean isStanding() {
        return isStanding;
    }

    public boolean isStandingAfter() {
        return isStandingAfter;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        OrderFromString other = (OrderFromString) obj;

        return this.getForm().equals(other.getForm())
                && this.getStartCoordinate().equals(other.getStartCoordinate()) //FIXME add test
                && this.getTypeInLevel().equals(other.getTypeInLevel())
                && this.getStartLayer().equals(other.getStartLayer())
                && GlobalVariable.equalsNumber(this.getStartTime(), other.getStartTime())
                && GlobalVariable.equalsNumber(this.getStartAngle(), other.getStartAngle())
                && this.getEndCoordinate().equals(other.getEndCoordinate())
                && this.getEndLayer().equals(other.getStartLayer())
                && GlobalVariable.equalsNumber(this.getEndAngle(), other.getEndAngle())
                && this.isSetAngleEnd() == other.isSetAngleEnd()
                && this.isStanding() == other.isStanding()
                && this.isStandingAfter() == other.isStandingAfter();
    }


    private void extractionStandingAfter(JsonObject orderJsonObject) throws NotEnoughDataException {
        JsonElement standingAfterElement = orderJsonObject.get("standing_after");
        if (standingAfterElement == null) {
            throw new NotEnoughDataException("standing_after is missing");
        }
        this.isStandingAfter = standingAfterElement.getAsBoolean();
    }

    private void extractionStanding(JsonObject orderJsonObject) throws NotEnoughDataException {
        JsonElement standingElement = orderJsonObject.get("standing");
        if (standingElement == null) {
            isNeedExtractionDynamicParameters = true;
        }
        else {
            this.isStanding = standingElement.getAsBoolean();
            boolean isDynamicMovingObject = !this.isStanding;
            isNeedExtractionDynamicParameters = isDynamicMovingObject;
        }

    }

    private void extractionParameterMoving(JsonObject orderJson) throws NotEnoughDataException {

        JsonObject parametersMovingJson = extractionParametersMoving(orderJson);

        extractionTypes(parametersMovingJson);

        if (isNeedExtractionDynamicParameters) {
            extractionSpeed(parametersMovingJson);
        }

        extractionUniquePolygonForm(parametersMovingJson);
    }

    private void extractionUniquePolygonForm(JsonObject parametersMovingJson) throws NotEnoughDataException {
        boolean isNeedExtractionUniquePolygonForm = false;
        if (this.typeMachinesBody == null) {
            isNeedExtractionUniquePolygonForm = true;
        } else {
            isNeedExtractionUniquePolygonForm = this.typeMachinesBody.equals("");
        }
        if (isNeedExtractionUniquePolygonForm) {
            extractionUniquePolygon(parametersMovingJson);
        }
    }


    private JsonObject extractionParametersMoving(JsonObject orderJson) throws NotEnoughDataException {
        JsonElement parametersMovingTest = orderJson.get("parameters_moving");
        if (parametersMovingTest == null) {
            throw new NotEnoughDataException("parameters_moving is missing");
        }
        JsonObject parametersMovingJson = parametersMovingTest.getAsJsonObject();
        return parametersMovingJson;
    }

    private void extractionSpeed(JsonObject parametersMovingJson) throws NotEnoughDataException {
        JsonElement speedElementJson = parametersMovingJson.get("speed");

        if (speedElementJson == null) {
            throw new NotEnoughDataException("speed is missing");
        }

        this.speed = speedElementJson.getAsDouble();

        if (this.speed < 0) {
            throw new NotEnoughDataException("speed is negative");
        }

        boolean isDynamicMovingObject = !this.isStanding;
        if (GlobalVariable.equalsNumber(this.speed, 0) && isDynamicMovingObject) {
            throw new NotEnoughDataException("speed is 0 for dynamic moving object");
        }
    }

    private void extractionUniquePolygon(JsonObject parametersMovingJson) throws NotEnoughDataException {

        JsonArray polygonPoints = parametersMovingJson.getAsJsonArray("polygon_form");
        if (polygonPoints == null) {
            throw new NotEnoughDataException("polygon_form or type_machines_body is missing");
        }
        PolygonCCoA polygonForm = new PolygonCCoAClass();
        for (int i = 0; i < polygonPoints.size(); i++) {
            JsonObject iPoint = polygonPoints.get(i).getAsJsonObject();
            double x = iPoint.get("x").getAsDouble();
            double y = iPoint.get("y").getAsDouble();
            polygonForm.addPoint(new PointCCoAClass(x, y));
        }

        if (polygonForm.getCountPoints() < 3) {
            throw new NotEnoughDataException("polygon_form has minimum 3 points");
        }

        double areaPolygon = polygonForm.getArea();
        if (areaPolygon <= 0) {
            throw new NotEnoughDataException("polygon_form has positive area (area > 0)");
        }

        this.form = polygonForm;


        //verification polygon has area > 0 //FIXME
    }

    private void extractionTypes(JsonObject orderJson) throws NotEnoughDataException {


        JsonObject typeJson = extractionType(orderJson);

        extractionTypeInLevel(typeJson);

        extractionTypeMachinesBody(typeJson);
    }

    private JsonObject extractionType(JsonObject orderJson) throws NotEnoughDataException {

        JsonElement typeElement = orderJson.get("type");
        if (typeElement == null) {
            throw new NotEnoughDataException("type is missing");
        }
        JsonObject typeJson = typeElement.getAsJsonObject();
        return typeJson;
    }

    private void extractionTypeInLevel(JsonObject typeJson) throws NotEnoughDataException {
        JsonElement typeInLevelElement = typeJson.get("type_in_layer");
        if (typeInLevelElement == null) {
            throw new NotEnoughDataException("type_in_layer is missing");
        }
        String title = typeInLevelElement.getAsString();
        this.typeInLevel = TypesInLevel.valueOf(title);
    }

    private void extractionTypeMachinesBody(JsonObject typeJson) {
        JsonElement typeMachinesBodyTitleElement = typeJson.get("type_machines_body");
        if (typeMachinesBodyTitleElement != null) {
            String typeMachinesBodyTitle = typeMachinesBodyTitleElement.getAsString();

            this.typeMachinesBody = TypeMachinesBody.valueOf(typeMachinesBodyTitle);
        }
    }

    private void extractionStart(JsonObject orderJson) throws NotEnoughDataException {


        JsonObject startJson = extractionStartJsonObject(orderJson);

        extractionStartPoint(startJson);

        extractionStartTime(startJson);

        extractionStartAngle(startJson);

        extractionStartLayer(startJson);
    }

    private JsonObject extractionStartJsonObject(JsonObject orderJson) throws NotEnoughDataException {

        JsonElement startElement = orderJson.get("start");
        if (startElement == null) {
            throw new NotEnoughDataException("start is missing");
        }
        JsonObject startJson = startElement.getAsJsonObject();
        return startJson;
    }

    private void extractionStartTime(JsonObject startJson) throws NotEnoughDataException {

        JsonElement startTimeElement = startJson.get("time");
        if (startTimeElement == null) {
            throw new NotEnoughDataException("start time is missing");
        }
        this.startTime = startTimeElement.getAsDouble();
    }

    private void extractionStartAngle(JsonObject startJson) {
        JsonElement angleStartElement = startJson.get("angle");
        if (angleStartElement != null) {
            this.startAngle = angleStartElement.getAsDouble();
        }
    }

    private void extractionStartLayer(JsonObject startJson) {
        JsonElement layerNumberElement = startJson.get("layer");
        if (layerNumberElement != null) {
            int layerNumber = layerNumberElement.getAsInt();
            this.startLayer = new IndexLayerClass(layerNumber);
        }
    }

    private void extractionStartPoint(JsonObject startJson) throws NotEnoughDataException {

        JsonObject pointJson = extractionStartPointJson(startJson);

        double xStartPoint = extractionXStartPoint(pointJson);

        double yStartPoint = extractionYStartPoint(pointJson);

        this.startCoordinate = new PointCCoAClass(xStartPoint, yStartPoint);
    }

    private JsonObject extractionStartPointJson(JsonObject startJson) throws NotEnoughDataException {
        JsonElement startPointElement = startJson.get("coordinate");
        if (startPointElement == null) {
            throw new NotEnoughDataException("start coordinate is missing");
        }
        JsonObject pointJson = startPointElement.getAsJsonObject();
        return pointJson;
    }

    private double extractionXStartPoint(JsonObject pointJson) throws NotEnoughDataException {
        JsonElement xPointStart = pointJson.get("x");
        if (xPointStart == null) {
            throw new NotEnoughDataException("x point start coordinate is missing");
        }
        double xStartPoint = xPointStart.getAsDouble();

        return xStartPoint;
    }

    private double extractionYStartPoint(JsonObject pointJson) throws NotEnoughDataException {
        JsonElement yPointStart = pointJson.get("y");
        if (yPointStart == null) {
            throw new NotEnoughDataException("y point start coordinate is missing");
        }
        double yStartPoint = yPointStart.getAsDouble();
        return yStartPoint;
    }
    private void extractionEndOperation(JsonObject orderJson) throws NotEnoughDataException {

        JsonObject endJson = extractionEndJsonObject(orderJson);

        extractionEndPoint(endJson);

        extractionEndAngle(endJson);

        extractionEndLayer(endJson);
    }

    private JsonObject extractionEndJsonObject(JsonObject orderJson) throws NotEnoughDataException {

        JsonElement endElement = orderJson.get("end");
        if (endElement == null) {
            throw new NotEnoughDataException("end or standing is missing");
        }

        JsonObject endJson = endElement.getAsJsonObject();
        return endJson;
    }

    private void extractionEndAngle(JsonObject endJson) {
        JsonElement angleElement = endJson.get("angle");
        if (angleElement == null) {
            this.isSetAngleEnd = false;
        } else {
            this.isSetAngleEnd = true;
            this.endAngle = angleElement.getAsDouble();
        }
    }

    private void extractionEndLayer(JsonObject endJson) {
        JsonElement layerElement = endJson.get("layer");
        if (layerElement != null) {
            int layerNumber = layerElement.getAsInt();
            this.endLayer = new IndexLayerClass(layerNumber);
        }
    }

    private void extractionEndPoint(JsonObject endJson) throws NotEnoughDataException {
        JsonObject pointJson = extractionEndPointJson(endJson);

        double xStartPoint = extractionXEndPoint(pointJson);

        double yStartPoint = extractionYEndPoint(pointJson);

        this.endCoordinate = new PointCCoAClass(xStartPoint, yStartPoint);
    }

    private JsonObject extractionEndPointJson(JsonObject endJson) throws NotEnoughDataException {
        JsonElement coordinateElement = endJson.get("coordinate");
        if (coordinateElement == null) {
            throw new NotEnoughDataException("coordinate end is missing");
        }
        JsonObject pointJson = coordinateElement.getAsJsonObject();
        return pointJson;
    }

    private double extractionXEndPoint(JsonObject pointJson) throws NotEnoughDataException {

        JsonElement pointXElement = pointJson.get("x");
        if (pointXElement == null) {
            throw new NotEnoughDataException("x end is missing");
        }
        double xStartPoint = pointXElement.getAsDouble();
        return xStartPoint;
    }
    private double extractionYEndPoint(JsonObject pointJson) throws NotEnoughDataException {

        JsonElement pointYElement = pointJson.get("y");
        if (pointYElement == null) {
            throw new NotEnoughDataException("у end is missing");
        }
        double yStartPoint = pointYElement.getAsDouble();
        return yStartPoint;
    }


}
