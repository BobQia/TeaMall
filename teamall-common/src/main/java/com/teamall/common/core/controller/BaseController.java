package com.teamall.common.core.controller;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.teamall.common.core.domain.enums.RespBeanEnum;
import com.teamall.common.core.domain.page.PageDomain;
import com.teamall.common.core.domain.page.TableDataInfo;
import com.teamall.common.core.domain.page.TableSupport;
import com.teamall.common.domain.vo.response.RespBean;
import com.teamall.common.utils.PageUtils;
import com.teamall.common.utils.ServletUtils;
import com.teamall.common.utils.SqlUtil;
import com.teamall.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 *
 * @author ruoyi
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtil.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageUtils.startPage();
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 清理分页的线程变量
     */
    protected void clearPage() {
        PageUtils.clearPage();
    }

    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected <T> RespBean<T> toRespBean(int rows) {
        return rows > 0 ? ok() : fail();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected <T>RespBean<T> toRespBean(boolean result) {
        return result ? ok() : fail();
    }

    /**
     * 返回成功
     */
    public <T>RespBean<T> ok() {
        return RespBean.ok();
    }

    /**
     * 返回失败消息
     */
    public <T>RespBean<T> fail() {
        return RespBean.fail();
    }


    /**
     * 返回成功数据
     */
    public static <T>RespBean<T> ok(T data) {
        return RespBean.ok(data);
    }

    /**
     * 返回失败消息
     */
    public <T>RespBean<T> fail(T data) {
        return RespBean.fail(data);
    }

    /**
     * 返回错误码消息
     */
    public <T>RespBean<T> fail(RespBeanEnum respBeanEnum) {
        return RespBean.fail(RespBeanEnum.ERROR);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

    ///**
    // * 获取用户缓存信息
    // */
    //public SysUser getSysUser() {
    //    return ShiroUtils.getSysUser();
    //}
    //
    ///**
    // * 设置用户缓存信息
    // */
    //public void setSysUser(SysUser user) {
    //    ShiroUtils.setSysUser(user);
    //}
    //
    ///**
    // * 获取登录用户id
    // */
    //public Long getUserId() {
    //    return getSysUser().getUserId();
    //}
    //
    ///**
    // * 获取登录用户名
    // */
    //public String getLoginName() {
    //    return getSysUser().getLoginName();
    //}
}
