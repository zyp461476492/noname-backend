package github.beginner.noname.service.sys.impl;

import github.beginner.noname.common.enums.ExceptionEnum;
import github.beginner.noname.common.exception.NonameException;
import github.beginner.noname.constant.MsgConstant;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.entity.sys.role.RoleEntity;
import github.beginner.noname.repository.sys.RoleRepository;
import github.beginner.noname.service.sys.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
 * @date 18/12/13 11:20
 */

@Service
@Slf4j
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public RoleEntity addRole(RoleEntity role) {
        role.onCreate();
        return roleRepository.save(role);
    }

    @Override
    public boolean deleteRoleById(Long id) {
        boolean res = true;
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        } else {
            log.error("ID 为{}的角色不存在", id);
            res = false;
        }
        return res;
    }

    @Override
    public boolean deleteBatchRole(List<RoleEntity> roleList) {
        //TODO 判断roleList为null
        roleRepository.deleteInBatch(roleList);
        return true;
    }

    @Override
    public RoleEntity updateRole(RoleEntity role, Integer updateBy) {
        if (!roleRepository.existsById(role.getId())) {
            log.error("ID为{}的角色不存在", role.getId());
            throw new NonameException(ExceptionEnum.ROLE_NOT_EXIST);
        }
        role.onUpdate(updateBy);
        return roleRepository.save(role);
    }

    @Override
    public Optional<RoleEntity> queryRoleById(Long id) {
        if (!roleRepository.existsById(id)) {
            log.error("ID为{}的角色不存在", id);
            throw new NonameException(ExceptionEnum.ROLE_NOT_EXIST);
        }
        return roleRepository.findById(id);
    }

    @Override
    public Page<RoleEntity> findAll(Pageable pageable) {
        ResponseMsg responseMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        Page<RoleEntity> entityPage = roleRepository.findAll(pageable);
        responseMsg.setData(entityPage);
        return entityPage;
    }

    @Override
    public List<RoleEntity> queryAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public boolean checkRoleName(String name) {
        return roleRepository.existsByRoleName(name);
    }
}
