package github.beginner.noname.domain.vo.sys.menu;

import lombok.Data;

/**
 * @author zyp on 2019/1/25
 */
@Data
public class IconTypeVO {
    private String type;

    private String typeName;

    public IconTypeVO(String type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }
}
