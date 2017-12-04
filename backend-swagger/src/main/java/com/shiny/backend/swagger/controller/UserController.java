package com.shiny.backend.swagger.controller;

import com.shiny.backend.common.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shiny
 **/
@Api(value = "用户controller",tags = {"用户操作相关接口"})
@RestController
@RequestMapping("users")
public class UserController {

    @ApiOperation(value = "获取用户列表",httpMethod = "POST",response = List.class,
            notes = "调用用户列表接口",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("list")
    public List<User> list(){
        return new ArrayList<>();
    }
}
