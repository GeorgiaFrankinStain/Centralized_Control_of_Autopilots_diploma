package com.alamutra.CCoASpring.conrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerMessageController {
    @GetMapping("/messages")
    public String getHelloWorld() {
        return "Hello world docker";
    }
}
