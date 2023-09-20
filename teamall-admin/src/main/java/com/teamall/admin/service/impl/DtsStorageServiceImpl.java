package com.teamall.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teamall.admin.domain.TeaStorage;
import com.teamall.admin.service.DtsStorageService;
import com.teamall.admin.mapper.DtsStorageMapper;
import org.springframework.stereotype.Service;

/**
* @author DengQiao
* @description 针对表【dts_storage(文件存储表)】的数据库操作Service实现
* @createDate 2023-09-03 14:20:10
*/
@Service
public class DtsStorageServiceImpl extends ServiceImpl<DtsStorageMapper, TeaStorage>
    implements DtsStorageService{

    @Override
    public TeaStorage findByKey(String key) {
        return lambdaQuery().eq(TeaStorage::getFileKey,key).one();
    }
}




