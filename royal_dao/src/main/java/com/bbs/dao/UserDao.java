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
