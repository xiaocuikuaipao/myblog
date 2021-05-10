package com.usst.myblog.service;

import com.usst.myblog.pojo.TTag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
public interface TTagService  {

    int addtag(String tagname);

    TTag findTagById(Long id);

    int updataTag(Long id, String tagname);

    int delTag(Long id);
}
