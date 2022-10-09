package org.walavo.bar.generate.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import org.walavo.bar.generate.util.Util;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Random;

@Repository
public class CacheRedisRepository {

    private final ReactiveRedisOperations<String, String> redisOperations;
    private final ReactiveHashOperations<String, String, String> hashOperations;

    @Value("${spring.cache.ttl}")
    private Long tll;

    @Autowired
    public CacheRedisRepository(ReactiveRedisOperations<String, String> redisOperations) {
        this.redisOperations = redisOperations;
        this.hashOperations = redisOperations.opsForHash();
    }

    public Mono<String> registerCache(String value) {
        String generatedString = Util.generateValue();
        return redisOperations
                .opsForValue()
                .set(generatedString, value, Duration.ofSeconds(tll))
                .map(aLong -> generatedString);
    }

    public Mono<String> getCache(String key) {
        return redisOperations.opsForValue().get(key).log("Redis Get");
    }
}
