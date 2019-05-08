package github.beginner.noname.domain.vo.sys.menu;

import lombok.Data;

/**
 * @author zyp on 2019/5/8
 */
@Data
public class MenuDTO {
    private Long id;
    private MenuDTO parent;
    private String name;
    private String url;
    private String icon;
}
