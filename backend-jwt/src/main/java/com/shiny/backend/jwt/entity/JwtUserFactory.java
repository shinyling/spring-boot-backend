package com.shiny.backend.jwt.entity;

import com.shiny.backend.common.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shiny
 */
public final class JwtUserFactory {

    private JwtUserFactory(){

    }

    public static JwtUser create(User user){
        return new JwtUser(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthorities(user.getRoleStrings()),
                user.getLastPasswordResetDate());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities){
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
