package com.teamall.common.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//公共的返回的枚举对象:状态码之类的
@AllArgsConstructor
@ToString
@Getter
public enum RespBeanEnum {

    //通用错误
    SUCCESS("成功", 200),
    ERROR("服务器异常了", 500),

    //账号密码的错误
    LOGIN_ERROR("休想白嫖,请登录", 530),
    LOGIN_FAILED("账号或者密码不正确", 531),
    LOGIN_FAILEDP("密码不对",532),
    LOGIN_ERRORID("绑定异常,参数校验错误",533),
    LOGIN_OUT_TIME("请求超时,莫慌",5555),
    //秒杀库存不足的错误
    EMPTY_STOCK("库存不足,秒杀失败",666),
    //重复购买
    REPEATE_ERROR("还是给别人留一点吧",999),
    NO_ORDER_MESSAGE("没有这个订单",555),
    //验证码错误
    VERIFICARION_CODE_ERROR("验证码错误",123123),
    //路径错误
    PATH_ERROR("我怀疑你是脚本",777);
    private final String MESSAGE;
    private final Integer CODE;

}
