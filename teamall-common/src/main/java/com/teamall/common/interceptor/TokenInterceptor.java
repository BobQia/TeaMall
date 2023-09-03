package com.teamall.common.interceptor;

import com.teamall.common.utils.sso.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author DengQiao
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token=request.getHeader("token");
        if(token==null||"".equals(token)) {
            throw new RuntimeException("请先登陆");
        }
        if(JwtUtils.isTokenExpired(token)) {
            throw new RuntimeException("请先登陆");
        }
        Claims claims=JwtUtils.getClaimsFromToken(token);
        List<String> list = (List<String>) claims.get("authorities");
        String[]authorities=list.toArray(new String[]{});
        UserDetails userDetails= User.builder()
                .username((String)claims.get("username"))
                .password("")
                .authorities(authorities)
                .build();
        //将UserDetails对象保存到一个可以和Spring-Security交互的对象中
        PreAuthenticatedAuthenticationToken authenticationToken=
                new PreAuthenticatedAuthenticationToken(
                        userDetails,userDetails.getPassword(),
                        AuthorityUtils.createAuthorityList(authorities));
        //将本次解析的用户详情和当前请求关联
        //关联之后才能在后面的控制器中获得用户详情
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        //将当前用户详情保存到Spring-Security上下文
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return true;
    }
}
