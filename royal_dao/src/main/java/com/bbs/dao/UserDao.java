package com.bbs.dao;

import com.bbs.domain.User;
import org.apache.ibatis.annotations.*;

public interface UserDao {

    @Select("Select * from bbs_user_table where userName = #{userName} and userPass= #{userPass}")
    @Results({
            @Result(id=true,property = "id",column="id"),
            @Result(property = "userName",column="userName"),
            @Result(property = "userPass",column="userPass"),
            @Result(property = "email",column="email"),
            @Result(property = "picUrl",column="picUrl"),
            @Result(property = "role",column="role"),
            @Result(property = "lastLoginTime",column="lastLoginTime"),
            @Result(property = "loginStatus",column="loginStatus"),
            @Result(property = "talkStatus",column="talkStatus")
    })
    public User login(@Param("userName")String userName, @Param("userPass")String userPass);

    @Select("select * from bbs_user_table where userName = #{userName}")
    @Results({
            @Result(id=true,property = "id",column="id"),
            @Result(property = "userName",column="userName"),
            @Result(property = "userPass",column="userPass"),
            @Result(property = "email",column="email"),
            @Result(property = "picUrl",column="picUrl"),
            @Result(property = "role",column="role"),
            @Result(property = "lastLoginTime",column="lastLoginTime"),
            @Result(property = "loginStatus",column="loginStatus"),
            @Result(property = "talkStatus",column="talkStatus")
    })
    User findUserByName(String userName);

    @Insert("insert into bbs_user_table(userName,userPass,email) values(#{userName},#{userPass},#{email})")
    @Results({
            @Result(id=true,property = "id",column="id"),
            @Result(property = "userName",column="userName"),
            @Result(property = "userPass",column="userPass"),
            @Result(property = "email",column="email"),
            @Result(property = "picUrl",column="picUrl"),
            @Result(property = "role",column="role"),
            @Result(property = "lastLoginTime",column="lastLoginTime"),
            @Result(property = "loginStatus",column="loginStatus"),
            @Result(property = "talkStatus",column="talkStatus")
    })
    int userRegist(User user);
}
