package com.teamall.admin.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teamall.admin.dao.mapper.TeaUserMapper;
import com.teamall.admin.dao.service.TeaUserService;
import com.teamall.admin.domain.TeaUser;
import org.springframework.stereotype.Service;

/**
* @author DengQiao
* @description 针对表【tea_user(用户表)】的数据库操作Service实现
* @createDate 2023-08-30 21:43:27
*/
@Service
public class TeaUserServiceImpl extends ServiceImpl<TeaUserMapper, TeaUser>
    implements TeaUserService {

    @Override
    public TeaUser getByUserName(String username) {
        return lambdaQuery().eq(TeaUser::getUsername,username).one();
    }
}




