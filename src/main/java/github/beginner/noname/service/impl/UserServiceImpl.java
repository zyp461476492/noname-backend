package github.beginner.noname.service.impl;

import github.beginner.noname.domain.constant.MsgConstant;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.entity.sys.user.UserEntity;
import github.beginner.noname.repository.sys.UserRepository;
import github.beginner.noname.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author zyp on 2018-12-6.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        Page<UserEntity> page = userRepository.findAll(pageable);
        retMsg.setData(page);
        return page;
    }

    @Override
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            try {
                userRepository.deleteById(id);
            } catch (Exception e) {
                logger.error("删除用户发生异常", e);
                return false;
            }

        } else {
            return false;
        }
        return true;
    }

    @Override
    public UserEntity addUser(UserEntity user) {
        user.onCreate();
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> queryUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity updateUser(UserEntity user, Integer updateBy) {
        user.onUpdate(updateBy);
        return userRepository.save(user);
    }

    @Override
    public boolean checkUserName(String name) {
        return userRepository.existsByName(name);
    }


}
