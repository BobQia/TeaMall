package com.teamall.admin.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teamall.admin.domain.TeaCategory;
import com.teamall.admin.dao.service.TeaCategoryService;
import com.teamall.admin.dao.mapper.TeaCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author DengQiao
* @description 针对表【tea_category(类目表)】的数据库操作Service实现
* @createDate 2023-10-16 23:08:43
*/
@Service
public class TeaCategoryServiceImpl extends ServiceImpl<TeaCategoryMapper, TeaCategory>
    implements TeaCategoryService{

}




