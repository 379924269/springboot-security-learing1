package com.dnp.huazai.authority;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dnp.huazai.modular.entity.Role;
import com.dnp.huazai.modular.entity.User;
import com.dnp.huazai.modular.service.IRoleService;
import com.dnp.huazai.modular.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = iUserService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, userName));
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        List<Role> roles = iRoleService.findByUsername(user.getUsername());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            for (Role role : roles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }
}
