package com.CCoABackendCalculate.CCoA.ControllerSpring;

import com.CCoABackendCalculate.CCoA.Core.Controller.ManagerRoom;
import com.CCoABackendCalculate.CCoA.Core.Controller.ManagerRoomClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.FootprintSpaceTime.RouteClass;
import com.CCoABackendCalculate.CCoA.Core.ModelLogic.GlobalVariable;
import com.CCoABackendCalculate.CCoA.Core.ViewSettingRenderingTasks.PathMovingUniqueJSON;
import com.CCoABackendCalculate.CCoA.Core.Wrappers.RandomWrapper;
import com.CCoABackendCalculate.CCoA.Core.Wrappers.RandomWrapperClass;
import com.CCoABackendCalculate.CCoA.SpringModel.RoomDTO;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/create_room", produces = "application/json")
    public RoomDTO createRoom(Model model) {

        System.out.println("------------- TEST SOUT ------------: ");

        RandomWrapper random = new RandomWrapperClass(3463734);
        String generatedRandomString = random.nextString(GlobalVariable.getLengthRandStringKey());
        generatedRandomString = generatedRandomString + "__testMakesDeploy2222";
        
        managerRoom.createNewRoom(generatedRandomString);
        return new RoomDTO(generatedRandomString);
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "get_elbow_footprint", consumes = "application/json", produces = "application/json")
    public List<PathMovingUniqueJSON> getElbowFootprint(Model model) {



        List<PathMovingUniqueJSON> list = new ArrayList<>();
        list.add(new RouteClass());

        return list;
    }



//    @CrossOrigin(origins = "http://localhost:3000")
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PostMapping(value = "to_application_orders", consumes = "application/json", produces = "application/json")
//    @RequestMapping(
//            path = "/to_application_orders",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//            produces = {
//                    MediaType.APPLICATION_ATOM_XML_VALUE,
//                    MediaType.APPLICATION_JSON_VALUE
//            })
//    @RequestMapping(value = "to_application_orders",
//            method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public @ResponseBody RoomApi toApplicationOrders(@RequestParam RoomApi orders) {
    @PostMapping(path = "/to_application_orders", consumes = "application/json", produces = "application/json")
//            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public @ResponseBody RoomApi toApplicationOrders(@RequestParam RoomApi id) {
    public @ResponseBody RoomDTO toApplicationOrders(Model model) {



//        System.out.println("------------- orders ------------: " + id);
//        ObjectMapper objectMapper = new ObjectMapper();
//        RoomApi prod = objectMapper.readValue(request, RoomApi.class);

        RandomWrapper random = new RandomWrapperClass(3463734);
        String generatedRandomString = random.nextString(GlobalVariable.getLengthRandStringKey());
        generatedRandomString = generatedRandomString + "__win_control";

        managerRoom.createNewRoom(generatedRandomString);
        return new RoomDTO("idJson");
    }

//    @CrossOrigin(origins = "http://localhost:3000")
//    @PostMapping(value = "to_application_orders", consumes = "application/json", produces = "application/json")
//    public PathMovingUniqueJSON toApplicationOrders(@RequestBody List<OrderDTO> orders, Model model, HttpServletResponse response) {
//
//        List<PathMovingUniqueJSON> list = new ArrayList<>();
//        list.add(new RouteClass());
//
////        return list;
//
//        return new RouteClass();
//    }
}
