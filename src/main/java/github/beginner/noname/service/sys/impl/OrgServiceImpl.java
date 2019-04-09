package github.beginner.noname.service.sys.impl;

import github.beginner.noname.domain.entity.sys.org.OrgEntity;
import github.beginner.noname.repository.sys.OrgRepository;
import github.beginner.noname.repository.sys.UserRepository;
import github.beginner.noname.service.sys.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author zyp on 2018/12/14
 */
@Service
public class OrgServiceImpl extends BaseServiceImpl implements OrgService {
    private final OrgRepository orgRepository;

    private final UserRepository userRepository;

    @Autowired
    public OrgServiceImpl(OrgRepository orgRepository, UserRepository userRepository) {
        this.orgRepository = orgRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<OrgEntity> findById(Long id) {
        return orgRepository.findById(id);
    }

    @Override
    public OrgEntity addOrg(OrgEntity entity) {
        setNull(entity, OrgEntity.class, "parent", "setParent", null);
        entity.onCreate();
        return orgRepository.save(entity);
    }

    @Override
    public OrgEntity updateOrg(OrgEntity entity, Integer updateBy) {
        setNull(entity, OrgEntity.class, "parent", "setParent", null);
        entity.onUpdate(updateBy);
        return orgRepository.save(entity);
    }

    @Override
    public void delOrg(long id) {
        // 删除前将所有 属于该部门的人员的组织机构设置为空
        userRepository.updateOrg(id);
        orgRepository.deleteById(id);
    }

    @Override
    public List<OrgEntity> findRoot() {
        return orgRepository.findByParentIsNull();
    }
}
