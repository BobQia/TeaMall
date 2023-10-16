package com.teamall.admin.controller;

import com.teamall.admin.domain.TeaUser;
import com.teamall.common.domain.response.RespBean;
import com.teamall.common.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DengQiao
 * @date 2023-10-9 0009
 */
@RequestMapping("user")
@RestController
@Api(tags = "用户")
public class UserController {


    @GetMapping("userinfo")
    @ApiOperation(("查询user"))
    public RespBean<TeaUser> getUserInfo() {
        String currentToken = TokenUtil.getCurrentToken();
    }
}
