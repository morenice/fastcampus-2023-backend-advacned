package com.fastcampus.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowApplication.class, args);
	}

}
