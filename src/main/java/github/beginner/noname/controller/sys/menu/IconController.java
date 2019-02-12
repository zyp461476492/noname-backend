package github.beginner.noname.controller.sys.menu;

import com.alibaba.fastjson.JSON;
import github.beginner.noname.domain.constant.MsgConstant;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.entity.sys.menu.IconEntity;
import github.beginner.noname.domain.vo.sys.menu.IconTypeVO;
import github.beginner.noname.service.IconService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zyp on 2019/1/25
 */
@Slf4j
@RestController
@RequestMapping("/sys/icon")
@Api(value = "图标模块API")
public class IconController {
    private final IconService iconService;

    @Autowired
    public IconController(IconService iconService) {
        this.iconService = iconService;
    }

    @GetMapping(value = "/list")
    @ApiImplicitParam(name = "type", value = "icon类型", dataType = "String", example = "element")
    @ApiOperation(value = "查询指定类型的icon信息")
    public String getAllByType(@RequestParam(value = "type") String type) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        List<IconEntity> iconList = iconService.getAllByType(type);
        retMsg.setData(iconList);
        return JSON.toJSONString(retMsg);
    }

    @GetMapping(value = "/type")
    @ApiOperation(value = "查询图标对应的所有类型")
    public String getAllType() {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        List<IconTypeVO> typeList = iconService.getAllType();
        retMsg.setData(typeList);
        return JSON.toJSONString(retMsg);
    }
}
