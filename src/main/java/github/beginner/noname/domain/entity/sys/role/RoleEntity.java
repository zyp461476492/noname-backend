package github.beginner.noname.domain.entity.sys.role;

import github.beginner.noname.domain.entity.BaseEntity;
import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author wpt
 * @version 1.0
 * .
 * .                 .-~~~~~~~~~-._       _.-~~~~~~~~~-.
 * .             __.'              ~.   .~              `.__
 * .           .'//                  \./                  \\`.
 * .         .'//                     |                     \\`.
 * .       .'// .-~"""""""~~~~-._     |     _,-~~~~"""""""~-. \\`.
 * .     .'//.-"                 `-.  |  .-'                 "-.\\`.
 * .   .'//______.============-..   \ | /   ..-============.______\\`.
 * . .'______________________________\|/______________________________`.
 * .                    高山仰止,景行行止.虽不能至,心向往之
 * @date 18/12/12 18:00
 */

@Data
@Entity
@ApiModel(value = "Role")
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_role")
public class RoleEntity extends BaseEntity {

    @Column(name = "role_code", length = 16)
    private String roleCode;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_type", length = 128)
    private String roleType;

    @Column(name = "is_sys", length = 1)
    private String isSys;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "remarks")
    private String remarks;

    /**
     * 1、关系维护端，负责多对多关系的绑定和解除
     * 2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(Role，这里是role_id)
     * 3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(Menu，即menu_id)
     * 4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
     * 即表名为user_authority
     * 关联到主表的外键名：主表名+下划线+主表中的主键列名,即user_id
     * 关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即authority_id
     * 主表就是关系维护端对应的表，从表就是关系被维护端对应的表
     */
    @ManyToMany
    @JoinTable(name = "role_menu", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private List<MenuEntity> menuList;
}
