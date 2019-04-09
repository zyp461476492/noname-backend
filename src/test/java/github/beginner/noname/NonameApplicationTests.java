package github.beginner.noname;

import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import github.beginner.noname.domain.entity.sys.role.RoleEntity;
import github.beginner.noname.repository.sys.MenuRepository;
import github.beginner.noname.repository.sys.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NonameApplicationTests {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MenuRepository menuRepository;


	@Test
	public void contextLoads() {
		manyToManyTest();
	}

	private void manyToManyTest() {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleName("张三");

		MenuEntity menuEntity = new MenuEntity();
		menuEntity.setParent(null);
		menuEntity.setName("测试菜单");

		List<MenuEntity> list = new ArrayList<>();
		list.add(menuEntity);
		roleEntity.setMenuList(list);

		menuRepository.save(menuEntity);
		roleRepository.save(roleEntity);
	}



}
