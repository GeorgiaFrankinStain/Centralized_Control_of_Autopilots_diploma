package com.alamutra.CCoAWeb.Core.Controller;

import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTime;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.FootprintsSpaceTimeClass;
import com.alamutra.CCoAWeb.PercistanceDataAccessObjects.AdderMarks;
import com.alamutra.CCoAWeb.PercistanceDataAccessObjects.Exception.NotEnoughDataException;
import com.alamutra.CCoAWeb.PercistanceDataAccessObjects.StringAdderMarks;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.NoSuchElementException;

public class ManagerRoomClass implements ManagerRoom {
    StorageFootprintSpaceTimes storageRoom = new StorageFootprintSpaceTimeClass();

    @Override
    public String createNewRoom(String titleRoom) {
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
