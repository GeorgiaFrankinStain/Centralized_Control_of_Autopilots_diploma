package com.alamutra.CCoAWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CCoAWebApplication {

	@RequestMapping("/")
	public String home() {
		return "Hello world spring CCoA";
	}

	public static void main(String[] args) {
		SpringApplication.run(CCoAWebApplication.class, args);
	}

}
