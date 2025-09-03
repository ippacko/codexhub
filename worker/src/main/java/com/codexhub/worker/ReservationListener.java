package com.codexhub.worker;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReservationListener {
    private static final Logger log = LoggerFactory.getLogger(ReservationListener.class);
    private final RedisTemplate<String, String> redis;

    public ReservationListener(RedisTemplate<String, String> redis) {
        this.redis = redis;
    }

    @PostConstruct
    public void init() {
        log.info("Worker online. Listening for fake events...");
        // Placeholder hook. In a real app you would subscribe to a stream or channel.
    }
}
