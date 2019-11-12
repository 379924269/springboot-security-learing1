package com.dnp.huazai.modular.controller;


import com.dnp.huazai.constant.KaptchaConst;
import com.dnp.huazai.util.ServletUtil;
import com.dnp.huazai.util.VerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 登录前端控制器
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
@Api(value = "LoginController", description = "登录相关信息")
@RestController
@RequestMapping("")
@Slf4j
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口")
    public void login(@ApiParam(name = "username", value = "用户名称", required = true, defaultValue = "admin") @RequestParam String username,
                      @ApiParam(name = "password", value = "密码", required = true, defaultValue = "123456") @RequestParam String password) {
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "未登录接口（或叫登录界面跳转接口）", hidden = true)
    public Object login() {
        return new JSONObject().put("result", "true").put("msg", "请登录").toString();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "退出接口")
    public void logout() {
    }

    @RequestMapping(value = "/login/accessDenied", method = RequestMethod.POST)
    @ApiOperation(value = "拒绝访问处理", hidden = true)
    public Object accessDenied() {
        ServletUtil.setStatus(HttpStatus.FORBIDDEN.value());
        return new JSONObject().put("code", HttpStatus.FORBIDDEN.value()).put("msg", "无权限，拒绝访问").toString();
    }

    @RequestMapping(value = "/login/logoutHandle", method = RequestMethod.GET)
    @ApiOperation(value = "退出处理接口", hidden = true)
    public Object logoutHandle() {
        return new JSONObject().put("result", "true").put("msg", "退出成功").toString();
    }

    @RequestMapping(value = "/login/outLine", method = RequestMethod.GET)
    @ApiOperation(value = "挤下线处理", hidden = true)
    public Object outLine() {
        return new JSONObject().put("result", "true").put("msg", "用户被挤下线").toString();
    }

    @RequestMapping(value = "/login/invalidSession", method = RequestMethod.GET)
    @ApiOperation(value = "session无效处理", hidden = true)
    public Object invalidSession() {
        return new JSONObject().put("result", true).put("msg", "session无效处理").toString();
    }

    @RequestMapping(value = "/login/getVerifyCode", method = RequestMethod.GET)
    @ApiOperation(value = "获取验证码")
    public void getVerifyCode(HttpServletResponse resp, HttpServletRequest req) {
        VerifyCodeUtil.outputVerifyImage(resp, req, VerifyCodeUtil.getVerifyCode(), VerifyCodeUtil.getConfig());
    }

    @RequestMapping(value = "/login/checkVerifyCode", method = RequestMethod.POST)
    @ApiOperation(value = "checkVerifyCode")
    public Object checkVerficationCode(HttpServletRequest req,
                                       @ApiParam(name = "verifycode", value = "验证码", required = true) @RequestParam String verifycode) {
        Object sessionKeyValue = req.getSession().getAttribute(KaptchaConst.KAPTCHA_SESSION_KEY);
        Object createTime = req.getSession().getAttribute(KaptchaConst.KAPTCHA_SESSION_DATE);
        if (null == sessionKeyValue) {
            return new JSONObject().put("result", false).put("msg", "验证码已经失效，请重新输入").toString();
        }

        String sessionKeyValueStr = sessionKeyValue.toString();
        if (!sessionKeyValueStr.equalsIgnoreCase(verifycode)) {
            return new JSONObject().put("result", false).put("msg", "验证码已错误").toString();
        } else if (System.currentTimeMillis() - Long.valueOf(createTime.toString()) > 1000 * 60 * 5) {
            return new JSONObject().put("result", false).put("msg", "验证码过期请重新获取").toString();
        } else {
            return new JSONObject().put("result", true).put("msg", "验证成功").toString();
        }
    }

}
