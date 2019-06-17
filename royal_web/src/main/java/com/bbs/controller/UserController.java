package com.bbs.controller;

import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/findIndex.do")
    public String findIndex(){
        return "index";
    }

    @RequestMapping("/findRegister.do")
    public String findRedister(){
        return "register";
    }

    @RequestMapping("/findUserByUserName.do")
    public @ResponseBody User findUserByUserName(String userName){
        User user = userService.findUserByuserName(userName);
        return user;
    }

    @RequestMapping(value = "/login.do" ,method = RequestMethod.POST)
    public String login(String userName, String userPass, HttpServletRequest request){
        User user = userService.findUserByuserNameAndpassWord(userName, userPass);
        if(user == null){
            //登录失败
            return "false";
        }else{
            //登录成功
            request.getSession().setAttribute("user",user);
            return "index";
        }
    }

    @RequestMapping(value = "/findUser.do" ,method = RequestMethod.POST)
    public @ResponseBody User findUser(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            return null;
        }else{
            return user;
        }
    }

    @RequestMapping(value = "/userRegist.do")
    public String userRegist(String userName,String userPass,String email){
//        System.out.println(userName+"---"+userPass+"---"+email);
        User user = new User();
        user.setUserName(userName);
        user.setUserPass(userPass);
        if ("".equals(email)){
            user.setEmail(null);
        }else{
            user.setEmail(email);
        }
        Boolean regist = userService.userRegist(user);

        if (regist){
            //注册成功

        }else{
            //注册失败
        }

        return null;
    }
}
