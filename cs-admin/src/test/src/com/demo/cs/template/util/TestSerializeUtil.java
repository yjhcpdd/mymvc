package com.demo.cs.template.util;

import com.demo.cs.template.bean.TempUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 测试序列化工具类
 */
@RunWith(JUnit4.class)
public class TestSerializeUtil {
    
    /**
     * 测试序列化对象
     */
    @Test
    public void testSerialize() throws Exception {
        TempUser tempUser=new TempUser();
        tempUser.setUserName("张三");
        tempUser.setAge(18);
        //序列化前对象
        String oldStr=tempUser.toString();
        String serializeStr=SerializeUtil.serialize(tempUser);
        System.out.println("序列化前对象："+oldStr);
        System.out.println("序列化后字符串："+serializeStr);
        TempUser tempUserNew=SerializeUtil.deserialize(serializeStr);
        String newStr=tempUserNew.toString();
        System.out.println("反序列化后对象："+newStr);
        Assert.assertEquals(oldStr,newStr);
    }
    
    /**
     * 测试序列化对象（依赖commons-lang3包）
     */
    @Test
    public void testSerializeCommon() throws Exception {
        TempUser tempUser=new TempUser();
        tempUser.setUserName("张三");
        tempUser.setAge(18);
        //序列化前对象
        String oldStr=tempUser.toString();
        String serializeStr=SerializeUtil.serializeCommon(tempUser);
        System.out.println("序列化前对象："+oldStr);
        System.out.println("序列化后字符串："+serializeStr);
        TempUser tempUserNew=SerializeUtil.deserializeCommon(serializeStr);
        String newStr=tempUserNew.toString();
        System.out.println("反序列化后对象："+newStr);
        Assert.assertEquals(oldStr,newStr);
    }
    
    
}
