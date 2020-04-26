package com.dnp.huazai.handler;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huazai
 * @description 登录成功处理
 * @date 2019/9/12
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    //    这里可以调用service接口  查询数据库数据返回给客户端

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//       登录成功要什么其他信息，后面再加
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new JSONObject().put("code", HttpStatus.OK.value()).put("msg", "登录成功").toString());
    }
}
