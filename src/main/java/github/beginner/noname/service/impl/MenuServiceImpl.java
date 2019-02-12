package github.beginner.noname.service.impl;

import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import github.beginner.noname.repository.sys.MenuRepository;
import github.beginner.noname.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author zyp on 2019/1/17
 */
@Service
@Slf4j
public class MenuServiceImpl extends BaseServiceImpl implements MenuService  {
    @Resource
    private MenuRepository repository;

    @Override
    public Optional<MenuEntity> queryMenuById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<MenuEntity> findRoot() {
        return repository.findByParentIsNull();
    }

    @Override
    public MenuEntity addMenu(MenuEntity entity) {
        entity.onCreate();
        return repository.save(entity);
    }

    @Override
    public boolean deleteMenuById(Long id) {
        boolean res = true;
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            log.error(this.getClass().getName() + "-deleteMenuById", e);
            res = false;
        }
        return res;
    }

    @Override
    public MenuEntity updateMenu(MenuEntity entity) {
        entity.onUpdate(1);
        return repository.save(entity);
    }
}
