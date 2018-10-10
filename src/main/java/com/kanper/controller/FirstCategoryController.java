package com.kanper.controller;

import com.kanper.annotation.Authorization;
import com.kanper.bean.FirstCategoryBean;
import com.kanper.common.ActionResult;
import com.kanper.service.IFirstCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/firstCategory")
public class FirstCategoryController {
    @Autowired
    private IFirstCategoryService firstCategoryService;

    @PostMapping("/add")
    @Authorization
    public ActionResult add(FirstCategoryBean firstCategoryBean) {
        FirstCategoryBean firstCategoryBean1 = firstCategoryService.add(firstCategoryBean);
        if (firstCategoryBean1 != null) {
            return ActionResult.success("添加分类" + firstCategoryBean.getFirstCategoryName() + "成功", firstCategoryBean1);
        }
        return ActionResult.fail("添加分类" + firstCategoryBean.getFirstCategoryName() + "失败");
    }

    @GetMapping("/getAllCategory")
    public List<FirstCategoryBean> getAllCategory() {
        return firstCategoryService.allCategory();
    }
}
