package com.usst.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.usst.myblog.pojo.TComment;
import com.usst.myblog.mapper.TCommentMapper;
import com.usst.myblog.service.TCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
@Service
public class TCommentServiceImpl  implements TCommentService {

    @Autowired
    TCommentMapper mapper;
    @Override
    public int addComment(TComment comment) {
        return mapper.insert(comment);
    }

    @Override
    public List<TComment> findComment(Long id) {
        QueryWrapper<TComment> w = new QueryWrapper<>();
        w.eq("blog_id",id);
        return mapper.selectList(w);
    }
}
