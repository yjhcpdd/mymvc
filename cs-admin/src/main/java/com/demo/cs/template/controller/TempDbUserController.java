package com.demo.cs.template.controller;

import com.demo.cs.template.mapper.ext.model.ExtTempDbUser;
import com.demo.cs.template.service.TempDbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("tempDbUser")
public class TempDbUserController {

    @Autowired
    private TempDbUserService tempDbUserService;
    
    /**
     * 查询记录
     * @param form
     * @return
     */
    @ResponseBody
    @RequestMapping("getRecordList")
    public List<ExtTempDbUser> getRecordList(ExtTempDbUser form){
        return tempDbUserService.getRecordList(form);
    }
    
    /**
     * 插入记录
     * @return
     */
    @ResponseBody
    @RequestMapping("insertRecord")
    public Map insertRecord(ExtTempDbUser form){
        tempDbUserService.insertRecord(form);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        return resultMap;
    }
    
    /**
     * 删除记录
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteRecordById")
    public Map deleteRecordById(Integer id){
        tempDbUserService.deleteRecordById(id);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        return resultMap;
    }
    
}
