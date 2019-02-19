package github.beginner.noname.config.web.interceptor;

import github.beginner.noname.annotation.NotCheckJwt;
import github.beginner.noname.constant.CommonConstant;
import github.beginner.noname.exception.JwtVerifyException;
import github.beginner.noname.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token 验证拦截器
 * @author zyp on 2019/2/19
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法，直接通过，比如类级别的映射，按道理来说是错误地址
        log.info("url: {}", request.getRequestURL());
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获得class 级别的注解
        NotCheckJwt notCheckJwt = handlerMethod.getMethod().getAnnotation(NotCheckJwt.class);
        // 存在@NotCheckJwt注解，不需要验证jwt
        if (notCheckJwt != null) {
            return true;
        }

        String jws = request.getHeader(CommonConstant.USER_TOKEN);
        if (jws == null) {
            throw new JwtVerifyException("token is empty");
        }
        boolean res = JwtUtils.verifyJwt(jws);
        if (res) {
            return true;
        } else {
            throw new JwtVerifyException("access-deny");
        }
    }
}
