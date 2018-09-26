package com.kanper.service.impl;

import com.kanper.bean.UserBean;
import com.kanper.repository.IUserRepository;
import com.kanper.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserBean login(String userName, String passWord) {
        return userRepository.findUserBeanByUserNameAndPassWord(userName, passWord);
    }
}
