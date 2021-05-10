package com.usst.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.usst.myblog.mapper.UserMapper;
import com.usst.myblog.pojo.TUser;
import com.usst.myblog.service.UserService;
import com.usst.myblog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Override
    public int checkUserByNamePassword(String username, String password) {
        String code = MD5Utils.code(password);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        wrapper.eq("password",code);
        Integer count = userMapper.selectCount(wrapper);
        return count;
    }
}
