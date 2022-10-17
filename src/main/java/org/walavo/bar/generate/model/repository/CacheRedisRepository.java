package org.walavo.bar.generate.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.walavo.bar.generate.util.Util.randomString;

@Repository
public class CacheRedisRepository {

    private final ReactiveRedisOperations<String, String> redisOperations;
    private final ReactiveHashOperations<String, String, String> hashOperations;

    @Value("${spring.cache.ttl}")
    private Long tll;

    private Long ttlCache;

    public CacheRedisRepository ttl(Long ttl) {
        this.ttlCache = ttl;
        return this;
    }

    public CacheRedisRepository ttlNotExpire() {
        this.ttlCache = -1L;
        return this;
    }
    public CacheRedisRepository defaultTtl() {
        this.ttlCache = this.tll;
        return this;
    }

    @Autowired
    public CacheRedisRepository(ReactiveRedisOperations<String, String> redisOperations) {
        this.redisOperations = redisOperations;
        this.hashOperations = redisOperations.opsForHash();
    }

    public Mono<String> registerCache(String value) {
        String generatedString = randomString();
        return saveCache(generatedString, value);
    }

    public Mono<String> saveCache(String key, String value) {
        return redisOperations
                .opsForValue()
                .set(key, value, Duration.ofSeconds(ttlCache))
                .map(aLong -> key);
    }

    public Mono<String> getCache(String key) {
        return redisOperations.opsForValue().get(key).log("Redis Get");
    }
}
