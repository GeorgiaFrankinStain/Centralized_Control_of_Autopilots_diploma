package com.alamutra.CCoAWeb.ControllerSpring;

import com.alamutra.CCoAWeb.Model.RoomApi;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ManagerRoomController {

    @GetMapping(value = "create_room", produces = "application/json")
    public RoomApi createRoom(Model model, HttpServletResponse response) {

        return new RoomApi("test_text_id");
    }
}
