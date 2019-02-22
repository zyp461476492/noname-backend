package github.beginner.noname.repository.sys;

import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zyp on 2019/1/17
 */
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    List<MenuEntity> findByParentIsNull();
}
