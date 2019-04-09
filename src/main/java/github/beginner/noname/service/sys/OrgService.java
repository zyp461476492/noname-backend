package github.beginner.noname.service.sys;

import github.beginner.noname.domain.entity.sys.org.OrgEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author zyp on 2018/12/14
 */
public interface OrgService extends BaseService {
    /**
     * 根据ID查找Org
     *
     * @param id ID
     * @return orgEntity
     */
    Optional<OrgEntity> findById(Long id);

    /**
     * 添加一个新的组织机构
     *
     * @param entity 组织结构信息
     * @return 添加后的信息
     */
    OrgEntity addOrg(OrgEntity entity);

    /**
     * 更新组织机构
     *
     * @param entity   组织机构实体
     * @param updateBy 更新人
     * @return 更新后的组织机构
     */
    OrgEntity updateOrg(OrgEntity entity, Integer updateBy);

    /**
     * 删除组织机构
     * 找到待删除节点的child，
     * 随后更新child节点的parent为待删除节点的parent
     * 根节点不能删除
     *
     * @param id 待删除的组织机构id
     */
    void delOrg(long id);

    /**
     * 查询所有的树根节点
     *
     * @return 返回parent为空的节点
     */
    List<OrgEntity> findRoot();
}
