package com.shiny.backend.jwt.service.impl;

import com.shiny.backend.common.entity.Role;
import com.shiny.backend.common.entity.User;
import com.shiny.backend.jwt.entity.JwtUser;
import com.shiny.backend.jwt.service.AuthService;
import com.shiny.backend.jwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * @author DELL
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public JwtUser login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken=new UsernamePasswordAuthenticationToken(username,password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtUser jwtUser= (JwtUser) userDetails;
        jwtUser.setToken(token);
        return jwtUser;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public User register(User userAdd) {
        final String username = userAdd.getUsername();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userAdd.getPassword();
        userAdd.setPassword(encoder.encode(rawPassword));
        userAdd.setLastPasswordResetDate(new Date());
        userAdd.setLocked(false);
        userAdd.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userAdd;
    }
}
