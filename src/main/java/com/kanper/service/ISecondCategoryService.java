package com.kanper.service;

import com.kanper.bean.SecondCategory;

import java.util.List;

public interface ISecondCategoryService {
    SecondCategory add(SecondCategory secondCategory);

    List<SecondCategory> allGoods();
}
