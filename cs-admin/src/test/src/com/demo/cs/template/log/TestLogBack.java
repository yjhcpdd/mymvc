package com.demo.cs.template.log;

import com.demo.cs.template.mapper.ext.model.ExtTempDbUser;
import com.demo.cs.template.service.TempDbUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试logBack
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring.xml","classpath:conf/spring-mybatis.xml"})
public class TestLogBack {
    
    @Autowired
    private TempDbUserService tempDbUserService;
    
    @Test
    public void testLog(){
        log.error("testError.....");
        tempDbUserService.getRecordList(new ExtTempDbUser());
    }
    
}
