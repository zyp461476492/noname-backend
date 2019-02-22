package github.beginner.noname.config.web.exception;

import com.alibaba.fastjson.JSON;
import github.beginner.noname.constant.CodeConstant;
import github.beginner.noname.domain.dto.common.ResponseMsg;
import github.beginner.noname.exception.JwtVerifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zyp on 2019/2/19
 */
@Slf4j
@Component
public class WebExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        ResponseMsg responseMsg = new ResponseMsg();
        if (ex instanceof JwtVerifyException) {
            resolverJwtException(ex, responseMsg);
        } else {
            log.debug("请求时发生异常 {} ", ex.getMessage(), ex);
            ex.printStackTrace();
            resolverOtherException(ex, responseMsg);
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            response.getWriter().write(JSON.toJSONString(responseMsg));
        } catch (IOException e) {
            log.error("与客户端通讯异常：{}", e.getMessage(), e);
            e.printStackTrace();
        }
        return new ModelAndView();
    }

    private void resolverJwtException(Exception ex, ResponseMsg resMsg) {
        JwtVerifyException jwtVerifyException = (JwtVerifyException) ex;
        resMsg.setCode(CodeConstant.JWT_VERIFY_CODE);
        resMsg.setMsg(jwtVerifyException.getMsg());
    }

    private void resolverOtherException(Exception ex, ResponseMsg resMsg) {
        resMsg.setCode(CodeConstant.FAIL_CODE);
        resMsg.setMsg(ex.getMessage());
    }
}
