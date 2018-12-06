package github.beginner.noname.controller.sys.user;

import com.alibaba.fastjson.JSON;
import github.beginner.noname.controller.BaseController;
import github.beginner.noname.domain.dto.common.UpdateDTO;
import github.beginner.noname.domain.entity.sys.user.UserDO;
import github.beginner.noname.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author zyp on 2018-12-6.
 */
@RestController
@RequestMapping("/sys/user")
@Api(value = "用户模块API")
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/del/{id}")
    @ApiOperation(value = "用户删除", notes = "根据传入ID删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "1")
    public String deleteUser(@PathVariable("id") Long id) {
        return JSON.toJSONString(userService.deleteUserById(id));
    }

    @GetMapping(value = "/list/")
    @ApiOperation(value = "用户信息分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "页面", dataType = "Integer", example = "0"),
            @ApiImplicitParam(name = "limit", value = "每页大小", dataType = "Integer", example = "20")
    })
    public String queryUserList(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        return JSON.toJSONString(userService.findAll(PageRequest.of(offset, limit)));
    }

    @PostMapping(value = "/add/")
    @ApiOperation(value = "新增用户")
    public String addUser(@RequestBody UserDO user) {
        return JSON.toJSONString(userService.addUser(user));
    }

    @PostMapping(value = "/update/")
    @ApiOperation(value = "更新用户信息", notes = "传递更新信息和更新人数据")
    public String updateUser(@RequestBody UpdateDTO<UserDO> updateData) {
        return JSON.toJSONString(userService.updateUser(updateData.getData(), updateData.getUpdateBy()));
    }
}
