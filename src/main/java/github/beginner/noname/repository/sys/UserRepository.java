package github.beginner.noname.repository.sys;

import github.beginner.noname.domain.entity.sys.user.UserEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * 用户类 Data Access Object
 *
 * @author zyp on 2018-12-6.
 */
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * 根据loginID 获取用户信息
     * @param loginId loginid
     * @return userEntity
     */
    Optional<UserEntity> findByLoginId(String loginId);

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

    /**
     * 删除组织机构时，级联更新user的org为空
     * @param orgId 组织机构ID
     */
    @Modifying
    @Query("update UserEntity u set u.org.id = null where u.org.id = :orgId")
    void updateOrg(@Param(value = "orgId") Long orgId);

}
