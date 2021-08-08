package com.demo.cs.template.controller;

import com.demo.cs.template.bean.TempUser;
import com.demo.cs.template.mapper.ext.model.ExtTempDbUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户列表
 * 测试页面请求路径：http://localhost:8080/cs-admin/tempUser/init?userName=张三&age=15
 */
@Slf4j
@Controller
@RequestMapping("tempUser")
public class TempUserController {
    
    /**
     * 初始化用户列表
     * @return
     */
    @RequestMapping("init")
    public ModelAndView init(TempUser form){
        ModelAndView modelAndView=new ModelAndView("template/user/user_list");
        modelAndView.addObject("model",form);
        return modelAndView;
    }
    
    /**
     * 执行业务操作
     * @return
     */
    @ResponseBody
    @RequestMapping("doBusniess")
    public Object doBusiness(TempUser form){
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        resultMap.put("message",form.toString());
        return resultMap;
    }
    
    /**
     * 文件上传
     */
    @ResponseBody
    @RequestMapping("doUploadFile")
    public Object doUploadFile(HttpServletRequest request,TempUser form) throws IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        //获取文件信息
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
        //文件名：multipartFile.getOriginalFilename()
        //文件是否存在：multipartFile.isEmpty()
        //文件字节大小：multipartFile.getSize()
        //获取文件字节数组：multipartFile.getBytes()
        //获取文件输入流：multipartFile.getInputStream()
        //拷贝文件：multipartFile.transferTo(...);
        
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        resultMap.put("message",form.toString()+multipartFile.getOriginalFilename());
        return resultMap;
    }
    
    /**
     * 文件下载
     * @param response
     */
    @ResponseBody
    @RequestMapping("doDownloadFile")
    public void doDownloadFile(HttpServletRequest request,HttpServletResponse response){
        File file=new File("D:\\Elasticsearch权威指南（中文版）.pdf");
        String fileName=file.getName();
        
        try(ServletOutputStream out=response.getOutputStream();
            FileInputStream input=new FileInputStream(file);
        ) {
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            //防止文件名中文乱码
            fileName=URLEncoder.encode(fileName,"UTF-8");
            String agent=request.getHeader("User-Agent");
            if(StringUtils.isNotEmpty(agent)&&agent.toUpperCase().indexOf("MSIE")<0){
                ///其他浏览器
                response.setHeader("Content-Disposition","attachment; filename*=utf-8'zh_cn'"+fileName);
            }else{
                ///IE浏览器(6,7,8,9)
                response.setHeader("Content-Disposition","attachment; filename="+fileName);
            }
            //全部拷贝
            //out.write(FileUtils.readFileToByteArray(file));
            //缓存拷贝
            IOUtils.copyLarge(input,out,new byte[1024]);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 接收json业务方法
     * @return
     */
    @ResponseBody
    @RequestMapping("doJsonBusiness")
    public Object doJsonBusiness(@RequestBody TempUser tempUser){
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        resultMap.put("message",tempUser.toString());
        return resultMap;
    }
    
    /**
     * 日志打印测试
     * @param tempUser
     * @return
     */
    @ResponseBody
    @RequestMapping("doLogPrint")
    public Object doLogPrint(){
        log.info("info test...");
        log.debug("debug test...");
        log.warn("warn test...");
        log.error("error test...");
        //测试记录异常
        try {
            int a=1/0;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        resultMap.put("message","");
        return resultMap;
    }
    
}
