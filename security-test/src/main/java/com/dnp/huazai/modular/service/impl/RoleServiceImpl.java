package com.dnp.huazai.modular.service.impl;

import com.dnp.huazai.modular.entity.Role;
import com.dnp.huazai.modular.mapper.RoleMapper;
import com.dnp.huazai.modular.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findByUsername(String username) {
        return roleMapper.findByUsername(username);
    }
}
