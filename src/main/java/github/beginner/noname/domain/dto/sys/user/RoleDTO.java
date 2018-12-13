package github.beginner.noname.domain.dto.sys.user;

import github.beginner.noname.domain.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Role DTO
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
 * @date 18/12/13 10:17
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends BaseDTO {
    private String roleCode;

    private String roleName;

    private String roleType;

    private Integer isSys;

    private String userType;

    private Integer dataScope;

    private Integer status;

    private String remarks;

    private Integer orgId;

    private String orgName;
}
