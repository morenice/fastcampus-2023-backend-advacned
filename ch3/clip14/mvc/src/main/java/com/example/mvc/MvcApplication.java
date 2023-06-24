package com.example.mvc;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class MvcApplication implements ApplicationListener<ApplicationReadyEvent> {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }

    @GetMapping("/health")
    public Map<String, String> heatlh() {
        return Map.of("health", "ok");
    }

    @GetMapping("/users/1/cache")
    public Map<String, String> getCachedUser() {
        var name = redisTemplate.opsForValue().get("users:1:name");
        var email = redisTemplate.opsForValue().get("users:1:email");
        return Map.of("name", name == null ? "" : name, "email", email == null ? "" : email);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        redisTemplate.opsForValue().set("users:1:name", "greg");
        redisTemplate.opsForValue().set("users:1:email", "greg@fastcampus.co.kr");

        Optional<User> user = userRepository.findById(1L);
        if (user.isEmpty()) {
            userRepository.save(User.builder().name("greg").email("greg@fastcampus.co.kr").build());
        }
    }
}

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "users")
class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

interface UserRepository extends JpaRepository<User, Long> {
}

