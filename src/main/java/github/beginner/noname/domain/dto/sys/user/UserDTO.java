package github.beginner.noname.domain.dto.sys.user;

import com.alibaba.fastjson.annotation.JSONField;
import github.beginner.noname.domain.dto.BaseDTO;
import github.beginner.noname.domain.dto.sys.OrgDTO;
import github.beginner.noname.domain.entity.sys.org.OrgEntity;
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
    private String loginId;

    private String name;

    private String identifyCard;

    private String phone;

    private String email;

    private String lastLoginIp;

    private String avatar;

    private String gender;

    private Integer order;

    private OrgDTO org;

    private Integer status;

}
