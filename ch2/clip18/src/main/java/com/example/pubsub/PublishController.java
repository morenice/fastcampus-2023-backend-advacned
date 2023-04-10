package com.example.pubsub;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublishController {
    private final RedisTemplate<String, String> redisTemplate;

    @PostMapping("/events/users/deregister")
    void publishUserDeregisterEvent() {
        redisTemplate.convertAndSend("users:unregister", "500");
    }
}
