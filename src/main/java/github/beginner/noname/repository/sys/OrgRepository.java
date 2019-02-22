package github.beginner.noname.repository.sys;

import github.beginner.noname.domain.entity.sys.org.OrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zyp on 2018/12/14
 */
public interface OrgRepository extends JpaRepository<OrgEntity, Long> {
    /**
     * 查询 parent的孩子节点
     * @param parent parent节点
     * @return parent的孩子节点
     */
    List<OrgEntity> findChildByParent(OrgEntity parent);

    /**
     * 找到root节点
     * @return root
     */
    List<OrgEntity> findByParentIsNull();
}
