package github.beginner.noname.common;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

/**
 * @author zyp on 2019/1/10
 */
public class PageConvert<T, R> {
    private final ModelMapper modelMapper;
    private Class<R> dtoClazz;

    public PageConvert(ModelMapper modelMapper, Class<R> dtoClazz) {
        this.modelMapper = modelMapper;
        this.dtoClazz = dtoClazz;
    }

    public Page<R> convert(Page<T> entityPage) {
        return entityPage.map(this::convertDTO);
    }

    public R convertDTO(T entity) {
        return modelMapper.map(entity, dtoClazz);
    }
}
