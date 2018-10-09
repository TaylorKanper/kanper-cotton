package com.kanper.service;

import com.kanper.bean.SecondCategory;

import java.util.List;

public interface ISecondCategoryService {
    /**
     * 添加商品品类
     *
     * @param secondCategory
     * @return
     */
    SecondCategory add(SecondCategory secondCategory);

    /**
     * 查找所有商品品类
     *
     * @return
     */
    List<SecondCategory> allGoods();

    /**
     * 根据类目查找所有商品品类
     *
     * @param id 类目ID
     * @return
     */
    List<SecondCategory> getAllGoodsByFirstId(Long id);
}
