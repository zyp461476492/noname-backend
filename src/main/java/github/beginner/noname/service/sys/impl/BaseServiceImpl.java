package github.beginner.noname.service.sys.impl;

import github.beginner.noname.domain.entity.BaseEntity;
import github.beginner.noname.service.sys.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @author zyp on 2018-12-6.
 */
@Slf4j
@Service
public class BaseServiceImpl implements BaseService {

    /**
     * 判断entity对象是否为空
     *
     * @param entity baseEntity
     * @return true 如果为空
     */
    boolean isEmpty(BaseEntity entity) {
        if (entity == null)
            return true;
        Long id = entity.getId();
        return id == null;
    }

    /**
     * 调用entity的methodName的setter，设置对应字段的值
     *
     * @param obj        entity
     * @param fieldName  字段名称
     * @param paramType  参数类型
     * @param methodName setter方法名称
     * @param param      要设置的值
     */
    void setNull(Object obj, Class<?> paramType, String fieldName, String methodName, Object param) {
        try {
            Class<?> clazz = obj.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            BaseEntity entity = (BaseEntity) field.get(obj);
            Method setter = clazz.getMethod(methodName, paramType);
            if (isEmpty(entity)) {
                // 字段为null时，设置该对象为null
                setter.invoke(obj, param);
            }
        } catch (Exception e) {
            log.error("setNull 时发生异常，methodName为{}", methodName);
            e.printStackTrace();
        }
    }
}
