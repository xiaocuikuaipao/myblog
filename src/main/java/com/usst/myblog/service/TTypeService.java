package com.usst.myblog.service;

import com.usst.myblog.pojo.TType;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
public interface TTypeService  {

    int addtype(String typename);

    TType findTypeById(Integer id);

    int editTypeById(Long id, String typename);

    int delTypeById(Long id);
}
