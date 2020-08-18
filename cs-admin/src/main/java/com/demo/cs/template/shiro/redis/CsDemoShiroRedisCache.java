package com.demo.cs.template.shiro.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.Set;

public class CsDemoShiroRedisCache<K, V> implements Cache<K, V> {
    
    /**
     * key前缀
     */
    private String keyPrefix;
    
    /**
     * redis句柄
     */
    private StringRedisTemplate stringRedisTemplate;
    

    public CsDemoShiroRedisCache(StringRedisTemplate stringRedisTemplate, String keyPrefix){
        this.stringRedisTemplate=stringRedisTemplate;
        this.keyPrefix=keyPrefix;
    }
    
    @Override
    public V get(Object o) throws CacheException {
        return null;
    }
    
    @Override
    public Object put(Object o, Object o2) throws CacheException {
        return null;
    }
    
    @Override
    public Object remove(Object o) throws CacheException {
        return null;
    }
    
    @Override
    public void clear() throws CacheException {
        
    }
    
    @Override
    public int size() {
        return 0;
    }
    
    @Override
    public Set keys() {
        return null;
    }
    
    @Override
    public Collection values() {
        return null;
    }
    
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    
}
