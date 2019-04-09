package github.beginner.noname.domain.entity.sys.org;

import github.beginner.noname.domain.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 组织机构实体类
 *
 * @author zyp on 2018/12/13
 */
@Getter
@Setter
@ApiModel(value = "Organization")
@Entity
@Table(name = "sys_organization")
public class OrgEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private OrgEntity parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
    private Set<OrgEntity> children = new HashSet<>();

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "code", length = 64)
    private String code;

    @Column(name = "org_desc", length = 512)
    private String desc;

    @Column(name = "org_order")
    private Integer order;

    @Column(name = "type", length = 4)
    private String type;

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
                ", childSize= "+ children.size() +
                "}";
    }


}
