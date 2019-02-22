package github.beginner.noname.domain.dto.sys;

import github.beginner.noname.domain.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zyp on 2019/1/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrgDTO extends BaseDTO {
    private String name;

    private String code;
}
