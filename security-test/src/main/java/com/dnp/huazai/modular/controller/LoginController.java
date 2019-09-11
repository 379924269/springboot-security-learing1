package com.dnp.huazai.modular.controller;


import com.dnp.huazai.constant.VersionConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class LoginController {
    @RequestMapping(value = VersionConst.INTERFACE_VERSION + "/login/login", method = RequestMethod.POST)
    @ApiOperation(value = "测试接口")
    public Object findAll(@ApiParam(name = "username", value = "用户名称", required = true, defaultValue = "huazai") @RequestParam String username,
                          @ApiParam(name = "password", value = "密码", required = true, defaultValue = "123456") @RequestParam String password) {
        return true;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口")
    public void login(@ApiParam(name = "username", value = "用户名称", required = true, defaultValue = "admin") @RequestParam String username,
                      @ApiParam(name = "password", value = "密码", required = true, defaultValue = "123456") @RequestParam String password) {
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "退出接口")
    public void logout() {
    }

    @RequestMapping(value = "/login/loginSuccessHandle", method = RequestMethod.POST)
    @ApiOperation(value = "登录成功处理接口", hidden = true)
    public Object loginSuccessHandle() {
        return new JSONObject().put("result", "true").put("msg", "登录成功").toString();
    }

    @RequestMapping(value = "/login/loginFailureHandle", method = RequestMethod.POST)
    @ApiOperation(value = "登录失败处理接口", hidden = true)
    public Object loginFailureHandle() {
        return new JSONObject().put("result", "true").put("msg", "登录失败").toString();
    }

    @RequestMapping(value = "/login/accessDenied", method = RequestMethod.POST)
    @ApiOperation(value = "拒绝访问处理", hidden = true)
    public Object accessDenied() {
        return new JSONObject().put("result", "true").put("msg", "拒绝访问").toString();
    }

    @RequestMapping(value = "/login/loginPage", method = RequestMethod.GET)
    @ApiOperation(value = "登录页面（未登录处理）", hidden = true)
    public Object loginPage() {
        return new JSONObject().put("result", "true").put("msg", "请登录").toString();
    }

    @RequestMapping(value = "/login/logoutHandle", method = RequestMethod.GET)
    @ApiOperation(value = "退出处理接口", hidden = true)
    public Object logoutHandle() {
        return new JSONObject().put("result", "true").put("msg", "退出成功").toString();
    }
}