package com.kanper.controller;

import com.kanper.annotation.Authorization;
import com.kanper.bean.SupplierBean;
import com.kanper.common.ActionResult;
import com.kanper.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @PostMapping("/add")
    @Authorization
    public ActionResult add(SupplierBean supplierBean) {
        SupplierBean supplierBean1 = supplierService.add(supplierBean);
        if (supplierBean1 != null) {
            return ActionResult.success("添加供应商" + supplierBean.getSupplierName() + "成功", supplierBean1);
        }
        return ActionResult.fail("添加供应商"+supplierBean.getSupplierName()+"失败");
    }

    @GetMapping("/allSuppliers")
    public List<SupplierBean> allSuppliers() {
        return supplierService.allSuppliers();
    }
}
