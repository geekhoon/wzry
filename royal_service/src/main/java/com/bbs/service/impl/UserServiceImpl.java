package com.bbs.service.impl;

import com.bbs.dao.UserDao;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByuserNameAndpassWord(String userName, String userPass) {
        User user = userDao.login(userName, userPass);
        return user;
    }

    @Override
    public User findUserByuserName(String userName) {
        User user = userDao.findUserByName(userName);
        return user;
    }

    @Override
    public Boolean userRegist(User user) {
        int num = userDao.userRegist(user);
        if (num > 0){
            return true;
        }
        return false;
    }
}
