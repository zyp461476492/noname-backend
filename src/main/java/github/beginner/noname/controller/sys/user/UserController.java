package github.beginner.noname.controller.sys.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import github.beginner.noname.common.PageConvert;
import github.beginner.noname.controller.BaseController;
import github.beginner.noname.domain.constant.CommonConstant;
import github.beginner.noname.domain.constant.MsgConstant;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.dto.common.UpdateDTO;
import github.beginner.noname.domain.dto.sys.user.UserDTO;
import github.beginner.noname.domain.entity.sys.user.UserEntity;
import github.beginner.noname.service.UserService;
import github.beginner.noname.util.EncryptUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 * @author zyp on 2018-12-6.
 */
@Slf4j
@RestController
@RequestMapping("/sys/user")
@Api(value = "用户模块API")
public class UserController extends BaseController {
    private final UserService userService;

    private final PageConvert<UserEntity, UserDTO> pageConvert;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        pageConvert = new PageConvert<>(modelMapper, UserDTO.class);
    }


    @GetMapping(value = "/list/")
    @ApiOperation(value = "用户信息分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "页面", dataType = "Integer", example = "0"),
            @ApiImplicitParam(name = "limit", value = "每页大小", dataType = "Integer", example = "20")
    })
    public String queryUserList(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        retMsg.setData(pageConvert.convert(userService.findAll(PageRequest.of(offset, limit))));
        return JSON.toJSONString(retMsg, SerializerFeature.DisableCircularReferenceDetect);
    }

    @GetMapping(value = "/query/{id}")
    @ApiOperation(value = "用户查询", notes = "根据传入ID查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "1")
    public String queryUserById(@PathVariable("id") Long id) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        Optional<UserEntity> optionalUserEntity = userService.queryUserById(id);
        if (optionalUserEntity.isPresent()) {
            retMsg.setData(pageConvert.convertDTO(optionalUserEntity.get()));
        } else {
            retMsg.setFailResponse(MsgConstant.QUERY_FAIL);
        }
        return JSON.toJSONString(retMsg);
    }

    @PostMapping(value = "/add/")
    @ApiOperation(value = "新增用户")
    public String addUser(@RequestBody UserEntity user) throws NoSuchAlgorithmException {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.ADD_SUCC);
        user.setPassword(EncryptUtils.generatePassword(user.getName(), CommonConstant.INITIALIZED_PASSWORD));
        UserDTO userDTO = pageConvert.convertDTO(userService.addUser(user));
        retMsg.setData(userDTO);
        return JSON.toJSONString(retMsg);
    }

    @PostMapping(value = "/update/")
    @ApiOperation(value = "更新用户信息", notes = "传递更新信息和更新人数据")
    public String updateUser(@RequestBody UpdateDTO<UserEntity> updateData) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.UPDATE_SUCC);
        UserDTO userDTO = pageConvert.convertDTO(userService.updateUser(updateData.getData(), updateData.getUpdateBy()));
        retMsg.setData(userDTO);
        return JSON.toJSONString(retMsg);
    }

    @GetMapping(value = "/del/{id}")
    @ApiOperation(value = "用户删除", notes = "根据传入ID删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "1")
    public String deleteUser(@PathVariable("id") Long id) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.DEL_SUCC);
        boolean delFlag = userService.deleteUserById(id);
        if (!delFlag) {
            retMsg.setFailResponse(MsgConstant.DEL_FAIL);
        }
        return JSON.toJSONString(retMsg);
    }

    @PostMapping(value = "/del/batch/")
    @ApiOperation(value = "批量删除用户", notes = "删除批量传入的用户信息")
    public String deleteBatchUser(@RequestBody List<UserEntity> userList) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.DEL_SUCC);
        userService.deleteBatchUser(userList);
        return JSON.toJSONString(retMsg);
    }





}
