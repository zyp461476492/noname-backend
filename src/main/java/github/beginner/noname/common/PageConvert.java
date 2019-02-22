package github.beginner.noname.common;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author zyp on 2019/1/10
 */
public class PageConvert<T, R> {
    private final ModelMapper modelMapper;
    private Class<R> dtoClazz;
    private Type destinationType;

    /**
     * entity 转换为 dto 的工具类
     * @param modelMapper modelMapper 实例对象
     * @param dtoClazz dto的class
     * @param destinationType List<dto>的Type对象，供list转换时使用
     */
    public PageConvert(ModelMapper modelMapper, Class<R> dtoClazz, Type destinationType) {
        this.destinationType = destinationType;
        this.modelMapper = modelMapper;
        this.dtoClazz = dtoClazz;
    }

    public Page<R> convert(Page<T> entityPage) {
        return entityPage.map(this::convertDTO);
    }

    public R convertDTO(T entity) {
        return modelMapper.map(entity, dtoClazz);
    }

    public List<R> convertDTOList(List<T> entityList) {
        return modelMapper.map(entityList, destinationType);
    }
}
