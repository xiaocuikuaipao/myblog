package com.usst.myblog.service;

import com.usst.myblog.pojo.TComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
public interface TCommentService  {

    int addComment(TComment comment);

    List<TComment> findComment(Long id);
}
