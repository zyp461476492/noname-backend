package github.beginner.noname.domain.entity.sys.org;

import com.alibaba.fastjson.annotation.JSONField;
import github.beginner.noname.domain.constant.CommonConstant;
import github.beginner.noname.domain.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * 组织机构实体类
 *
 * @author zyp on 2018/12/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "Organization")
@Entity
@Table(name = "sys_organization")
public class OrgEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private OrgEntity parent;

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "code", length = 64)
    private String code;

    @Column(name = "org_desc", length = 512)
    private String desc;

    @Column(name = "org_order")
    private Integer order;

    @Column(name = "type")
    private Integer type;

    public boolean isRoot() {
        return parent == null;
    }

    @Override
    public String toString() {
        String parentId = parent == null ? "null" : String.valueOf(parent.getId());
        return "{" +
                "id=" + getId() +
                ", name=" + name +
                ", isRoot=" + isRoot() +
                ", parent=" + parentId +
                "}";
    }


}
