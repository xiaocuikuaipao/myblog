package com.usst.myblog.mapper;

import com.usst.myblog.pojo.TComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
@Repository
public interface TCommentMapper extends BaseMapper<TComment> {

}
