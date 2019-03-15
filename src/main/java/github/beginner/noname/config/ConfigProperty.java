package github.beginner.noname.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置属性类
 * @author zyp on 2019/2/22
 */
@PropertySource({"classpath:config.properties"})
@Component
public class ConfigProperty {
    @Value("${jwt.expiration}")
    public Long jwtExpiration;
}
