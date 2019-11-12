package com.dnp.huazai.modular.mapper;

import com.dnp.huazai.modular.entity.RoleResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dnp.huazai.vo.RoleResourceVo;

import java.util.List;

/**
 * <p>
 * 角色资源 Mapper 接口
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {
    List<RoleResourceVo> list();
}
