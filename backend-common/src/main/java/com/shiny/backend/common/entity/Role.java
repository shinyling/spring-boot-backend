package com.shiny.backend.common.entity;

import com.shiny.backend.common.base.BaseDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DELL
 */
@Data
@NoArgsConstructor
public class Role extends BaseDomain {
    private String id;

    private String roleName;

    public Role(String roleName){
        this.roleName=roleName;
    }
}
