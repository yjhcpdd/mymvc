package com.demo.cs.template.shiro.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义redis安全缓存管理器
 */
public class CsDemoSecurityCacheManager implements CacheManager {
    
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * 缓存map映射（用来存储不同命名空间内的缓存器）
     */
    private static final Map<String,Cache> CACHE_MAP=new ConcurrentHashMap<String,Cache>();
    
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        System.out.println("===========："+name);
        Cache cache=null;
        if(StringUtils.isNotBlank(name)){
            cache=CACHE_MAP.get(name);
            if(cache==null){
                cache=new CsDemoShiroRedisCache(stringRedisTemplate,name+"_");
                CACHE_MAP.put(name, cache);
            }
        }
        return cache;
    }
    
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
}
