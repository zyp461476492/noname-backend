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
        return orgRepository.save(entity);
    }

    @Override
    public boolean delOrg(OrgEntity node, Integer updateBy) {
        if (CommonConstant.TREE_ROOT.equals(node.getParent().getId())) {
            return false;
        }
        List<OrgEntity> childList = orgRepository.findChildByParent(node);
        OrgEntity parent = node.getParent();
        for (OrgEntity entity: childList) {
            entity.setParent(parent);
            // 随后更新数据
            entity.onUpdate(updateBy);
        }
        orgRepository.saveAll(childList);
        return true;
    }

    @Override
    public List<OrgEntity> findChild(OrgEntity parent) {
        return orgRepository.findChildByParent(parent);
    }
}
