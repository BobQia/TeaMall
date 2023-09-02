package com.teamall.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DengQiao
 * @date 2023-9-2 0002
 */
@RestController
@RequestMapping("/good")
public class GoodsController {
    @GetMapping("/goods")
    public String getGoods() {
        return "good";
    }
}
