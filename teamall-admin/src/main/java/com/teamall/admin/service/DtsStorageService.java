package com.teamall.admin.service;

import com.teamall.admin.domain.TeaStorage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author DengQiao
* @description 针对表【dts_storage(文件存储表)】的数据库操作Service
* @createDate 2023-09-03 14:20:10
*/
public interface DtsStorageService extends IService<TeaStorage> {

    TeaStorage findByKey(String key);
}
