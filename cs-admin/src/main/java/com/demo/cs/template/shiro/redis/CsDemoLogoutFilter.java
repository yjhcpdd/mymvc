package com.demo.cs.template.shiro.redis;

import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义退出拦截器
 */
public class CsDemoLogoutFilter extends LogoutFilter {
    
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //执行退出前的操作
        return super.preHandle(request, response);
    }
}
