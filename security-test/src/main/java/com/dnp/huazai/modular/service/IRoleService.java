package com.dnp.huazai.modular.service;

import com.dnp.huazai.modular.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
public interface IRoleService extends IService<Role> {
    List<Role> findByUsername(String username);
}
