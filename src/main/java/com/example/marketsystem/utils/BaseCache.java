package com.example.marketsystem.utils;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * gnava缓存
 */
@Component
public class BaseCache<K,V> {
    private Cache<K,V> tenMinuteCache = CacheBuilder.newBuilder()
            //缓存大小
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发量
            .concurrencyLevel(50)
            //缓存过期时间  写入10分钟后过期
            .expireAfterWrite(600, TimeUnit.SECONDS)
            //缓存命中率
            .recordStats()
            .build();

    public Cache<K,V> getTenMinuteCache() {
        return tenMinuteCache;
    }

    public void setTenMinuteCache(Cache<K,V> tenMinuteCache) {
        this.tenMinuteCache = tenMinuteCache;
    }
}
