package github.beginner.noname.service.impl;

import github.beginner.noname.domain.constant.MsgConstant;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.entity.sys.user.UserDO;
import github.beginner.noname.repository.sys.UserRepository;
import github.beginner.noname.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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
    public ResponseMsg findAll(Pageable pageable) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        Page<UserDO> page = userRepository.findAll(pageable);
        retMsg.setData(page);
        return retMsg;
    }

    @Override
    public ResponseMsg deleteUserById(Long id) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.DEL_SUCC);
        if (userRepository.existsById(id)) {
            try {
                userRepository.deleteById(id);
            } catch (Exception e) {
                logger.error("删除用户发生异常", e);
                retMsg.setFailResponse(MsgConstant.DEL_FAIL);
            }

        } else {
            retMsg.setFailResponse(MsgConstant.USER_NOT_EXIST);
        }
        return retMsg;
    }

    @Override
    public ResponseMsg addUser(UserDO user) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.ADD_SUCC);
        user.onCreate();
        UserDO userDO = userRepository.save(user);
        retMsg.setData(userDO);
        return retMsg;
    }

    @Override
    public ResponseMsg updateUser(UserDO user, Integer updateBy) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.UPDATE_SUCC);
        user.onUpdate(updateBy);
        UserDO userDO = userRepository.save(user);
        retMsg.setData(userDO);
        return retMsg;
    }

    @Override
    public ResponseMsg checkUserName(String name) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        if (userRepository.existsByName(name)) {
            retMsg.setMsg(MsgConstant.USER_NAME_REPEAT);
        }
        return retMsg;
    }


}
