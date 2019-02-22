package github.beginner.noname.domain.vo.sys;

import lombok.Data;

/**
 * @author zyp on 2019/2/22
 */
@Data
public class LoginVO {
    private String loginId;

    private String pwd;

    private String code;
}
