package com.teamall.common.domain.vo.response;

import com.teamall.common.core.domain.enums.RespBeanEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DengQiao
 */ //公共的返回对象
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "返回对象",description = "返回对象信息")
public class RespBean<T> {

    private Integer code;
    private String message;
    private T obj;

    //登录成功返回的消息
    public static <T>RespBean<T> ok(){
        return new RespBean<>(RespBeanEnum.SUCCESS.getCODE(), RespBeanEnum.SUCCESS.getMESSAGE(),null);
    }

    public static <T>RespBean<T> ok(T obj){
        return new RespBean<>(RespBeanEnum.SUCCESS.getCODE(), RespBeanEnum.SUCCESS.getMESSAGE(),obj);
    }

    //登录失败返回的消息
    public static <T>RespBean<T> fail(RespBeanEnum pe){
        return new RespBean<>(pe.getCODE(), pe.getMESSAGE(), null);
    }

    public static <T>RespBean<T> fail(){
        return new RespBean<>(RespBeanEnum.ERROR.getCODE(), RespBeanEnum.ERROR.getMESSAGE(), null);
    }
    public static <T>RespBean<T> fail(T massage){
        return new RespBean<>(RespBeanEnum.ERROR.getCODE(), RespBeanEnum.ERROR.getMESSAGE(), massage);
    }

    public static <T>RespBean<T> fail(RespBeanEnum pe, T obj){
        return new RespBean<>(pe.getCODE(), pe.getMESSAGE(), obj);
    }


}
