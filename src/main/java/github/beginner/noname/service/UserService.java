package github.beginner.noname.service;

import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.entity.sys.user.UserDO;
import org.springframework.data.domain.Pageable;

/**
 * @author zyp on 2018-12-6.
 */
public interface UserService {
    /**
     * 查找所有用户
     *
     * @param pageable 分页组件
     * @return ResponseMsg
     */
    ResponseMsg findAll(Pageable pageable);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return ResponseMsg
     */
    ResponseMsg deleteUserById(Long id);

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return ResponseMsg
     */
    ResponseMsg addUser(UserDO user);

    /**
     * 更新用户
     *
     * @param user     用户信息
     * @param updateBy 更新人ID
     * @return ResponseMsg
     */
    ResponseMsg updateUser(UserDO user, Integer updateBy);

    /**
     * 检查重名用户
     *
     * @param name 姓名
     * @return ResponseMsg
     */
    ResponseMsg checkUserName(String name);
}
