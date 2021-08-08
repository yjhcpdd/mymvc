package com.demo.cs.template.service;

import com.demo.cs.template.mapper.ext.model.ExtTempDbUser;

import java.util.List;

public interface TempDbUserService {
    
    /**
     * 获取记录
     * @param model
     * @return
     */
    List<ExtTempDbUser> getRecordList(ExtTempDbUser model);
    
    /**
     * 新增记录
     * @param model
     */
    void insertRecord(ExtTempDbUser model);
    
    /**
     * 主键删除记录
     * @param id
     */
    void deleteRecordById(Integer id);
    
    /**
     * 批量插入测试
     */
    void insertBatch();
    
    /**
     * 批量插入测试(无事务)
     */
    void noTransInsertBatch();
    
    /**
     * 测试带事务service调用非事务service
     */
    void insertNestTrasnAndNoTrans();
}
