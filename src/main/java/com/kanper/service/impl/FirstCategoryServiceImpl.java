package com.kanper.service.impl;

import com.kanper.bean.FirstCategoryBean;
import com.kanper.repository.IFirstCategoryRepository;
import com.kanper.service.IFirstCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstCategoryServiceImpl implements IFirstCategoryService {
    @Autowired
    private IFirstCategoryRepository firstCategoryRepository;

    @Override
    public FirstCategoryBean add(FirstCategoryBean firstCategory) {
        FirstCategoryBean firstCategoryBean = firstCategoryRepository.save(firstCategory);
        return firstCategoryBean;
    }
}
