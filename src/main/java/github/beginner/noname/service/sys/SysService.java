package github.beginner.noname.service.sys;

import github.beginner.noname.domain.entity.sys.user.UserEntity;
import github.beginner.noname.domain.vo.sys.menu.MenuDTO;

import java.util.Set;

/**
 * @author zyp on 2019/2/22
 */
public interface SysService {
    UserEntity findUserByLoginId(String loginId);

    Set<MenuDTO> getUserAuthList(String jws);
}
