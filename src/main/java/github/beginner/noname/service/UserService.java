package github.beginner.noname.service;

import github.beginner.noname.domain.entity.sys.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author zyp on 2018-12-6.
 */
public interface UserService {
    /**
     * 根据组织机构查找对应的用户信息
     * @param orgId 组织机构ID
     * @return org 下的人员信息
     */
    List<UserEntity> findUserByOrg(Long orgId);
    /**
     * 查找所有用户
     *
     * @param pageable 分页组件
     * @return ResponseMsg
     */
    Page<UserEntity> findAll(Pageable pageable);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return ResponseMsg
     */
    boolean deleteUserById(Long id);

    /**
     * 批量删除用户
     * @param userList 待删除的用户
     * @return true 删除成功
     */
    boolean deleteBatchUser(List<UserEntity> userList);

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return ResponseMsg
     */
    UserEntity addUser(UserEntity user);

    /**
     * 根据ID查询用户数据
     * @param id id
     * @return 用户信息
     */
    Optional<UserEntity> queryUserById(Long id);

    /**
     * 更新用户
     *
     * @param user     用户信息
     * @param updateBy 更新人ID
     * @return ResponseMsg
     */
    UserEntity updateUser(UserEntity user, Integer updateBy);

    /**
     * 检查重名用户
     *
     * @param name 姓名
     * @return ResponseMsg
     */
    boolean checkUserName(String name);
}
