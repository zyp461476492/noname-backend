package github.beginner.noname.domain.entity.sys.menu;

import github.beginner.noname.domain.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zyp on 2019/1/17
 */
@Getter
@Setter
@ApiModel(value = "Menu")
@Entity
@Table(name = "sys_menu")
public class MenuEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private MenuEntity parent;

    @OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.EAGER, mappedBy = "parent")
    private Set<MenuEntity> children = new HashSet<>();

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "url", length = 512)
    private String url;

    @Column(name = "icon", length = 64)
    private String icon;

    @Column(name = "menu_order")
    private Integer order;


}
