package github.beginner.noname.domain.vo.sys.org;

import lombok.Data;

import java.util.Set;

/**
 * 组织机构VO对象
 * @author zyp on 2018/12/19
 */
@Data
public class OrgVO {
    private Long id;
    private String name;
    private String code;
    private Long createDate;
    private Set<OrgVO> children;
}
