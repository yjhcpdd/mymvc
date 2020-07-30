package com.demo.cs.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户列表
 */
@Controller
@RequestMapping("tempUser")
public class TempUserController {
    
    /**
     * 初始化用户列表
     * @return
     */
    @RequestMapping("init")
    public ModelAndView init(){
        ModelAndView modelAndView=new ModelAndView("template/user/user_list");
    
        modelAndView.addObject("msg","后台消息");
        
        return modelAndView;
    }
    
    /**
     * 执行业务操作
     * @return
     */
    @ResponseBody
    @RequestMapping("doBusniess")
    public Object doBusiness(){
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        resultMap.put("message","执行成功");
        return resultMap;
    }
    
    /**
     * 文件上传
     */
    @RequestMapping("doFileUpload")
    public void doFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取文件名
        System.out.println(file.getOriginalFilename());;
        //获取文件流
        InputStream inputStream=null;
        OutputStream outputStream=null;
        try {
            inputStream=new ByteArrayInputStream(file.getBytes());
            outputStream=new FileOutputStream(new File("D:\\"+file.getOriginalFilename()));
            byte[] tmp=new byte[1024];
            int len=0;
            while ((len=inputStream.read(tmp))!=-1){
                outputStream.write(tmp,0,len);
                outputStream.flush();
            }
            response.getWriter().write("<script>alert('上传成功')</script>");
        }catch (Exception e){
            
        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
            if(outputStream!=null){
                outputStream.close();
            }
        }
    }
    
}
