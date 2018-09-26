package com.kanper.controller;

import com.kanper.bean.UserBean;
import com.kanper.common.ActionResult;
import com.kanper.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping
    public ActionResult login(String userName, String passWord) {
        UserBean userBean = userService.login(userName, passWord);
        return null;
    }
}
