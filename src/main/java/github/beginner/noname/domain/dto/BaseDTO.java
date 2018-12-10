package github.beginner.noname.domain.dto;

import lombok.Data;

/**
 * @author 46147
 */
@Data
public class BaseDTO {
    private Long id;

    private Long createDate;

    private Long updateDate;

    private Integer updateBy;
}
