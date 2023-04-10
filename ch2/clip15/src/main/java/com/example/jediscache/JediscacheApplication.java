package com.example.jediscache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JediscacheApplication implements ApplicationRunner {
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JediscacheApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		userRepository.save(User.builder().name("greg").email("greg@fastcampus.co.kr").build());
		userRepository.save(User.builder().name("tony").email("tony@fastcampus.co.kr").build());
		userRepository.save(User.builder().name("bob").email("bob@fastcampus.co.kr").build());
		userRepository.save(User.builder().name("ryan").email("ryan@fastcampus.co.kr").build());
	}
}
