package com.teamall.admin.service;

import com.teamall.admin.domain.TeaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TeaUserService teaUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.基于用户名从数据库查询用户信息
        TeaUser teaUser=teaUserService.getByUserName(username);
        if (Objects.isNull(teaUser) &&!teaUser.getUsername().equals(username))//假设这是从数据库查询的信息
        {
            throw new UsernameNotFoundException("user not exists");
        }
        //2.将用户信息封装到UserDetails对象中并返回
        //假设这个密码是从数据库查询出来的
        String encodedPwd = passwordEncoder.encode(teaUser.getPassword());
        //假设这个权限信息也是从数据库查询到的
        //List<String> permissions=userMapper.selectUserPermissions(username);//查数据库
        //假如分配权限的方式是角色,编写字符串时用"ROLE_"做前缀
        List<GrantedAuthority> grantedAuthorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList("sys:res:retrieve,sys:res:create");
        //这个user是SpringSecurity提供的UserDetails接口的实现,用于封装用户信息
        //后续我们也可以基于需要自己构建UserDetails接口的实现
        User user = new User(username, encodedPwd, grantedAuthorities);
        return user;//这里的返回值会交给springsecurity去校验
    }
}
