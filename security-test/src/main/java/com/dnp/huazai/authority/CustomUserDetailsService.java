package com.dnp.huazai.authority;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dnp.huazai.constant.TableFieldConst;
import com.dnp.huazai.modular.entity.Role;
import com.dnp.huazai.modular.entity.User;
import com.dnp.huazai.modular.service.IRoleService;
import com.dnp.huazai.modular.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IRoleService iRoleService;

    //    锁定30分钟
    private static final long LOCK_MAILS = 1000 * 60 * 60 * 30;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = iUserService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, userName));
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        lockedOrCancleLocked(user);

        List<Role> roles = iRoleService.findByUsername(user.getUsername());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            for (Role role : roles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
        }
//明天来改一下这里， 参考https://blog.csdn.net/I_am_Hutengfei/article/details/100561564 的userdetail
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    /**
     * description: 锁定用户或取消锁定用户
     *
     * @param user : 用户实体类信息
     */
    private void lockedOrCancleLocked(User user) {
        if (user.getLocked() == 1) {
            boolean unLocked = System.currentTimeMillis() - user.getLockedTime() > LOCK_MAILS;
            if (unLocked) {
                User updateUser = new User();
                user.setId(user.getId());
                user.setLocked(TableFieldConst.USER_UN_LOCKED);
                user.setLoginErrorCount(0);
                iUserService.updateById(updateUser);
            }
            throw new LockedException(new StringBuilder().append("账号").append(user.getUsername()).append("错误次数超出限制，已经被锁定").toString());
        }
    }
}
