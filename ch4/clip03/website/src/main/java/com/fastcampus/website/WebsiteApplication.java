package com.fastcampus.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class WebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
