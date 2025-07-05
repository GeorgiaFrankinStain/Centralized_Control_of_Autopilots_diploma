package com.CCoABackendCalculate.CCoA.SpringController;

import com.CCoABackendCalculate.CCoA.Core.Controller.ManagerRoom;
import com.CCoABackendCalculate.CCoA.Core.Controller.ManagerRoomClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.RouteClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.PathMovingUniqueJSON;
import com.CCoABackendCalculate.CCoA.Core.Wrappers.RandomWrapper;
import com.CCoABackendCalculate.CCoA.Core.Wrappers.RandomWrapperClass;
import com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects.AdderMarks;
import com.CCoABackendCalculate.CCoA.PercistanceDataAccessObjects.StringAdderMarks;
import com.CCoABackendCalculate.CCoA.SpringModel.OrderDTO;
import com.CCoABackendCalculate.CCoA.SpringModel.RoomDTO;
import com.CCoABackendCalculate.CCoA.SpringModel.StatusResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ManagerRoomController {
    private static final Logger LOG = LoggerFactory.getLogger(ManagerRoomController.class);
    private ManagerRoom managerRoom = new ManagerRoomClass();

    @CrossOrigin(origins = {"${settings.cors_origin}"})
    @PostMapping(value = "/create_room", produces = "application/json")
    public RoomDTO createRoom(Model model) {

//        RandomWrapper random = new RandomWrapperClass(3463734);
//        String generatedId = random.nextString(GlobalVariable.getLengthRandStringKey());
//        generatedId = generatedId;

        String generatedId = managerRoom.createNewRoom();
        return new RoomDTO(generatedId);
    }



    @CrossOrigin(origins = {"${settings.cors_origin}"})
    @PostMapping(value = "/get_elbow_footprint", produces = "application/json")
    public @ResponseBody String getElbowFootprint(@RequestBody String idRoom) {

//        List<PathMovingUniqueJSON> list = new ArrayList<>();
//        list.add(new RouteClass());



        //get list all footprints from timeStamp

        //write to json id, form, type
        //getToEndCalculateExistFrom

//        return "{\"boom_suka\": \"win_control\"}";

        return this.managerRoom.getElbowFootprintJson(idRoom);
    }




    @CrossOrigin(origins = {"${settings.cors_origin}"})
    @PostMapping(path = "/to_application_orders", produces = "application/json")
    public @ResponseBody StatusResponseDTO toApplicationOrders(@RequestBody String orders) throws Exception {
        this.managerRoom.toApplicationOrders(orders);

        return new StatusResponseDTO(true);
    }
}
