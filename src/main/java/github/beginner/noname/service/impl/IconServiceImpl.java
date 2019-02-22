package github.beginner.noname.service.impl;

import github.beginner.noname.domain.entity.sys.menu.IconEntity;
import github.beginner.noname.domain.vo.sys.menu.IconTypeVO;
import github.beginner.noname.repository.sys.IconRepo;
import github.beginner.noname.service.IconService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyp on 2019/1/25
 */
@Service
@Slf4j
public class IconServiceImpl extends BaseServiceImpl implements IconService{
    private final IconRepo iconRepo;

    @Autowired
    public IconServiceImpl(IconRepo iconRepo) {
        this.iconRepo = iconRepo;
    }

    @Override
    public List<IconEntity> getAllByType(String type) {
        return iconRepo.findByType(type);
    }

    @Override
    public List<IconTypeVO> getAllType() {
        return iconRepo.getAllType();
    }
}
