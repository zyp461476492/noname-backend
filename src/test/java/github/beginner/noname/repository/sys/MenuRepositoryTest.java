package github.beginner.noname.repository.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import github.beginner.noname.domain.vo.sys.menu.MenuVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author zyp on 2019/1/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void baseTest() {
        for (int i = 0; i < 10; i++) {
            menuRepository.save(buildEntity());
        }
    }

    @Test
    public void queryTestList() {
        List<MenuEntity> list =  menuRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<MenuVO> voList = modelMapper.map(list, new TypeToken<List<MenuVO>>(){}.getType());
        System.out.println(JSON.toJSONString(voList, SerializerFeature.DisableCircularReferenceDetect));
    }

    @Test
    public void queryTest() {
        Optional<MenuEntity> entity =  menuRepository.findById(1L);
        System.out.println(JSON.toJSONString(entity));
    }

    private MenuEntity buildEntity() {
        MenuEntity entity = new MenuEntity();
        entity.setName("菜单1");
        entity.setUrl("/main/sys/user");
        entity.setOrder(0);
        entity.setIcon("el-icon-menu");
        entity.onCreate();
        return entity;
    }
}
