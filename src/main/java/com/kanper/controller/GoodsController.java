package com.kanper.controller;

import com.kanper.bean.GoodsBean;
import com.kanper.common.ActionResult;
import com.kanper.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    @PostMapping("/batchAdd")
    public ActionResult batchAdd(@RequestBody GoodsBean[] goodsBeans) {
        List<GoodsBean> rest = goodsService.addBatch(Arrays.asList(goodsBeans));
        if (!rest.isEmpty()) {
            return ActionResult.success("批量添加成功", rest);
        }
        return ActionResult.fail("批量添加失败");
    }
}
