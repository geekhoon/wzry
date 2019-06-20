package com.bbs.service;

import com.bbs.domain.User;

import java.util.List;

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

    /**
     * 后台管理登录
     * @param user
     * @return
     */
    User manageLogin(User user);

    /**
     * 条件查询
     * @param user
     * @return
     */
    List<User> findByPage(User user);

    /**
     * 修改用户禁言状态
     * @param id
     */
    void changeStatus(Integer id);

    /**
     * 根据用户id查询信息
     * @param id
     * @return
     */
    User findById(Integer id);
}
