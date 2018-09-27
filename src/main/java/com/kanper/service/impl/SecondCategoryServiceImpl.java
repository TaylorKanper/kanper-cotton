package com.kanper.service.impl;

import com.kanper.bean.SecondCategory;
import com.kanper.repository.ISecondCategoryRepository;
import com.kanper.service.ISecondCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "secondCategory")
public class SecondCategoryServiceImpl implements ISecondCategoryService {
    @Autowired
    private ISecondCategoryRepository secondCategoryRepository;

    @Cacheable
    @Override
    public SecondCategory add(SecondCategory secondCategory) {
        return secondCategoryRepository.save(secondCategory);
    }

    @CacheEvict(allEntries = true)
    @Override
    public List<SecondCategory> allGoods() {
        Sort sort = new Sort(Sort.Direction.ASC, "firstCategory", "id");
        return secondCategoryRepository.findAll(sort);
    }
}
