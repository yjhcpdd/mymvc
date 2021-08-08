package com.demo.cs.template.controller.system;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录controller
 */
@Controller
@RequestMapping("/tmpLogin")
public class TempLoginController {
    
    /**
     * 初始化登录页面
     * @return
     */
    @RequestMapping("initLogin")
    public ModelAndView initLogin(){
        ModelAndView modelAndView=new ModelAndView("system/login");
        return modelAndView;
    }
    
    /**
     * 登录
     * @return
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request){
        ////错误信息页面
        ModelAndView errorModelAndView=new ModelAndView("system/login");
        ////获取参数
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        ////校验参数
        if(StringUtils.isBlank(userName)){
            errorModelAndView.addObject("message","请输入用户名");
            return errorModelAndView;
        }
        if(StringUtils.isBlank(password)){
            errorModelAndView.addObject("message","请输入密码");
            return errorModelAndView;
        }
        ////登录
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
            errorModelAndView.addObject("message","用户名密码不正确");
            return errorModelAndView;
        }
        ////清除缓存权限
        
        //跳转页面
        String contextPath=request.getContextPath();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setView(new RedirectView(contextPath+"/"));
        return modelAndView;
    }
    
    /**
     * 登录后首页
     * @return
     */
    @RequestMapping("init")
    public ModelAndView init(){
        ModelAndView modelAndView=new ModelAndView("/system/desktop");
        
        return modelAndView;
    }
    
    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request){
        //权限主体登出系统
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        //清空缓存
        
        //跳转页面
        String contextPath=request.getContextPath();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setView(new RedirectView(contextPath+"/"));
        return modelAndView;
    }
    
}
