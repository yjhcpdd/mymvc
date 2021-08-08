package com.demo.cs.template.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring.xml","classpath:conf/spring-mybatis.xml"})
public class TestTempDbUserService {
    
    @Autowired
    private TempDbUserService tempDbUserService;
    
    /**
     * 测试带事务批量插入
     */
    @Test
    public void testInsertBatch(){
        tempDbUserService.insertBatch();
    }
    
    /**
     * 测试无事务批量插入
     */
    @Test
    public void testNoTransInsertBatch(){
        tempDbUserService.noTransInsertBatch();
    }
    
    /**
     * 测试调用多个带事务的方法
     */
    @Test
    public void testManyTransBatch(){
        System.out.println("----------第一个事务调用----------");
        tempDbUserService.insertBatch();
        System.out.println("----------第二个事务调用----------");
        tempDbUserService.insertBatch();
    }
    
    /**
     * 测试带事务service调用非事务service
     */
    @Test
    public void testNestTrasnAndNoTrans(){
        tempDbUserService.insertNestTrasnAndNoTrans();
    }
}
