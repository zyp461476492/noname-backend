package github.beginner.noname.service;

import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import github.beginner.noname.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * @author zyp on 2019/1/17
 */
public interface MenuService extends BaseService {
    List<MenuEntity> findRoot();
}
