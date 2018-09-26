package com.kanper.service.impl;

import com.kanper.bean.MenuBean;
import com.kanper.repository.IMenuRepository;
import com.kanper.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private IMenuRepository menuRepository;

    @Override
    public List<MenuBean> queryMenuByRole(Boolean isAdmin) {
        if (isAdmin) {
            return menuRepository.findAll();
        }
        return menuRepository.noneAdminMenus();
    }
}
