package github.beginner.noname.controller.sys;

import com.alibaba.fastjson.JSON;
import github.beginner.noname.annotation.NotCheckJwt;
import github.beginner.noname.constant.CodeConstant;
import github.beginner.noname.constant.MsgConstant;
import github.beginner.noname.controller.BaseController;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.domain.entity.sys.user.UserEntity;
import github.beginner.noname.domain.vo.sys.LoginVO;
import github.beginner.noname.service.sys.SysService;
import github.beginner.noname.util.EncryptUtils;
import github.beginner.noname.util.JwtUtils;
import github.beginner.noname.util.SpringContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @author zyp on 2019/2/19
 */
@Slf4j
@RestController
@RequestMapping("/login")
@Api(value = "登录，授权相关API")
public class LoginController extends BaseController {
    @Resource
    private SysService sysService;

    @NotCheckJwt
    @GetMapping(value = "/jwt/")
    @ApiOperation(value = "获取jwt", notes = "获取jwt-供测试用")
    public String queryRole() {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        retMsg.setData(JwtUtils.buildJws("lisi", 1L));
        return JSON.toJSONString(retMsg);
    }

    @NotCheckJwt
    @GetMapping(value = "/refresh/")
    @ApiOperation(value = "刷新系统缓存", notes = "供测试用")
    public String refresh() {
        ResponseMsg retMsg = ResponseMsg.succMsg(MsgConstant.QUERY_SUCC);
        SpringContextUtil.getApplicationContext().refresh();
        return JSON.toJSONString(retMsg);
    }

    @NotCheckJwt
    @PostMapping(value = "valid")
    @ApiOperation(value = "登录校验", notes = "登录成功，返回jwt")
    public String loginValid(@RequestBody LoginVO loginReq) throws NoSuchAlgorithmException {
        ResponseMsg resMsg = new ResponseMsg();
        resMsg.setCode(CodeConstant.FAIL_CODE);
        resMsg.setMsg("登录验证失败");
        UserEntity visitor = sysService.findUserByLoginId(loginReq.getLoginId());
        if (visitor != null) {
            // 校验密码
            String encrypt = EncryptUtils.generatePassword(loginReq.getLoginId(), loginReq.getPwd());
            log.info("encrypt---{}", encrypt);
            boolean res = encrypt.equals(visitor.getPassword());
            if (res) {
                // 登录成功放入 jwt 和角色菜单信息
                HashMap<String, Object> resMap = new HashMap<>();
                String jws = JwtUtils.buildJws(visitor.getLoginId(), visitor.getId());
                resMap.put("jwt", jws);
                resMap.put("menuList", sysService.getUserAuthList(jws));
                resMsg.setCode(CodeConstant.SUCCESS_CODE);
                resMsg.setMsg("登录验证成功");
                resMsg.setData(resMap);
            }
        }
        return JSON.toJSONString(resMsg);
    }
}
