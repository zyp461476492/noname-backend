package github.beginner.noname.service.impl;

import github.beginner.noname.domain.entity.sys.user.RoleEntity;
import github.beginner.noname.repository.sys.RoleRepository;
import github.beginner.noname.service.RoleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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
 * @date 18/12/19 14:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Autowired
    RoleServiceImpl roleService;

    @Test
    public void addRoleTest() {
        RoleEntity role = new RoleEntity();
        role.setDataScope(1);
        role.setIsSys(1);
        role.setOrgId(100);
        role.setOrgName("test");
        role.setRemarks("addRole Test add this item in RoleServiceImplTest");
        role.setRoleCode("1000");
        role.setRoleName("TestRole1111");
        role.setRoleType("test");
        role.setStatus(1);
        role.setUserType("testUser");
        role.setCreateBy(1);

        RoleEntity result = roleService.addRole(role);
        Assert.assertNotNull(result);

    }

    @Test
    public void deleteRoleByIdTest() {
        boolean result = roleService.deleteRoleById(2L);
        Assert.assertEquals(true, result);
    }

    @Test
    public void deleteBatchRoleTest() {
        //TODO deleteBatchTest
    }

    @Test
    public void updateRoleTest() {
        RoleEntity role = new RoleEntity();
        role.setId(3L);
        role.setDataScope(1);
        role.setIsSys(1);
        role.setOrgId(100);
        role.setOrgName("RoleUpdateTest");
        role.setRemarks("addRole Test add this item in RoleServiceImplTest");
        role.setRoleCode("100");
        role.setRoleName("TestRole");
        role.setRoleType("test");
        role.setStatus(1);
        role.setUserType("testUser");
        role.setCreateBy(1);

        RoleEntity result = roleService.updateRole(role, 2);
        Assert.assertEquals(roleService.queryRoleById(3L).get(), result);

    }

    @Test
    public void queryRoleByIdTest() {
        Optional<RoleEntity> result = roleService.queryRoleById(3L);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAllTest() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<RoleEntity> roleEntityPage = roleService.findAll(pageRequest);
        System.out.println(roleEntityPage);
    }

    @Test
    public void checkRoleName() {
        boolean result = roleService.checkRoleName("TestRole");
        Assert.assertEquals(true, result);
    }
}