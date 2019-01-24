package github.beginner.noname.domain.vo.sys.menu;

import lombok.Data;

import java.util.Set;

/**
 * @author zyp on 2019/1/17
 */
@Data
public class MenuVO {
    private Long id;
    private String name;
    private String url;
    private String icon;
    private Set<MenuVO> children;
    public boolean hasChild() {
        return this.children != null && this.children.size() > 0;
    }
}
