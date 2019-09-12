package com.dnp.huazai.Listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dnp.huazai.constant.TableFieldConst;
import com.dnp.huazai.modular.entity.User;
import com.dnp.huazai.modular.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * @author huazai
 * @description 登录失败监听
 * @date 2019/9/11
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private static final int LIMIT_LOGIN_ERROR_COUNT = 5;

    @Autowired
    private IUserService iUserService;
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        String userName = authenticationFailureBadCredentialsEvent.getAuthentication().getPrincipal().toString();
        User user = iUserService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, userName));
        if (user != null) {
            int loginErrorCount = user.getLoginErrorCount();
            loginErrorCount++;

            User updateUser = new User();
            updateUser.setId(user.getId());
            if (loginErrorCount >= LIMIT_LOGIN_ERROR_COUNT) {
                updateUser.setLocked(TableFieldConst.USER_LOCKED);
                updateUser.setLockedTime(System.currentTimeMillis());
            } else {
                updateUser.setLoginErrorCount(loginErrorCount);
            }
            iUserService.updateById(updateUser);
        }
    }
}
