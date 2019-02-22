package github.beginner.noname.domain.entity.sys.menu;

import github.beginner.noname.domain.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 图标
 * type代表图片类型，如element-ui自带的图标，或第三方图标
 * 用以区别图标的来源
 * @author zyp on 2019/1/25
 */
@Data
@ApiModel(value = "Icon")
@Entity
@Table(name = "sys_icon")
@EqualsAndHashCode(callSuper = true)
public class IconEntity extends BaseEntity {
    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "type", length = 64)
    private String type;

    /**
     * 图标类型的中文说明
     */
    @Column(name = "type_name", length = 64)
    private String typeName;
}
