package github.beginner.noname.controller.sys.role;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import github.beginner.noname.common.PageConvert;
import github.beginner.noname.constant.MsgConstant;
import github.beginner.noname.controller.BaseController;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.dto.common.UpdateDTO;
import github.beginner.noname.domain.dto.sys.user.RoleDTO;
import github.beginner.noname.domain.entity.sys.role.RoleEntity;
import github.beginner.noname.service.sys.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

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
 * @date 18/12/13 15:19
 */
@Slf4j
@RestController
@RequestMapping("/sys/role")
@Api(value = "角色模块API")
public class RoleController extends BaseController {

    private final RoleService roleService;
    private final PageConvert<RoleEntity, RoleDTO> pageConvert;

    @Autowired
    public RoleController(RoleService roleService, ModelMapper modelMapper) {
        this.roleService = roleService;
        pageConvert = new PageConvert<>(modelMapper, RoleDTO.class, new TypeToken<List<RoleDTO>>(){}.getType());
    }

    @PostMapping(value = "/add")
    @ApiOperation("新增角色")
    public String addUser(@RequestBody RoleEntity role) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.ADD_SUCC);
        RoleDTO roleDTO = pageConvert.convertDTO(roleService.addRole(role));
        retMsg.setData(roleDTO);
        return JSON.toJSONString(retMsg);
    }

    @DeleteMapping(value = "/del/{id}")
    @ApiOperation(value = "删除角色", notes = "根据用户传入ID")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "1")
    public String deleteRole(@PathVariable("id") Long id) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.DEL_SUCC);
        if (!roleService.deleteRoleById(id)) {
            retMsg.setFailResponse(MsgConstant.DEL_FAIL);
        }
        return JSON.toJSONString(retMsg);
    }

    @DeleteMapping(value = "/del/batch")
    @ApiOperation(value = "批量删除角色", notes = "批量删除用户传入信息")
    public String deleteBatchRole(@RequestBody List<RoleEntity> roleEntityList) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.DEL_SUCC);
        roleService.deleteBatchRole(roleEntityList);
        return JSON.toJSONString(retMsg);
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "更新角色", notes = "传入更新信息和更新人ID")
    public String updateRole(@RequestBody UpdateDTO<RoleEntity> updateData) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.UPDATE_SUCC);
        RoleDTO roleDTO = pageConvert.convertDTO(roleService.updateRole(updateData.getData(), updateData.getUpdateBy()));
        retMsg.setData(roleDTO);
        return JSON.toJSONString(retMsg);
    }

    @GetMapping(value = "/query/{id}")
    @ApiOperation(value = "查询角色", notes = "根据用户传入ID查询")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "1")
    public String queryRole(@PathVariable("id") Long id) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        Optional<RoleEntity> roleEntity = roleService.queryRoleById(id);
        if (roleEntity.isPresent()) {
            retMsg.setData(roleEntity.get());
        } else {
            retMsg.setFailResponse(MsgConstant.ROLE_QUERY_FAIL);
        }
        return JSON.toJSONString(retMsg, SerializerFeature.DisableCircularReferenceDetect);
    }

    @GetMapping(value = "/list/")
    @ApiOperation(value = "角色列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "页面数", dataType = "Integer", example = "0"),
            @ApiImplicitParam(name = "limit", value = "页面大小", dataType = "Integer", example = "10")
    })
    public String listRole(@RequestParam(value = "offset", defaultValue = "20") Integer offset,
                           @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        retMsg.setData(pageConvert.convert(roleService.findAll(PageRequest.of(offset, limit))));
        return JSON.toJSONString(retMsg);

    }

    @GetMapping(value = "/all/")
    @ApiOperation(value = "查询所有的角色信息")
    public String allRole() {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        retMsg.setData(pageConvert.convertDTOList(roleService.queryAllRole()));
        return JSON.toJSONString(retMsg);

    }
}
