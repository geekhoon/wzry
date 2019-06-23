package com.bbs.dao;

import com.bbs.domain.User;
import com.bbs.domain.UserExample;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserDao {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    void updateUserEmail(User user);

    User checkUserEmail(String email);

    void updateUserPicture(User user);

    User findUserInfo(User user);

    User findUserPicture(User userid);

    void updateLoginStatus(Map map);

    User checkUserPass(User user);

    void changeUserPass(User User);

    void updateLoginTime(User user);

    User login(User user);

    @Select("select * from bbs_user_table where userName = #{username}")
    @Results({
            @Result(id=true,property = "id",column="id"),
            @Result(property = "username",column="userName"),
            @Result(property = "userpass",column="userPass"),
            @Result(property = "email",column="email"),
            @Result(property = "picurl",column="picUrl"),
            @Result(property = "role",column="role"),
            @Result(property = "lastlogintime",column="lastLoginTime"),
            @Result(property = "loginstatus",column="loginStatus"),
            @Result(property = "talkstatus",column="talkStatus")
    })
    User findUserByName(String userName);

    @Insert("insert into bbs_user_table(userName,userPass,email,lastlogintime,talkstatus) " +
            "values(#{username},#{userpass},#{email},#{lastlogintime},#{talkstatus})")
    @Results({
            @Result(id=true,property = "id",column="id"),
            @Result(property = "username",column="userName"),
            @Result(property = "userpass",column="userPass"),
            @Result(property = "email",column="email"),
            @Result(property = "picurl",column="picUrl"),
            @Result(property = "role",column="role"),
            @Result(property = "lastlogintime",column="lastLoginTime"),
            @Result(property = "loginstatus",column="loginStatus"),
            @Result(property = "talkstatus",column="talkStatus")
    })
    int userRegist(User user);

    User findPicByName(User user);
}