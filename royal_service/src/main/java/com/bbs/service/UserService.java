package com.bbs.service;

import com.bbs.domain.User;

public interface UserService {

    /**
     * 用户登录的功能
     * @param userName
     * @param userPass
     * @return
     */
    public User findUserByuserNameAndpassWord(String userName, String userPass);

    User findUserByuserName(String userName);

    Boolean userRegist(User user);

    User manageLogin(User user);
}
