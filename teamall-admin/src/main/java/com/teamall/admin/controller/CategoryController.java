package com.teamall.admin.controller;

import com.teamall.admin.dao.service.TeaCategoryService;
import com.teamall.admin.domain.TeaCategory;
import com.teamall.common.core.controller.BaseController;
import com.teamall.common.domain.response.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DengQiao
 * @date 2023-10-16 0016
 */
@RestController
@RequestMapping("/admin/storage")
@Api(tags = "商品服务")
@Slf4j
public class CategoryController extends BaseController {
    @Autowired
    private TeaCategoryService teaCategoryService;
    @PostMapping("/create")
    @ApiOperation("上传商品")
    public RespBean<TeaCategory> create(@RequestBody TeaCategory category) {
        teaCategoryService.save(category);
        return ok(category);
    }

    @PostMapping("/update")
    @ApiOperation("修改商品")
    public RespBean<TeaCategory> update(@RequestBody TeaCategory category) {
        teaCategoryService.updateById(category);
        return ok(category);
    }
}
