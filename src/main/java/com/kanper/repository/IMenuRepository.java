package com.kanper.repository;

import com.kanper.bean.MenuBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuRepository extends JpaRepository<MenuBean, Long> {
    @Query("from MenuBean m where m.admin=false ")
    List<MenuBean> noneAdminMenus();
}
