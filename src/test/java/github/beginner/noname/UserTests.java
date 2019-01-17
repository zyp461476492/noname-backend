package github.beginner.noname;

import com.alibaba.fastjson.JSON;
import github.beginner.noname.domain.dto.sys.user.UserDTO;
import github.beginner.noname.domain.entity.sys.org.OrgEntity;
import github.beginner.noname.domain.entity.sys.user.UserEntity;
import github.beginner.noname.repository.sys.UserRepository;
import github.beginner.noname.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zyp on 2019/1/10
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void basicTest() {
        List<UserEntity> userEntityList = userRepository.findByOrgId(8L);
        System.out.println(JSON.toJSONString(userEntityList));
        System.out.println(JSON.toJSONString(new ModelMapper().map(userEntityList, new TypeToken<List<UserDTO>>(){}.getType())));
        System.out.println(userEntityList.size());
    }

    @Test
    public void addTest() {
        UserEntity user = new UserEntity();
        user.setName("test");
        user.setLoginId("test");
        OrgEntity orgEntity = new OrgEntity();
        orgEntity.setId(8L);
        user.setOrg(orgEntity);

        userRepository.save(user);
    }
}
