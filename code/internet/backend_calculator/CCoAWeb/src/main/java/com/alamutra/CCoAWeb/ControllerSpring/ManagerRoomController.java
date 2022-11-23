package com.alamutra.CCoAWeb.ControllerSpring;

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

@RestController
public class ManagerRoomController {
    private static final Logger LOG = LoggerFactory.getLogger(ManagerRoomController.class);

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping(value = "create_room", produces = "application/json")
    public RoomApi createRoom(Model model, HttpServletResponse response) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        LOG.info("GFS request in createRoom" + dtf.format(now));
        System.out.println(dtf.format(now));
        return new RoomApi("test_text_id" + dtf.format(now));
    }
}
