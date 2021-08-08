package com.demo.cs.template.shiro.redis;

import com.demo.cs.template.util.SerializeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;

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
    public V get(K k) throws CacheException {
        if(k==null){
            return null;
        }
        try {
            String value=stringRedisTemplate.opsForValue().get(keyPrefix+k.toString());
            return SerializeUtil.deserialize(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public V put(K k, V v) throws CacheException {
         if(k==null || v==null){
             return null;
         }
         try {
             stringRedisTemplate.opsForValue().set(keyPrefix+k.toString(),SerializeUtil.serialize(v));
             return v;
         }catch (Exception e){
             e.printStackTrace();
            return null;
         }
    }
    
    @Override
    public V remove(K k) throws CacheException {
        try {
            String value=stringRedisTemplate.opsForValue().get(keyPrefix+k.toString());
            V res=SerializeUtil.deserialize(value);
            stringRedisTemplate.delete(k.toString());
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public void clear() throws CacheException {
        List<V> transList=new ArrayList<>();
        String prefix=keyPrefix+"*";
        Set<String> keySet=stringRedisTemplate.keys(prefix);
        if(CollectionUtils.isNotEmpty(keySet)){
            for(String k:keySet){
                stringRedisTemplate.delete(k);
            }
        }
    }
    
    @Override
    public int size() {
        String prefix=keyPrefix+"*";
        return stringRedisTemplate.opsForValue().size(prefix).intValue();
    }
    
    @Override
    public Set<K> keys() {
        String prefix=keyPrefix+"*";
        Set<String> keySet=stringRedisTemplate.keys(prefix);
        Set<K> transSet=new HashSet<>();
        if(CollectionUtils.isNotEmpty(keySet)){
            for(String k:keySet){
                transSet.add((K) k);
            }
        }
        return transSet;
    }
    
    @Override
    public Collection<V> values() {
        List<V> transList=new ArrayList<>();
        String prefix=keyPrefix+"*";
        Set<String> keySet=stringRedisTemplate.keys(prefix);
        if(CollectionUtils.isNotEmpty(keySet)){
            for(String k:keySet){
                transList.add(this.get((K) k));
            }
        }
        return  transList;
    }
}
