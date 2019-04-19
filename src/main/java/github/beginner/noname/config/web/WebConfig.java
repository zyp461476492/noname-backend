package github.beginner.noname.config.web;

import github.beginner.noname.config.web.exception.WebExceptionResolver;
import github.beginner.noname.config.web.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zyp on 2019/2/19
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private JwtInterceptor jwtInterceptor;
    @Resource
    private WebExceptionResolver webExceptionResolver;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // HandlerInterceptor 和 WebRequestInterceptor 的不同
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/swagger-resources/**");
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(webExceptionResolver);
    }


}
