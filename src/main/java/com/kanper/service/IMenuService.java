package com.kanper.service;

import com.kanper.bean.MenuBean;

import java.util.List;

public interface IMenuService {
    /**
     * 根据管理员标识查询菜单
     *
     * @param isAdmin 是否管理员
     * @return
     */
    List<MenuBean> queryMenuByRole(Boolean isAdmin);
}
