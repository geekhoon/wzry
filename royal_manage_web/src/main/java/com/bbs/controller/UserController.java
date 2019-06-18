package com.bbs.controller;

import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @ResponseBody
//    @RequestMapping("/login.do")
//    public ResponseResult checkLogin(@RequestBody User user){
//        BbsUserTable loginUser=userService.checkLogin(user);
//        if (loginUser==null){
//            //验证失败
//            return new Result(false,null);
//        }
//        //验证成功
//        return new ResponseResult(CommonCode.SUCCESS);
//    }

}
