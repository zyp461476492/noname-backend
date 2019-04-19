package github.beginner.noname.domain.entity.sys.user;

import github.beginner.noname.domain.entity.BaseEntity;
import github.beginner.noname.domain.entity.sys.org.OrgEntity;
import github.beginner.noname.domain.entity.sys.role.RoleEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * @author zyp on 2018-12-5.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "User")
@Entity
@Table(name = "sys_user")
public class UserEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private OrgEntity org;

    @Column(name = "login_id", length = 16)
    private String loginId;

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "password", length = 512)
    private String password;

    @Column(name = "identify_card", length = 18)
    private String identifyCard;

    @Column(name = "phone", length = 16)
    private String phone;

    @Column(name = "email", length = 32)
    private String email;

    @Column(name = "last_login_ip", length = 128)
    private String lastLoginIp;

    @Column(name = "avatar", length = 512)
    private String avatar;

    @Column(name = "gender", length = 4)
    private String gender;

    @Column(name = "user_order")
    private Integer order;

    @Column(name = "status")
    private Integer status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roleList;

}
