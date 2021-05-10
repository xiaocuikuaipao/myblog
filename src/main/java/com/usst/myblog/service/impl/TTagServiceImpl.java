package com.usst.myblog.service.impl;

import com.usst.myblog.pojo.TTag;
import com.usst.myblog.mapper.TTagMapper;
import com.usst.myblog.service.TTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TTagServiceImpl extends ServiceImpl<TTagMapper, TTag> implements TTagService {

    @Autowired
    TTagMapper tagMapper;

    /**
     * 添加一个tag
     * @param tagname
     * @return
     */
    @Override
    public int addtag(String tagname) {
        TTag tTag = new TTag();
        tTag.setName(tagname);
        return tagMapper.insert(tTag);
    }

    @Override
    public TTag findTagById(Long id) {
        return tagMapper.selectById(id);
    }

    @Override
    public int updataTag(Long id, String tagname) {
        TTag tag = new TTag();
        tag.setId(id);
        tag.setName(tagname);
        return tagMapper.updateById(tag);
    }

    @Override
    public int delTag(Long id) {
        return tagMapper.deleteById(id);
    }
}
