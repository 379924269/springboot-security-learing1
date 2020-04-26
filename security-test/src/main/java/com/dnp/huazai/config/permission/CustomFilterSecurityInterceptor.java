package com.dnp.huazai.config.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * description: 权限拦截器
 * @author: 华仔
 * @date: 2020/4/2
 */
@Slf4j
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        showMethodLog((HttpServletRequest) request);

        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    @Override
    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void setSecurityMetadataSource(
            FilterInvocationSecurityMetadataSource smSource) {
        this.securityMetadataSource = smSource;
    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }


    /**
     * <p> 用来显示请求方法和请求参数的
     *
     * @param request HttpServletRequest
     */
    private void showMethodLog(HttpServletRequest request) {
        String requestUriString = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUriString.substring(contextPath.length());
        StringBuffer bfParams = new StringBuffer();
        Enumeration<String> paraNames = request.getParameterNames();
        String key;
        String value;
        while (paraNames.hasMoreElements()) {
            key = paraNames.nextElement();
            value = request.getParameter(key);
            bfParams.append(key).append("=").append(value).append("&");
        }
        if (bfParams.length() == 0) {
            bfParams.append(request.getQueryString());
        }

        String strMessage = String.format("[方法]:%s,[请求]:%s,[参数]:%s", request.getMethod(), url, bfParams.toString());
        log.info(strMessage);
    }
}
