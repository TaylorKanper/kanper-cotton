package com.kanper.service;

import com.kanper.bean.UserBean;

public interface IUserService {
    /**
     * 根据用户名和密码查询用户
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    UserBean login(String userName, String passWord);
}
