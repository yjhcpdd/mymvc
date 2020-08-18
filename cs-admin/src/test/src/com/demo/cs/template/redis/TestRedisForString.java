package com.demo.cs.template.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试字符串redis连接
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring.xml","classpath:conf/spring-mybatis.xml","classpath:conf/spring-redis.xml"})
public class TestRedisForString {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Test
    public void testSetGet(){
        //字符串句柄写法
        stringRedisTemplate.opsForValue().set("k1", "v1");
        stringRedisTemplate.opsForValue().get("k1");
    
        //调用原生jedis回调写法
        stringRedisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.flushAll();
            return null;
        });
    }
    
}
