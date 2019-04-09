package github.beginner.noname.service.sys;

import github.beginner.noname.domain.entity.sys.role.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author wpt
 * @version 1.0
 * .
 * .                 .-~~~~~~~~~-._       _.-~~~~~~~~~-.
 * .             __.'              ~.   .~              `.__
 * .           .'//                  \./                  \\`.
 * .         .'//                     |                     \\`.
 * .       .'// .-~"""""""~~~~-._     |     _,-~~~~"""""""~-. \\`.
 * .     .'//.-"                 `-.  |  .-'                 "-.\\`.
 * .   .'//______.============-..   \ | /   ..-============.______\\`.
 * . .'______________________________\|/______________________________`.
 * .                    高山仰止,景行行止.虽不能至,心向往之
 * @date 18/12/13 10:46
 */

public interface RoleService {

    /**
     * 添加角色
     *
     * @param role 角色信息
     * @return ResponseMsg
     */
    RoleEntity addRole(RoleEntity role);

    /**
     * 根据ID删除角色
     *
     * @param id 角色ID
     * @return true 删除成功
     */
    boolean deleteRoleById(Long id);

    /**
     * 批量删除角色
     *
     * @param roleList 待删除角色列表
     * @return true 删除成功
     */
    boolean deleteBatchRole(List<RoleEntity> roleList);

    /**
     * 更新角色信息
     *
     * @param role     待更新角色信息
     * @param updateBy 更新人ID
     * @return ResponseMsg
     */
    RoleEntity updateRole(RoleEntity role, Integer updateBy);

    /**
     * 根据ID查找角色信息
     *
     * @param id 角色ID
     * @return ResponseMsg
     */
    Optional<RoleEntity> queryRoleById(Long id);

    /**
     * 查找所有角色
     *
     * @param pageable pageable组件
     * @return 角色页
     */
    Page<RoleEntity> findAll(Pageable pageable);

    /**
     * 检查角色重名
     *
     * @param name 角色名称
     * @return true 名称已经存在
     */
    boolean checkRoleName(String name);
}
