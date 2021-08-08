package com.demo.cs.template.bean;

import lombok.Data;
import lombok.ToString;

import java.beans.Transient;
import java.io.Serializable;

/**
 * 用户
 */
@Data
@ToString
public class TempUser implements Serializable {
    
    private String userName;
    
    private Integer age;
    
    
    
}
