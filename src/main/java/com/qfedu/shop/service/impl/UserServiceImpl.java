package com.qfedu.shop.service.impl;

import com.qfedu.shop.mapper.UserDao;
import com.qfedu.shop.pojo.User;
import com.qfedu.shop.service.UserService;
import com.qfedu.shop.utils.MessageDigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User regUser(User user) {
        //注册时，使用sha1方式保存密码摘要
        user.setPassword(MessageDigestUtils.sha1(user.getPassword()));
        int result=userDao.insertUser(user);
        if(result>0){
            return userDao.selectUser(user.getUsername(),user.getPassword());
        }
        return null;
    }

    @Override
    public boolean checkInfo(String info, String type) {
        int result=userDao.checkInfo(info,type);
        if(result==0){
            return true;
        }
        return false;
    }

    @Override
    public User loginUser(String loginInfo, String password) {
        //注册时，使用sha1方式保存密码摘要
        password=MessageDigestUtils.sha1(password);
        return userDao.selectUser(loginInfo,password);
    }

    @Override
    public User editUser(User user,String oldPassword) {
        //password是""或者null
        //意思是没有改变user的密码
        //如果有，改变user的密码
        String password=user.getPassword();
        if(password==null||"".equals(password)){
            user.setPassword(oldPassword);
        }else{
            password=MessageDigestUtils.sha1(password);
            user.setPassword(password);
        }
        int result=userDao.updateUser(user);
        if(result>0){
            return userDao.selectUser(user.getUsername(),user.getPassword());
        }

        return null;
    }
}
