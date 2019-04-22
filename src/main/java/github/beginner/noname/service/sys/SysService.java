package github.beginner.noname.service.sys;

import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import github.beginner.noname.domain.entity.sys.user.UserEntity;

import java.util.Set;

/**
 * @author zyp on 2019/2/22
 */
public interface SysService {
    UserEntity findUserByLoginId(String loginId);

    Set<MenuEntity> getUserAuthList(String jws);
}
