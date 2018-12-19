package github.beginner.noname.service;

import github.beginner.noname.domain.entity.sys.org.OrgEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author zyp on 2018/12/14
 */
public interface OrgService extends BaseService {
    /**
     * 根据ID查找Org
     * @param id ID
     * @return orgEntity
     */
    Optional<OrgEntity> findById(Long id);

    /**
     * 添加一个新的组织机构
     * @param entity 组织结构信息
     * @return 添加后的信息
     */
    OrgEntity addOrg(OrgEntity entity);

    /**
     * 删除组织机构
     * 找到待删除节点的child，
     * 随后更新child节点的parent为待删除节点的parent
     * 根节点不能删除
     * @param node 待删除的组织机构（ID，PARENT_ID）
     * @param updateBy 更新人ID
     * @return true 成功 false 失败
     */
    boolean delOrg(OrgEntity node, Integer updateBy);

    /**
     * 找到id的子树
     * @param parent id构造的orgEntity
     * @return id对应组织机构的子树
     */
    List<OrgEntity> findChild(OrgEntity parent);

    /**
     * 查询所有的树根节点
     * @return 返回parent为空的节点
     */
    List<OrgEntity> findRoot();
}
