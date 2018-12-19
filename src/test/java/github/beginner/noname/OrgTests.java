package github.beginner.noname;

import github.beginner.noname.domain.entity.sys.org.OrgEntity;
import github.beginner.noname.repository.sys.OrgRepository;
import github.beginner.noname.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author zyp on 2018/12/17
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgTests {
    @Autowired
    private OrgService orgService;

    @Autowired
    private OrgRepository orgRepository;

    @Test
    public void orgTest() {
        List<OrgEntity> entityList = orgRepository.findAll();
        for (OrgEntity entity : entityList) {
            log.info(entity.toString());
        }
        Optional<OrgEntity> entity = orgRepository.findById(1L);
        if (entity.isPresent()) {
            log.info(entity.get().toString());
        } else {
            log.info("数据为空");
        }
    }

    @Test
    public void orgParentTest() {
        OrgEntity parent = new OrgEntity();
        parent.setId(1L);
        List<OrgEntity> entityList = orgRepository.findChildByParent(parent);
        for (OrgEntity entity : entityList) {
            log.info(entity.toString());
        }
    }
}
