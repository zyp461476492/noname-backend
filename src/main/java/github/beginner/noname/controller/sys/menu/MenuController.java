package github.beginner.noname.controller.sys.menu;

import com.alibaba.fastjson.JSON;
import github.beginner.noname.common.PageConvert;
import github.beginner.noname.controller.BaseController;
import github.beginner.noname.domain.constant.MsgConstant;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.entity.sys.menu.MenuEntity;
import github.beginner.noname.domain.vo.sys.menu.MenuVO;
import github.beginner.noname.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
