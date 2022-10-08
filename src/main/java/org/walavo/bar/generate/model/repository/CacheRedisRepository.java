package org.walavo.bar.generate.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Random;

@Repository
public class CacheRedisRepository {

    private final ReactiveRedisOperations<String, String> redisOperations;
    private final ReactiveHashOperations<String, String, String> hashOperations;

    @Autowired
    public CacheRedisRepository(ReactiveRedisOperations<String, String> redisOperations) {
        this.redisOperations = redisOperations;
        this.hashOperations = redisOperations.opsForHash();
    }
    public Mono<String> registerCache(String value) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return redisOperations
                .opsForValue()
                .set(generatedString, value, Duration.ofSeconds(60))
                .map(aLong -> generatedString);
    }

    public Mono<String> getCache(String key) {
        return redisOperations.opsForValue().get(key).log("Redis Get");
    }
}
