package github.beginner.noname.repository.sys;

import github.beginner.noname.domain.entity.sys.user.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色类
 *
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
 * @date 18/12/13 10:13
 */

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * 根据角色名判断是否存在
     *
     * @param name 角色名
     * @return true 存在
     */
    boolean existsByRoleName(String name);
}
