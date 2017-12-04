package com.shiny.backend.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.shiny.backend.common.base.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shiny
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDomain {
    private String id;

    private String username;

    private String password;

    private String email;

    private boolean locked;

    private Date lastPasswordResetDate;

    @JSONField(serialize = false)
    private List<Role> roles=new ArrayList<>();

    @JSONField(serialize = false)
    private List<String> roleStrings=new ArrayList<>();

}
