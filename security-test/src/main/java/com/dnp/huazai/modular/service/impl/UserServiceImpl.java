package com.dnp.huazai.modular.service.impl;

import com.dnp.huazai.modular.entity.User;
import com.dnp.huazai.modular.mapper.UserMapper;
import com.dnp.huazai.modular.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author huazai
 * @since 2019-09-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
