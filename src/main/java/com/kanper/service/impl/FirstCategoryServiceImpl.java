package com.kanper.service.impl;

import com.kanper.bean.FirstCategoryBean;
import com.kanper.repository.IFirstCategoryRepository;
import com.kanper.service.IFirstCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "firstCategory")
public class FirstCategoryServiceImpl implements IFirstCategoryService {
    @Autowired
    private IFirstCategoryRepository firstCategoryRepository;

    @Cacheable
    @Override
    public List<FirstCategoryBean> allCategory() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return firstCategoryRepository.findAll(sort);
    }

    @CacheEvict(allEntries = true)
    @Override
    public FirstCategoryBean add(FirstCategoryBean firstCategory) {
        FirstCategoryBean firstCategoryBean = firstCategoryRepository.save(firstCategory);
        return firstCategoryBean;
    }
}
