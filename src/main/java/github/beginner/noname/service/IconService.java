package github.beginner.noname.service;

import github.beginner.noname.domain.entity.sys.menu.IconEntity;
import github.beginner.noname.domain.vo.sys.menu.IconTypeVO;

import javax.persistence.Column;
import java.util.List;

/**
 * @author zyp on 2019/1/25
 */

public interface IconService {
    List<IconEntity> getAllByType(String type);

    List<IconTypeVO> getAllType();
}
