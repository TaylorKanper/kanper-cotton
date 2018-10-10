package com.kanper.controller;

import com.kanper.common.ActionResult;
import com.kanper.common.Response;
import com.kanper.dto.ShoppingCar;
import com.kanper.service.ISoldGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/shoppingCarBuy")
    public ActionResult shoppingCarBuy(@RequestBody ShoppingCar shoppingCar) {
        try {
            Response<String> response = soldGoodsService.buyShoppingCar(shoppingCar);
            if (response.isOk()) {
                return ActionResult.success("购物车购买成功", response.getResult());
            }
            return ActionResult.fail(response.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ActionResult.fail(e.getMessage());
        }
    }
}