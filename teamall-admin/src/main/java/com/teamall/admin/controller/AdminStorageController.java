package com.teamall.admin.controller;

import com.teamall.admin.service.storage.StorageService;
import com.teamall.common.core.controller.BaseController;
import com.teamall.common.domain.vo.response.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DengQiao
 * @date 2023-9-3 0003
 */
@RestController
@RequestMapping("/admin/storage")
@Api(tags = "对象储存服务")
@Slf4j
public class AdminStorageController extends BaseController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/create")
    @ApiOperation("文件上传")
    public RespBean<String> create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String url = storageService.store(file.getInputStream(),
                file.getSize(),
                file.getContentType(),
                originalFilename);
        return ok(url);
    }

}
