package com.demo.cs.template.mapper.ext;

import com.demo.cs.template.mapper.ext.model.ExtTempDbUser;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface ExtTempDbUserMapper {
    /**
     * 查询记录
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
     * 删除记录
     * @param id
     */
    @Delete("delete from temp_db_user where id=#{id}")
    void deleteRecordById(int id);
}
