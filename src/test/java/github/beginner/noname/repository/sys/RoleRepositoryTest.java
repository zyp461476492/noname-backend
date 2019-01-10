package github.beginner.noname.repository.sys;

import github.beginner.noname.domain.entity.sys.user.RoleEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author wpt
 * @version 1.0
 * .
 * .                 .-~~~~~~~~~-._       _.-~~~~~~~~~-.
 * .             __.'              ~.   .~              `.__
 * .           .'//                  \./                  \\`.
 * .         .'//                     |                     \\`.
 * .       .'// .-~"""""""~~~~-._     |     _,-~~~~"""""""~-. \\`.
 * .     .'//.-"                 `-.  |  .-'                 "-.\\`.
 * .   .'//______.============-..   \ | /   ..-============.______\\`.
 * . .'______________________________\|/______________________________`.
 * .                    高山仰止,景行行止.虽不能至,心向往之
 * @date 18/12/19 14:23
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;


    @Test
    public void saveTest() {
        RoleEntity role = new RoleEntity();
        role.setDataScope(1);
        role.setIsSys(1);
        role.setOrgId(100);
        role.setOrgName("test");
        role.setRemarks("save Test add this item");
        role.setRoleCode("100");
        role.setRoleName("TestRole");
        role.setRoleType("test");
        role.setStatus(1);
        role.setUserType("testUser");
        role.setCreateBy(1);

        RoleEntity result = roleRepository.save(role);
        Assert.assertNotNull(result);
    }

    @Test
    public void existsByRoleNameTest() {
        boolean result = roleRepository.existsByRoleName("TestRole");
        Assert.assertEquals(true, result);
    }

}