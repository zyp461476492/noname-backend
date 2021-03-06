package github.beginner.noname.service.sys.impl;

import github.beginner.noname.domain.entity.sys.org.OrgEntity;
import github.beginner.noname.domain.entity.sys.user.UserEntity;
import github.beginner.noname.repository.sys.UserRepository;
import github.beginner.noname.service.sys.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author zyp on 2018-12-6.
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findUserByOrg(Long orgId) {
        return userRepository.findByOrgId(orgId);
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            try {
                userRepository.deleteById(id);
            } catch (Exception e) {
                log.error("删除用户发生异常", e);
                return false;
            }

        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBatchUser(List<UserEntity> userList) {
        userRepository.deleteInBatch(userList);
        return true;
    }

    @Override
    public UserEntity addUser(UserEntity user) {
        setNull(user, OrgEntity.class, "org", "setOrg", null);
        user.onCreate();
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> queryUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity updateUser(UserEntity user, Integer updateBy) {
        setNull(user, OrgEntity.class, "org", "setOrg", null);
        user.onUpdate(updateBy);
        return userRepository.save(user);
    }

    @Override
    public boolean checkUserName(String name) {
        return userRepository.existsByName(name);
    }

    /**
     * 如果user传递的组织机构为空，则设置组织机构为null，防止unsaved异常
     *
     * @param user 用户信息
     */
    private void setNull(UserEntity user) {
        if (isEmpty(user.getOrg())) {
            user.setOrg(null);
        }
    }
}
