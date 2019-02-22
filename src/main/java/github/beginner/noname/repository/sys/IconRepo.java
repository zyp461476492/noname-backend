package github.beginner.noname.repository.sys;

import github.beginner.noname.domain.entity.sys.menu.IconEntity;
import github.beginner.noname.domain.vo.sys.menu.IconTypeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zyp on 2019/1/25
 */
public interface IconRepo extends JpaRepository<IconEntity, Long> {
    List<IconEntity> findByType(String type);

    @Query("select distinct " +
            "new github.beginner.noname.domain.vo.sys.menu.IconTypeVO(i.type, i.typeName) " +
            "from IconEntity i")
    List<IconTypeVO> getAllType();
}
