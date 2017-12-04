package com.shiny.backend.jpa.entity;

import com.shiny.backend.common.base.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shiny
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
public class JpaUser extends BaseDomain {
    @Id
    @Column(name = "id",length = 36)
    @GenericGenerator(name="pk_user_id",strategy = "uuid2")
    @GeneratedValue(generator = "pk_user_id")
    private String id;

    @Column(name = "username",length = 20,unique = true)
    @NotNull
    @Size(min = 4,max = 20)
    private String username;

    @Column(name="password",length = 100)
    @NotNull
    @Size(min = 10,max = 100)
    private String password;

    @Column(name = "email", length=50)
    private String email;

    @Column(name = "locked",length = 1)
    private boolean locked;

    @Column(name = "last_password_reset_date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role",joinColumns = {
            @JoinColumn(name = "user_id",referencedColumnName = "id")},inverseJoinColumns ={
            @JoinColumn(name = "role_id",referencedColumnName = "id")
    } )
    private List<JpaRole> roles=new ArrayList<>();

    @Transient
    private List<String> roleStrings;

    public List<String> getRoleStrings(){
        List<String> temp=new ArrayList<>(roles.size());
        for(JpaRole role:roles){
            temp.add(role.getRoleName());
        }
        return temp;
    }
}
