package github.beginner.noname.domain.entity.sys.org;

import github.beginner.noname.domain.constant.CommonConstant;
import github.beginner.noname.domain.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 组织机构实体类
 * @author zyp on 2018/12/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "Organization")
@Entity
@Table(name = "sys_organization")
public class OrgEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name="parent_id",referencedColumnName="id")
    OrgEntity parent;

    @Column(name = "name", length = 128)
    String name;

    @Column(name = "code", length = 64)
    String code;

    @Column(name = "login_id", length = 512)
    String desc;

    @Column(name = "org_order")
    Integer order;

    @Column(name = "type")
    Integer type;

    public boolean isRoot() {
        if (parent == null) {
            return false;
        }
        return CommonConstant.TREE_ROOT.equals(parent.getId());
    }


}
