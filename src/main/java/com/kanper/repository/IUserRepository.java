package com.kanper.repository;

import com.kanper.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserBean, Long> {
    /**
     * 根据用户名和密码查询用户
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    @Query("from UserBean p where p.userName=:userName and p.passWord=:passWord")
    UserBean findUserBeanByUserNameAndPassWord(@Param("userName") String userName,@Param("passWord") String passWord);
}
