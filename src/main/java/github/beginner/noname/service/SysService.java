package github.beginner.noname.service;

import github.beginner.noname.domain.entity.sys.user.UserEntity;

/**
 * @author zyp on 2019/2/22
 */
public interface SysService {
    UserEntity findUserByLoginId(String loginId);
}
