package github.beginner.noname.domain.entity.sys.tree;

import github.beginner.noname.domain.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zyp on 2019/4/9
 */
@Data
@Entity
@ApiModel(value = "TreeNode")
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_tree_node")
public class TreeNodeEntity extends BaseEntity {
}
