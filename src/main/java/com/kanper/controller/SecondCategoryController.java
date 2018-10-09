package com.kanper.controller;

import com.kanper.bean.SecondCategory;
import com.kanper.common.ActionResult;
import com.kanper.service.ISecondCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/secondCategory")
public class SecondCategoryController {
    @Autowired
    private ISecondCategoryService secondCategoryService;

    @PostMapping("/add")
    public ActionResult add(SecondCategory secondCategory) {

        SecondCategory secondCategory1 = secondCategoryService.add(secondCategory);
        if (secondCategory1 != null) {
            return ActionResult.success("添加商品品类" + secondCategory.getSecondCategoryName() + "成功", secondCategory1);
        }
        return ActionResult.fail("添加商品品类" + secondCategory.getSecondCategoryName() + "失败");
    }

    @GetMapping("/getAllGoods")
    public List<SecondCategory> getAllGoods() {
        return secondCategoryService.allGoods();
    }

    @GetMapping("/getAllGoodsByFirstId")
    public List<SecondCategory> getAllGoodsByFirstId(Long id) {
        return secondCategoryService.getAllGoodsByFirstId(id);
    }
}
