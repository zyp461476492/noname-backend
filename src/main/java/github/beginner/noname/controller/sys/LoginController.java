package github.beginner.noname.controller.sys;

import com.alibaba.fastjson.JSON;
import github.beginner.noname.annotation.NotCheckJwt;
import github.beginner.noname.constant.MsgConstant;
import github.beginner.noname.controller.BaseController;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyp on 2019/2/19
 */
@Slf4j
@RestController
@RequestMapping("/login")
@Api(value = "登录，授权相关API")
public class LoginController extends BaseController {

    @NotCheckJwt
    @GetMapping(value = "/jwt/")
    @ApiOperation(value = "获取jwt", notes = "获取jwt-供测试用")
    public String queryRole() {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        retMsg.setData(JwtUtils.buildJws("lisi", 1L));
        return JSON.toJSONString(retMsg);
    }
}
