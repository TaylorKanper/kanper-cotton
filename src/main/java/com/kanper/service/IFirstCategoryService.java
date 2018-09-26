package com.kanper.service;

import com.kanper.bean.FirstCategoryBean;

import java.util.List;

public interface IFirstCategoryService {
    /**
     * 添加类目
     *
     * @param firstCategory 类目
     * @return
     */
    FirstCategoryBean add(FirstCategoryBean firstCategory);

    /**
     * 查询所有类目
     *
     * @return
     */
    List<FirstCategoryBean> allCategory();
}
