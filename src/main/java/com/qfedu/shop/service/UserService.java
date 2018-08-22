package com.qfedu.shop.service;

import com.qfedu.shop.pojo.User;

public interface UserService {
    //用户注册
    User regUser(User user);
    //检查唯一性
    //如果可用就是返回true。反之false。
    boolean checkInfo(String info,String type);

    //用户登录
    User loginUser(String loginInfo,String password);

    //用户信息修改
    User editUser(User user,String oldPassword);
}
