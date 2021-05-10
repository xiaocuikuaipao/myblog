package com.usst.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.usst.myblog.pojo.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<TUser> {
}
