package github.beginner.noname.service.sys.impl;

import github.beginner.noname.common.PageConvert;
import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import github.beginner.noname.domain.entity.sys.role.RoleEntity;
import github.beginner.noname.domain.entity.sys.user.UserEntity;
import github.beginner.noname.domain.vo.sys.menu.MenuDTO;
import github.beginner.noname.repository.sys.UserRepository;
import github.beginner.noname.service.sys.SysService;
import github.beginner.noname.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author zyp on 2019/2/22
 */
@Service
@Slf4j
public class SysServiceImpl implements SysService {
    @Resource
    private ModelMapper modelMapper;

    @Resource
    private UserRepository userRepository;

    @Override
    public UserEntity findUserByLoginId(String loginId) {
        Optional<UserEntity> optionalUser = userRepository.findByLoginId(loginId);
        return optionalUser.orElse(null);
    }

    @Override
    public Set<MenuDTO> getUserAuthList(String jws) {
        Set<MenuEntity> menuSet = new HashSet<>(16);
        UserEntity user = parseUser(jws);
        if (user != null) {
            Set<RoleEntity> roleList = user.getRoleList();
            for (RoleEntity role : roleList) {
                menuSet.addAll(role.getMenuList());
            }
        }
        PageConvert<MenuEntity, MenuDTO> pageConvert
                = new PageConvert<>(modelMapper, MenuDTO.class, new TypeToken<Set<MenuDTO>>() {
        }.getType());
        log.info("menuList size {}", menuSet.size());
        return pageConvert.convertDTOSet(menuSet);
    }

    private UserEntity parseUser(String jws) {
        Jws<Claims> userClaims = JwtUtils.parseJwt(jws);
        Long id = Long.parseLong(userClaims.getHeader().get("userId").toString());
        Optional<UserEntity> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
}
