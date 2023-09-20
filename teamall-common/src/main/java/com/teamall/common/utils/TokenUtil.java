package com.teamall.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DengQiao
 * @date 2023-9-3 0003
 */
public class TokenUtil {
    //获取当前登录用户的token  可以通过这个
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    //拿到token进行解析，拿到当前登录用户信息 返回即可
    public static String getToken(){
        return getHttpServletRequest().getHeader("token");
    }

}
