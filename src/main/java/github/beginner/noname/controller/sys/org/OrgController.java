package github.beginner.noname.controller.sys.org;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import github.beginner.noname.controller.BaseController;
import github.beginner.noname.domain.constant.CommonConstant;
import github.beginner.noname.domain.constant.MsgConstant;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.dto.common.UpdateDTO;
import github.beginner.noname.domain.dto.sys.user.UserDTO;
import github.beginner.noname.domain.entity.sys.org.OrgEntity;
import github.beginner.noname.domain.entity.sys.user.UserEntity;
import github.beginner.noname.domain.vo.sys.org.OrgVO;
import github.beginner.noname.service.OrgService;
import github.beginner.noname.service.UserService;
import github.beginner.noname.util.EncryptUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    @ApiOperation(value = "树根节点查询")
    public String queryRoot() {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        List<OrgEntity> rootOrg = orgService.findRoot();
        List<OrgVO> voList = list2VO(rootOrg);
        for (int i = 0; i < rootOrg.size(); i++) {
            OrgEntity parent = rootOrg.get(i);
            List<OrgEntity> childList = orgService.findChild(parent);
            List<OrgVO> childVO = list2VO(childList);
            voList.get(i).setChildren(childVO);
        }
        retMsg.setData(voList);
        return JSON.toJSONString(retMsg);
    }

    @PostMapping(value = "/tree/child")
    @ApiOperation(value = "孩子节点查询")
    public String queryChildByParent(@RequestBody OrgEntity parent) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        List<OrgEntity> orgList = orgService.findChild(parent);
        List<OrgVO> voList = modelMapper.map(orgList, new TypeToken<List<OrgVO>>() {
        }.getType());
        retMsg.setData(voList);
        return JSON.toJSONString(retMsg, SerializerFeature.DisableCircularReferenceDetect);
    }

    @PostMapping(value = "/tree/add")
    @ApiOperation(value = "添加新节点")
    public String addOrgNode(@RequestBody OrgEntity entity) {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.ADD_SUCC);
        OrgEntity orgEntity = orgService.addOrg(entity);
        retMsg.setData(orgEntity);
        return JSON.toJSONString(retMsg);
    }

    private List<OrgVO> list2VO(List<OrgEntity> entityList) {
        return modelMapper.map(entityList,
                new TypeToken<List<OrgVO>>() {
                }.getType());
    }

}
