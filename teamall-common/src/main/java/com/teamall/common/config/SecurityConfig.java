package com.teamall.common.config;

import com.teamall.common.utils.sso.JwtUtils;
import com.teamall.common.utils.sso.WebUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DengQiao
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.关闭跨域攻击
        http.cors().and().csrf().disable();
        //2.配置登录url(登录表单使用哪个页面)
        http.formLogin()
         .successHandler(authenticationSuccessHandler())
         .failureHandler(authenticationFailureHandler());
        //设置需要认证与拒绝访问的异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint());
        //3.放行登录url(不需要认证就可以访问)
        http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/webjars/**").permitAll()
                .anyRequest().authenticated();//除了以上资源必须认证才可访问
        //所有请求必须认证通过
        //http.authorizeRequests()
        //        //下边的路径放行
        //        .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
        //                "/swagger-resources", "/swagger-resources/configuration/security",
        //                "/swagger-ui.html", "/webjars/**").permitAll()
        //        .anyRequest().authenticated();
    }
    //认证成功处理器
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return (httpServletRequest, httpServletResponse,authentication)-> {
            User principal = (User)authentication.getPrincipal();
            Map<String,Object> map=new HashMap<>();
            map.put("code",200);
            map.put("message","Login ok");
            Map<String,Object> jwtMap=new HashMap<>();
            jwtMap.put("username", principal.getUsername());
            List<String> authorities = new ArrayList<>();
            principal.getAuthorities().forEach((authority)-> {
                    authorities.add(authority.getAuthority());
            });
            jwtMap.put("authorities",authorities);
            String token= JwtUtils.generatorToken(jwtMap);
            map.put("token", token);
            WebUtils.writeJsonToClient(httpServletResponse,map);
        };
    }
    //认证失败处理器
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return (httpServletRequest, httpServletResponse, e) -> {
            Map<String,Object> map=new HashMap<>();
            map.put("code",500);
            map.put("message","username or password error");
            WebUtils.writeJsonToClient(httpServletResponse,map);
        };
    }
    //没有认证时执行DefaultAuthenticationEntryPoint对象
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return (httpServletRequest, httpServletResponse, e)->{
                Map<String,Object> map=new HashMap<>();
                map.put("code",401);//SC_UNAUTHORIZED 的值为401
                map.put("message","请先登录再访问");
                WebUtils.writeJsonToClient(httpServletResponse,map);
        };
    }
}
