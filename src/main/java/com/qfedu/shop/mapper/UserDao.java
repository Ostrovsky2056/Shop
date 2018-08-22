package com.qfedu.shop.mapper;

import com.qfedu.shop.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    //新增用户
    int insertUser(User user);
    //验证用户信息是否可用
    //检查用户名、邮箱、电话是否在数据库中已经存在
    //调用样例：checkInfo("zhangsan","username");
    int checkInfo(@Param("info")String info,@Param("type") String type);
    //查询用户
    User selectUser(@Param("loginInfo") String loginInfo,@Param("password") String password);

    int updateUser(User user);
}
