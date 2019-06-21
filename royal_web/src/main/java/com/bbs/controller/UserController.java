package com.bbs.controller;

import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/userLogin.do")
    @ResponseBody
    public String userLogin(@Param("username")String username, @Param("userPass")String userpass, HttpServletRequest request, Model model){
        User user = new User();
        user.setUsername(username);
        user.setUserpass(userpass);
        User u = userService.login(user);
        if(u == null){
            //登录失败
            return "false";
        }else{
            //登录成功
            request.getSession().setAttribute("username",u.getUsername());
            request.getSession().setAttribute("user",u);
            return "true";
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
    public @ResponseBody User userRegist(String username,String userpass,String email,HttpServletRequest request,RedirectAttributes redirectAttributes){
        User user = new User();
        user.setUsername(username);
        user.setUserpass(userpass);
        if ("".equals(email)){
            user.setEmail(null);
        }else{
            user.setEmail(email);
        }
        Boolean regist = userService.userRegist(user);

        if (regist){
            //注册成功
            request.getSession().setAttribute("user",user);
            return user;
        }else{
            //注册失败
            return null;
        }
    }
}
