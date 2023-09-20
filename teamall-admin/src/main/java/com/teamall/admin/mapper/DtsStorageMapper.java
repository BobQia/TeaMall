package com.teamall.admin.mapper;

import com.teamall.admin.domain.TeaStorage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DengQiao
* @description 针对表【dts_storage(文件存储表)】的数据库操作Mapper
* @createDate 2023-09-03 14:20:10
* @Entity com.teamall.admin.domain.DtsStorage
*/
@Mapper
public interface DtsStorageMapper extends BaseMapper<TeaStorage> {

}




