package com.alamutra.CCoAWeb.PercistanceDataAccessObjects;

import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.AStar.AStarSpaceTimePlanarGraphClass;
import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.AlhorithmFastFindPath;
import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.FabricNetworkNodes.FabricHexagonNodes;
import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.FabricNetworkNodes.FabricNetworkNodes;
import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.Hexagon.HexagonTile;
import com.alamutra.CCoAWeb.Core.Logic.ControllerMachines.NetworkNodes;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.Exception.СrashIntoAnImpassableObjectException;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoA;
import com.alamutra.CCoAWeb.Core.Logic.FootprintSpaceTime.PointCCoAClass;
import com.alamutra.CCoAWeb.Core.Logic.IndexLayer;
import com.alamutra.CCoAWeb.Core.Logic.IndexLayerClass;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.FabricParametersMovingUnique;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.FabricParametersMovingUniqueClass;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.ParametersMovingUnique;
import com.alamutra.CCoAWeb.Core.Logic.MovingBody.PathCCoA;
import com.alamutra.CCoAWeb.Core.SettingRenderingTasks.TypeMachinesBody;
import com.alamutra.CCoAWeb.PercistanceDataAccessObjects.Exception.NotEnoughDataException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringAdderMarks implements AdderMarks {

    List<Order> orders = new ArrayList<>();

    public StringAdderMarks(String jsonMapAim) throws Exception {

        JsonObject input = extractionInput(jsonMapAim);

        validateVersion(input);

        JsonArray ordersJson = extractionOrders(input);
        for (int i = 0; i < ordersJson.size(); i++) {
            JsonObject iJson = ordersJson.get(i).getAsJsonObject();
            orders.add(new OrderFromString(iJson));
        }
    }

    @Override
    public void addMarksIn(FootprintsSpaceTime footprintsSpaceTime) {
        for (Order order : orders) {
            order.mark(footprintsSpaceTime);
        }
    }

    @Override
    public List<Order> getOrders() {
        return this.orders;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;


        AdderMarks other = (AdderMarks) obj;
        return Arrays.equals(this.orders.toArray(), other.getOrders().toArray());
    }

    private JsonObject extractionInput(String jsonMapAim) throws NotEnoughDataException {
        JsonElement ordersJsonElements = JsonParser.parseString(jsonMapAim);
        if (ordersJsonElements == null) {
            throw new NotEnoughDataException("json is missing (empty json input)");
        }

        JsonObject input = ordersJsonElements.getAsJsonObject();
        return input;
    }

    private void validateVersion(JsonObject input) throws Exception {
        JsonElement versionElement = input.get("version");
        if (versionElement == null) {
            throw new NotEnoughDataException("version is missing");
        }
        String version = versionElement.getAsString();
        if (!version.equals("1")) {
            throw new Exception("unknown version");
        }
    }

    private JsonArray extractionOrders(JsonObject input) throws NotEnoughDataException {
        JsonElement ordersElement = input.get("orders");

        if (ordersElement == null) {
            throw new NotEnoughDataException("orders is missing");
        }

        JsonArray ordersJson =  ordersElement.getAsJsonArray();
        return ordersJson;
    }

}
