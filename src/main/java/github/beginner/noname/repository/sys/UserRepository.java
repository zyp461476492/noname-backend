package github.beginner.noname.repository.sys;

import github.beginner.noname.domain.entity.sys.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户类 Data Access Object
 *
 * @author zyp on 2018-12-6.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * 根据姓名判断是否存在用户
     *
     * @param name 姓名
     * @return true 如果存在
     */
    boolean existsByName(String name);

    /**
     * 根据部门ID查找用户信息
     * @param orgId 部门ID
     * @return 用户信息
     */
    List<UserEntity> findByOrgId(Long orgId);

}
