package github.beginner.noname.repository.sys;

import com.alibaba.fastjson.JSON;
import github.beginner.noname.domain.vo.sys.menu.IconTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zyp on 2019/1/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IconRepoTest {
    @Autowired
    private IconRepo iconRepo;

    @Test
    public void findByType() {
        String type = "element";
        log.info(JSON.toJSONString(iconRepo.findByType(type)));
    }

    @Test
    public void getAllType() {
        List<IconTypeVO> list = iconRepo.getAllType();
        log.info(JSON.toJSONString(list));
    }


}
