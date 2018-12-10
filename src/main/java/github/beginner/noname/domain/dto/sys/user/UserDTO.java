package github.beginner.noname.domain.dto.sys.user;

import github.beginner.noname.domain.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User Data transfer object
 * @author zyp
 * @date 2018-12-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends BaseDTO {
    private String name;

    private String identifyCard;

    private String phone;

    private String email;

    private String lastLoginIp;

    private String avatar;

    private Integer order;

    private Integer orgId;

    private Integer status;

}
