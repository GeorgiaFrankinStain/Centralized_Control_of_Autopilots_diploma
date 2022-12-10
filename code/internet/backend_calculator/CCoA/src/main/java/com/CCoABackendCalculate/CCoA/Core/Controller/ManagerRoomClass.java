package com.CCoABackendCalculate.CCoA.Core.Controller;

import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects.AdderMarks;
import com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects.Exception.NotEnoughDataException;
import com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects.StringAdderMarks;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.NoSuchElementException;

public class ManagerRoomClass implements ManagerRoom {
    StorageFootprintSpaceTimes storageRoom = new StorageFootprintSpaceTimeClass();

    @Override
    public String createNewRoom() {
        FootprintsSpaceTime newFootprintsSpaceTime = new FootprintsSpaceTimeClass();

        String id = storageRoom.addFootprintSpaceTime(newFootprintsSpaceTime);

        return id;
    }

    @Override
    public void toApplicationOrders(String jsonOrders) throws Exception {
        JsonObject input = extractionInput(jsonOrders);

        String idRoom = extractionIdRoom(input);

        FootprintsSpaceTime footprintsSpaceTime = this.storageRoom.get(idRoom);

        if (footprintsSpaceTime == null) {
            throw new NoSuchElementException("not found footprintSpaceTime");
        }

        AdderMarks adderMarks = new StringAdderMarks(jsonOrders);
        adderMarks.addMarksIn(footprintsSpaceTime);
    }

    private String extractionIdRoom(JsonObject input) throws NotEnoughDataException {
        JsonElement idRoomElement = input.get("id_room");
        if (idRoomElement == null) {
            throw new NotEnoughDataException("id_room is missing");
        }
        return idRoomElement.getAsString();
    }



    private JsonObject extractionInput(String jsonMapAim) throws NotEnoughDataException {
        JsonElement ordersJsonElements = JsonParser.parseString(jsonMapAim);
        if (ordersJsonElements == null) {
            throw new NotEnoughDataException("json is missing (empty json input)");
        }

        JsonObject input = ordersJsonElements.getAsJsonObject();
        return input;
    }
}
