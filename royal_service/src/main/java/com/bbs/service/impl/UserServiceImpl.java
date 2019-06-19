package com.bbs.service.impl;

import com.bbs.dao.UserDao;
import com.bbs.domain.User;
import com.bbs.domain.UserExample;
import com.bbs.service.UserService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public User manageLogin(User user) {
        try {
            UserExample example=new UserExample();
            example.createCriteria().andUsernameEqualTo(user.getUsername()).andUserpassEqualTo(user.getUserpass()).andRoleEqualTo(3);
            List<User> userTables = userDao.selectByExample(example);
            if (userTables.size()==0){
                return null;
            }else{
                return userTables.get(0);
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<User> findByPage(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtil.isNotEmpty(user.getUsername()))
            criteria.andUsernameLike("%"+user.getUsername().trim()+"%");
        if (user.getRole()!=null)
            criteria.andRoleEqualTo(user.getRole());
        userExample.setOrderByClause("role desc");
        List<User> userList = userDao.selectByExample(userExample);
        return userList;
    }

    @Override
    public void changeStatus(Integer id) {
        User user = userDao.selectByPrimaryKey(id);
        if (user.getTalkstatus()==0){
            user.setTalkstatus(1);
        }else if (user.getTalkstatus()==1){
            user.setTalkstatus(0);
        }
        userDao.updateByPrimaryKey(user);
    }

    @Override
    public User findById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }
}
