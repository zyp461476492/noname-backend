package github.beginner.noname.service.impl;

import github.beginner.noname.domain.constant.CommonConstant;
import github.beginner.noname.domain.entity.sys.org.OrgEntity;
import github.beginner.noname.repository.sys.OrgRepository;
import github.beginner.noname.service.OrgService;
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

    @Autowired
    public OrgServiceImpl(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }

    @Override
    public Optional<OrgEntity> findById(Long id) {
        return orgRepository.findById(id);
    }

    @Override
    public OrgEntity addOrg(OrgEntity entity) {
        entity.onCreate();
        return orgRepository.save(entity);
    }

    @Override
    public OrgEntity updateOrg(OrgEntity entity, Integer updateBy) {
        entity.onUpdate(updateBy);
        return orgRepository.save(entity);
    }

    @Override
    public void delOrg(long id) {
        orgRepository.deleteById(id);
    }

    @Override
    public List<OrgEntity> findRoot() {
        return orgRepository.findByParentIsNull();
    }
}
