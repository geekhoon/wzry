package com.bbs.controller;

import com.bbs.common.CommonCode;
import com.bbs.common.ResponseResult;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/login.do")
    public ResponseResult checkLogin(@RequestBody User user){
        User loginUser=userService.manageLogin(user);
        if (loginUser==null){
            //验证失败
            return new ResponseResult(CommonCode.LOGINFAIL);
        }
        //验证成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

}
