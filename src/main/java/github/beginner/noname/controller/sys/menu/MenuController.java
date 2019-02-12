package github.beginner.noname.controller.sys.menu;

import com.alibaba.fastjson.JSON;
import github.beginner.noname.common.PageConvert;
import github.beginner.noname.controller.BaseController;
import github.beginner.noname.domain.constant.MsgConstant;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.dto.common.UpdateDTO;
import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import github.beginner.noname.domain.vo.sys.menu.MenuVO;
import github.beginner.noname.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author zyp on 2019/1/18
 */
@Slf4j
@RestController
@RequestMapping("/sys/menu")
@Api(value = "菜单模块API")
public class MenuController extends BaseController {
    private PageConvert<MenuEntity, MenuVO> pageConvert;

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService, ModelMapper modelMapper) {
        this.menuService = menuService;
        this.pageConvert = new PageConvert<>(modelMapper, MenuVO.class,
                new TypeToken<List<MenuVO>>(){}.getType());
    }

    @GetMapping(value = "/root")
    @ApiOperation(value = "查询整颗菜单树")
    public String queryRoot() {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        List<MenuEntity> rootOrg = menuService.findRoot();
        List<MenuVO> voList = pageConvert.convertDTOList(rootOrg);
        retMsg.setData(voList);
        return JSON.toJSONString(retMsg);
    }

    @GetMapping(value = "/query/{id}")
    @ApiOperation(value = "查询角色", notes = "根据用户传入ID查询")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "1")
    public String queryRole(@PathVariable("id") Long id) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        Optional<MenuEntity> menuEntity = menuService.queryMenuById(id);

        if (menuEntity.isPresent()) {
            retMsg.setData(menuEntity.get());
        } else {
            retMsg.setFailResponse(MsgConstant.QUERY_FAIL);
        }
        return JSON.toJSONString(retMsg);
    }

    @PostMapping(value = "/add/")
    @ApiOperation("新增角色")
    public String addMenu(@RequestBody MenuEntity menu) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.ADD_SUCC);
        MenuVO menuVO = pageConvert.convertDTO(menuService.addMenu(menu));
        retMsg.setData(menuVO);
        return JSON.toJSONString(retMsg);
    }

    @DeleteMapping(value = "/del/{id}")
    @ApiOperation(value = "删除角色", notes = "根据用户传入ID")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "1")
    public String deleteRole(@PathVariable("id") Long id) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.DEL_SUCC);
        if (!menuService.deleteMenuById(id)) {
            retMsg.setFailResponse(MsgConstant.DEL_FAIL);
        }
        return JSON.toJSONString(retMsg);
    }

    @PutMapping(value = "/update/")
    @ApiOperation(value = "更新角色", notes = "传入更新信息和更新人ID")
    public String updateRole(@RequestBody UpdateDTO<MenuEntity> updateData) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.UPDATE_SUCC);
        MenuVO menuVO = pageConvert.convertDTO(menuService.updateMenu(updateData.getData()));
        retMsg.setData(menuVO);
        return JSON.toJSONString(retMsg);
    }
}
