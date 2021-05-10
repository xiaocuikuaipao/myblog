package com.usst.myblog.service.impl;

import com.usst.myblog.pojo.TType;
import com.usst.myblog.mapper.TTypeMapper;
import com.usst.myblog.service.TTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
@Service
public class TTypeServiceImpl  implements TTypeService {

    @Autowired
    TTypeMapper mapper;

    /**
     * 插入一条type
     * @param typename
     * @return
     */
    @Override
    public int addtype(String typename) {
        TType tType = new TType();
        tType.setName(typename);
        int insert = mapper.insert(tType);
        return insert;
    }

    @Override
    public TType findTypeById(Integer id) {
        return mapper.selectById(id);
    }

    /**
     * 修改type
     * @param id
     * @param typename
     * @return
     */
    @Override
    public int editTypeById(Long id, String typename) {
        TType type = new TType();
        type.setName(typename);
        type.setId(id);
        return mapper.updateById(type);
    }

    @Override
    public int delTypeById(Long id) {
        return mapper.deleteById(id);
    }
}
