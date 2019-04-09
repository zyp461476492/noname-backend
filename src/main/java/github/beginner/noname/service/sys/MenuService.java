package github.beginner.noname.service.sys;

import github.beginner.noname.domain.entity.sys.menu.MenuEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author zyp on 2019/1/17
 */
public interface MenuService extends BaseService {
    /**
     * 根据ID查询菜单的信息
     * @param id id
     * @return menuEntity
     */
    Optional<MenuEntity> queryMenuById(Long id);

    /**
     * 查询菜单树
     * @return 菜单树
     */
    List<MenuEntity> findRoot();

    /**
     * 添加一个新的菜单
     * @param entity 菜单实体
     * @return 添加后的菜单实体
     */
    MenuEntity addMenu(MenuEntity entity);

    /**
     * 根据ID 删除指定的菜单
     * @param id 待删除的菜单ID
     * @return true 删除成功
     */
    boolean deleteMenuById(Long id);

    /**
     * 更新菜单
     * @param entity 待更新的菜单实体
     * @return 更新后的菜单实体
     */
    MenuEntity updateMenu(MenuEntity entity);
}
