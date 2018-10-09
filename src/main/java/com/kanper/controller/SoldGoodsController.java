package com.kanper.controller;

import com.kanper.common.ActionResult;
import com.kanper.service.ISoldGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/soldGoods")
@Slf4j
public class SoldGoodsController {
    @Autowired
    private ISoldGoodsService soldGoodsService;

    @PostMapping("/buyItem")
    public ActionResult buyItem(Long goodsId) {
        boolean buySuccess = soldGoodsService.buyOneItem(goodsId);
        if (buySuccess) {
            return ActionResult.success("商品购买成功");
        }
        return ActionResult.fail("商品购买失败");
    }

    @PostMapping("/batchBuy")
    public ActionResult batchBuy(Long id, int batchNumber) {
        boolean buySuccess = soldGoodsService.batchBuy(id, batchNumber);
        if (buySuccess) {
            return ActionResult.success("批量购买商品成功");
        }
        return ActionResult.fail("批量购买商品失败");
    }
}
