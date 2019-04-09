package github.beginner.noname.controller.sys.org;

import com.alibaba.fastjson.JSON;
import github.beginner.noname.constant.MsgConstant;
import github.beginner.noname.controller.BaseController;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.dto.common.UpdateDTO;
import github.beginner.noname.domain.entity.sys.org.OrgEntity;
import github.beginner.noname.domain.vo.sys.org.OrgVO;
import github.beginner.noname.service.sys.OrgService;
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
 * @author zyp on 2018-12-6.
 */
@Slf4j
@RestController
@RequestMapping("/sys/org")
@Api(value = "组织机构模块API")
public class OrgController extends BaseController {
    private final OrgService orgService;

    private final ModelMapper modelMapper;

    @Autowired
    public OrgController(OrgService orgService, ModelMapper modelMapper) {
        this.orgService = orgService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/tree/root")
    @ApiOperation(value = "查询整棵树")
    public String queryRoot() {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        List<OrgEntity> rootOrg = orgService.findRoot();
        List<OrgVO> voList = list2VO(rootOrg);
        retMsg.setData(voList);
        return JSON.toJSONString(retMsg);
    }

    @GetMapping(value = "/tree/del/{id}")
    @ApiOperation(value = "删除树节点")
    @ApiImplicitParam(name = "id", value = "组织机构ID", required = true, dataType = "Long", example = "1")
    public String delOrgById(@PathVariable("id") Long id) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.DEL_SUCC);
        orgService.delOrg(id);
        return JSON.toJSONString(retMsg);
    }

    @GetMapping(value = "/tree/query/{id}")
    @ApiOperation(value = "组织机构信息查询", notes = "根据传入ID查询组织机构信息")
    @ApiImplicitParam(name = "id", value = "组织机构ID", required = true, dataType = "Long", example = "1")
    public String queryOrgById(@PathVariable("id") Long id) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        Optional<OrgEntity> optionalOrgEntity = orgService.findById(id);
        if (optionalOrgEntity.isPresent()) {
            retMsg.setData(optionalOrgEntity.get());
        } else {
            retMsg.setFailResponse(MsgConstant.QUERY_FAIL);
        }
        return JSON.toJSONString(retMsg);
    }


    @PostMapping(value = "/tree/add")
    @ApiOperation(value = "添加新节点")
    public String addOrgNode(@RequestBody OrgEntity entity) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.ADD_SUCC);
        OrgEntity orgEntity = orgService.addOrg(entity);
        retMsg.setData(orgEntity);
        return JSON.toJSONString(retMsg);
    }

    @PostMapping(value = "/tree/update")
    @ApiOperation(value = "更新组织机构节点")
    public String updateOrgNode(@RequestBody UpdateDTO<OrgEntity> updateData) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.ADD_SUCC);
        OrgEntity orgEntity = orgService.updateOrg(updateData.getData(), updateData.getUpdateBy());
        retMsg.setData(orgEntity);
        return JSON.toJSONString(retMsg);
    }

    private List<OrgVO> list2VO(List<OrgEntity> entityList) {
        return modelMapper.map(entityList,
                new TypeToken<List<OrgVO>>() {
                }.getType());
    }

}
