package com.alamutra.CCoAWeb.ControllerSpring;

import com.alamutra.CCoAWeb.Core.Controller.ManagerRoom;
import com.alamutra.CCoAWeb.Core.Controller.ManagerRoomClass;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.RouteClass;
import com.alamutra.CCoAWeb.Core.ModelLogic.GlobalVariable;
import com.alamutra.CCoAWeb.Core.ViewSettingRenderingTasks.PathMovingUniqueJSON;
import com.alamutra.CCoAWeb.Core.Wrappers.RandomWrapper;
import com.alamutra.CCoAWeb.Core.Wrappers.RandomWrapperClass;
import com.alamutra.CCoAWeb.Model.RoomApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ManagerRoomController {
    private static final Logger LOG = LoggerFactory.getLogger(ManagerRoomController.class);
    private ManagerRoom managerRoom = new ManagerRoomClass();

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/create_room", produces = "application/json")
    public RoomApi createRoom(Model model, HttpServletResponse response) {

        System.out.println("------------- TEST SOUT ------------: ");

        RandomWrapper random = new RandomWrapperClass(3463734);
        String generatedRandomString = random.nextString(GlobalVariable.getLengthRandStringKey());
        generatedRandomString = generatedRandomString + "__testMakesDeploy2222";
        
        managerRoom.createNewRoom(generatedRandomString);
        return new RoomApi(generatedRandomString);
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "get_elbow_footprint", consumes = "application/json", produces = "application/json")
    public List<PathMovingUniqueJSON> getElbowFootprint(Model model, HttpServletResponse response) {



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
    public @ResponseBody RoomApi toApplicationOrders(HttpServletRequest request,
                                                     HttpServletResponse response, Model model) {


        String idJson = request.getParameter("id");


        LOG.info("idJson: " + idJson);
        LOG.info("request: " + request);

//        System.out.println("------------- orders ------------: " + id);
//        ObjectMapper objectMapper = new ObjectMapper();
//        RoomApi prod = objectMapper.readValue(request, RoomApi.class);

        RandomWrapper random = new RandomWrapperClass(3463734);
        String generatedRandomString = random.nextString(GlobalVariable.getLengthRandStringKey());
        generatedRandomString = generatedRandomString + "__win_control";

        managerRoom.createNewRoom(generatedRandomString);
        return new RoomApi(idJson);
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
