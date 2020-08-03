package com.demo.cs.template.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Properties;
import java.util.Set;

@RunWith(JUnit4.class)
public class TestJavaProperties {
    
    /**
     * 输出java环境变量（spring中可在xml中获取对应的变量${xxx}，如${catalina.home}代表tomcat根目录）
     */
    @Test
    public void testProperties(){
        Properties properties = System.getProperties();
        Set<String> propertyNameSet=properties.stringPropertyNames();
        for (String propertyName : propertyNameSet) {
            System.out.println(propertyName + "=" + properties.getProperty(propertyName));
        }
    }
    
}
