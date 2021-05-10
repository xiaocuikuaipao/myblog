package com.usst.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.usst.myblog.pojo.TUser;

public interface UserService  {

    int checkUserByNamePassword(String username, String password);
}
