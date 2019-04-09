package github.beginner.noname.service.sys.impl;

import github.beginner.noname.domain.entity.sys.user.UserEntity;
import github.beginner.noname.repository.sys.UserRepository;
import github.beginner.noname.service.sys.SysService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author zyp on 2019/2/22
 */
@Service
@Slf4j
public class SysServiceImpl implements SysService {
    @Resource
    private UserRepository userRepository;

    @Override
    public UserEntity findUserByLoginId(String loginId) {
        Optional<UserEntity> optionalUser = userRepository.findByLoginId(loginId);
        return optionalUser.orElse(null);
    }
}
