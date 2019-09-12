package com.dnp.huazai.Listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dnp.huazai.constant.TableFieldConst;
import com.dnp.huazai.modular.entity.User;
import com.dnp.huazai.modular.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @author huazai
 * @description 登录成功监听
 * @date 2019/9/11
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private IUserService iUserService;

//    https://www.jb51.net/article/108091.htm
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        org.springframework.security.core.userdetails.User authenticationUser = (org.springframework.security.core.userdetails.User) authenticationSuccessEvent.getAuthentication().getPrincipal();
        User user = new User();
        user.setLocked(TableFieldConst.USER_UN_LOCKED);
        user.setLockedTime(0L);
        user.setLoginErrorCount(0);
        iUserService.update(user, new QueryWrapper<User>().lambda().eq(User::getUsername, authenticationUser.getUsername()));
    }
}
