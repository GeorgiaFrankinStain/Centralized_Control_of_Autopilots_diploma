package com.alamutra.CCoASpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) //FIXME
public class CCoASpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CCoASpringApplication.class, args);
	}

}
