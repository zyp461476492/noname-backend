package github.beginner.noname.domain.dto;

import lombok.Data;

/**
 * @author zyp on 2018-12-10
 */
@Data
public class BaseDTO {
    private Long id;

    private Long createDate;

    private Long updateDate;

    private Integer updateBy;
}
