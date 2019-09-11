package com.dnp.huazai.modular.mapper;

import com.dnp.huazai.modular.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findByUsername(@Param("username") String username);
}
