package github.beginner.noname.service.impl;

import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import github.beginner.noname.repository.sys.MenuRepository;
import github.beginner.noname.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zyp on 2019/1/17
 */
@Service
@Slf4j
public class MenuServiceImpl extends BaseServiceImpl implements MenuService  {
    @Resource
    private MenuRepository repository;

    @Override
    public List<MenuEntity> findRoot() {
        return repository.findByParentIsNull();
    }
}
