package com.demo.cs.template.service.impl;

import com.demo.cs.template.mapper.ext.ExtTempDbUserMapper;
import com.demo.cs.template.mapper.ext.model.ExtTempDbUser;
import com.demo.cs.template.service.TempDbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempDbUserServiceImpl implements TempDbUserService {
    
    @Autowired
    private ExtTempDbUserMapper extTempDbUserMapper;
    
    @Autowired
    private TempDbUserService tempDbUserService;
    
    @Override
    public List<ExtTempDbUser> getRecordList(ExtTempDbUser model) {
        return extTempDbUserMapper.getRecordList(model);
    }
    
    @Override
    public void insertRecord(ExtTempDbUser model) {
        extTempDbUserMapper.insertRecord(model);
    }
    
    @Override
    public void deleteRecordById(Integer id){
        extTempDbUserMapper.deleteRecordById(id);
    }
    
    @Override
    public void insertBatch() {
        ExtTempDbUser model1=new ExtTempDbUser();
        model1.setUserName("测试1");
        extTempDbUserMapper.insertRecord(model1);
        ExtTempDbUser model2=new ExtTempDbUser();
        model2.setUserName("测试2");
        extTempDbUserMapper.insertRecord(model2);
    }
    
    @Override
    public void noTransInsertBatch() {
        ExtTempDbUser model1=new ExtTempDbUser();
        model1.setUserName("测试1-not trasn");
        extTempDbUserMapper.insertRecord(model1);
        ExtTempDbUser model2=new ExtTempDbUser();
        model2.setUserName("测试2-not trasn");
        extTempDbUserMapper.insertRecord(model2);
    }
    
    @Override
    public void insertNestTrasnAndNoTrans() {
        ExtTempDbUser model1=new ExtTempDbUser();
        model1.setUserName("测试1 for trans");
        extTempDbUserMapper.insertRecord(model1);
        ExtTempDbUser model2=new ExtTempDbUser();
        model2.setUserName("测试2 for trans");
        extTempDbUserMapper.insertRecord(model2);
        System.out.println("--------调用非事务管理方法--------");
        tempDbUserService.noTransInsertBatch();
    }
    
    
}
