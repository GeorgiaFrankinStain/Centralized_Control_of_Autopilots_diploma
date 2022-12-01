package com.alamutra.CCoAWeb.ControllerSpring;

import com.alamutra.CCoAWeb.Core.Controller.ManagerRoom;
import com.alamutra.CCoAWeb.Core.Controller.ManagerRoomClass;
import com.alamutra.CCoAWeb.Core.ModelLogic.FootprintSpaceTime.RouteClass;
import com.alamutra.CCoAWeb.Core.ModelLogic.GlobalVariable;
import com.alamutra.CCoAWeb.Core.ViewSettingRenderingTasks.PathMovingUniqueJSON;
import com.alamutra.CCoAWeb.Core.Wrappers.RandomWrapper;
import com.alamutra.CCoAWeb.Core.Wrappers.RandomWrapperClass;
import com.alamutra.CCoAWeb.Model.RoomApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ManagerRoomController {
    private static final Logger LOG = LoggerFactory.getLogger(ManagerRoomController.class);
    private ManagerRoom managerRoom = new ManagerRoomClass();

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping(value = "create_room", produces = "application/json")
    public RoomApi createRoom(Model model, HttpServletResponse response) {
        RandomWrapper random = new RandomWrapperClass(3463734);
        String generatedRandomString = random.nextString(GlobalVariable.getLengthRandStringKey());
        
        managerRoom.createNewRoom(generatedRandomString);
        return new RoomApi(generatedRandomString);
    }



    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping(value = "get_elbow_footprint", consumes = "application/json", produces = "application/json")
    public List<PathMovingUniqueJSON> getElbowFootprint(Model model, HttpServletResponse response) {

        List<PathMovingUniqueJSON> list = new ArrayList<>();
        list.add(new RouteClass());

        return list;
    }
}
