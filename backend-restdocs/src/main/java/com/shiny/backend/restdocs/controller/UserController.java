package com.shiny.backend.restdocs.controller;

import com.shiny.backend.common.entity.Role;
import com.shiny.backend.common.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiny
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("register")
    public User register(@RequestBody User user){
        user.setLocked(false);
        user.setEmail("ggsmd");
        user.setId("111");
        List<Role> roles=new ArrayList<>(1);
        roles.add(new Role("ROLE_USER"));
        user.setRoles(roles);
        return user;
    }
}
