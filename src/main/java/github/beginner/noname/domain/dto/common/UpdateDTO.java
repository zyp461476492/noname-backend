package github.beginner.noname.domain.dto.common;

import lombok.Data;

/**
 * 更新DTO data为待更新数据，updateBy为更新人ID
 *
 * @author zyp on 2018-12-6.
 */
@Data
public class UpdateDTO<T> {
    T data;

    Integer updateBy;
}
