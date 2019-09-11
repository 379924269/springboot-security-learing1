package com.dnp.huazai.modular.service.impl;

import com.dnp.huazai.modular.entity.Resource;
import com.dnp.huazai.modular.mapper.ResourceMapper;
import com.dnp.huazai.modular.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
