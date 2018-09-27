package com.kanper.service.impl;

import com.kanper.bean.SupplierBean;
import com.kanper.repository.ISupplierRepository;
import com.kanper.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "supplier")
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private ISupplierRepository supplierRepository;

    @CacheEvict(allEntries = true)
    @Override
    public SupplierBean add(SupplierBean supplierBean) {
        return supplierRepository.save(supplierBean);
    }

    @Cacheable
    @Override
    public List<SupplierBean> allSuppliers() {
        return supplierRepository.findAll();
    }
}
