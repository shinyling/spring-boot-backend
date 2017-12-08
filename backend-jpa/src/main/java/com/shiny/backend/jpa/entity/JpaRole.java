package com.shiny.backend.jpa.entity;

import com.shiny.backend.common.base.BaseDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DELL
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_role")
public class JpaRole extends BaseDomain {
    @Id
    @GenericGenerator(name="pk_role_id",strategy = "uuid2")
    @GeneratedValue(generator = "pk_role_id")
    @Column(length = 36)
    private String id;

    @Column(length = 30,unique = true)
    @NotNull
    @Size(min = 3,max = 30)
    private String roleName;

    @ManyToMany
    private List<JpaUser> users=new ArrayList<>();

    public JpaRole(String roleName){
        this.roleName=roleName;
    }
}
