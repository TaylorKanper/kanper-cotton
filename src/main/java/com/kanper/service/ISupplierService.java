package com.kanper.service;

import com.kanper.bean.SupplierBean;

import java.util.List;

public interface ISupplierService {
    /**
     * 添加供应商
     *
     * @param supplierBean
     * @return
     */
    SupplierBean add(SupplierBean supplierBean);

    /**
     * 查询所有供应商
     *
     * @return
     */
    List<SupplierBean> allSuppliers();
}
