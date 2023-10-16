package com.teamall.admin.dao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.teamall.admin.domain.TeaUser;

/**
* @author DengQiao
* @description 针对表【tea_user(用户表)】的数据库操作Service
* @createDate 2023-08-30 21:43:27
*/
public interface TeaUserService extends IService<TeaUser> {

    TeaUser getByUserName(String username);
}
