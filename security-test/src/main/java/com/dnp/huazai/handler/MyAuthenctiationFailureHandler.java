package com.dnp.huazai.handler;

import com.dnp.huazai.constant.ExceptionConst;
import org.json.JSONObject;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huazai
 * @description 登录失败处理
 * @date 2019/9/12
 */
@Component
public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(ExceptionConst.MY_HANDLE_EXCEPTION);
        e.printStackTrace();

        //这里定义了很多错误  我暂时没有处理
        if (e instanceof AccountExpiredException) {
            //账号过期
        } else if (e instanceof BadCredentialsException) {
            //密码错误
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
        } else if (e instanceof DisabledException) {
            //账号不可用
        } else if (e instanceof LockedException) {
            //账号锁定
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
        } else {
            //其他错误
        }


        if (e.getCause() instanceof LockedException) {
            response.getWriter().write(new JSONObject().put("code", ExceptionConst.MY_HANDLE_EXCEPTION)
                    .put("msg", e.getMessage()).toString());

        } else {
            response.getWriter().write(new JSONObject().put("code", ExceptionConst.MY_HANDLE_EXCEPTION)
                    .put("msg", "用户名或密码错误").toString());

        }
    }
}
