package com.kanper.repository;

import com.kanper.bean.UserBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IUserRepositoryTest {
    @Autowired
    private IUserRepository userRepository;

    @Test
    public void findUserBeanByUserNameAndPassWord() {
        UserBean userBean = userRepository.findUserBeanByUserNameAndPassWord("kanper", "123456");
        System.out.println(userBean);
    }
}