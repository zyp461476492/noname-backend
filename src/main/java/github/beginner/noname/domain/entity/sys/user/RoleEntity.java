package github.beginner.noname.domain.entity.sys.user;

import github.beginner.noname.domain.entity.BaseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Cleanup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

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

    @Column(name = "is_sys", length = 4)
    private Integer isSys;

    @Column(name = "user_type", length = 32)
    private String userType;

    @Column(name = "data_scope", length = 4)
    private Integer dataScope;

    @Column(name = "status", length = 4)
    private Integer status;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "org_id")
    private Integer orgId;

    @Column(name = "org_name")
    private String orgName;
}
