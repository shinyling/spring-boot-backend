package com.shiny.backend.jwt.service.impl;

import com.shiny.backend.common.entity.User;
import com.shiny.backend.jwt.entity.JwtUserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author DELL
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 此处为假数据，真实情况请查数据库
         */
        User user=new User();
        user.setId("111");
        user.setUsername("shiny");
        user.setPassword("123");
        user.setEmail("shiny123400@163.com");
        user.setLastPasswordResetDate(new Date());
        user.setLocked(false);
        if(username==null){
            throw new UsernameNotFoundException(username);
        }
        return JwtUserFactory.create(user);
    }
}
