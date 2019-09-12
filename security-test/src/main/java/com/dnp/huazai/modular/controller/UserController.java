package com.dnp.huazai.modular.controller;


import com.dnp.huazai.constant.VersionConst;
import com.dnp.huazai.modular.entity.User;
import com.dnp.huazai.modular.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
@Api(value = "LoginController", description = "登录相关信息")
@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = VersionConst.INTERFACE_VERSION + "/user", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有用户信息")
    public List<User> findAll() {
        return userService.list();
    }
}
