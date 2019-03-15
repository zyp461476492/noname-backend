package github.beginner.noname.util;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * spring 获取上下文工具类
 * @author zyp on 2019/3/7
 */
public class SpringContextUtil {
    private static ConfigurableApplicationContext applicationContext = null;

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取 applicationContext
     * @return applicationContext
     */
    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据name获取bean
     * @param name bean名称
     * @return name对应的bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);

    }

    /**
     * 根据clazz获取bean
     * @param clazz bean class
     * @return clazz对应的bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据name和clazz获取bean
     * @param name bean名称
     * @param clazz bean class
     * @return clazz和name对应的bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
