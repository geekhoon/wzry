package com.bbs.dao;

import com.bbs.domain.User;
import com.bbs.domain.UserExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("Select * from bbs_user_table where userName = #{username} and userPass= #{userpass}")
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
    public User login(@Param("username")String userName, @Param("userpass")String userPass);

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
}
