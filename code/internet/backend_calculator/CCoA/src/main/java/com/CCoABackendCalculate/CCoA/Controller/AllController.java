package com.CCoABackendCalculate.CCoA.Controller;

import com.CCoABackendCalculate.CCoA.Model.RoomDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AllController {

    @PostMapping
    public ResponseEntity registration(@RequestBody RoomDTO roomDTO) {
        String res = "lose :(";

        if (roomDTO.getId().equals("13")) {
            res = "win_control2";
        }
        return ResponseEntity.ok(res);
    }
}
