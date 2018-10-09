package com.kanper.service;

import com.kanper.bean.SoldGoodBean;

import java.util.List;

public interface ISoldGoodsService {
    /**
     * 查询今天销售的所有商品
     *
     * @return
     */
    List<SoldGoodBean> queryTodaySoldGoods();

    /**
     * 购买一个商品，如果商品只有一个库存，自动删除，如果多于1个，减少库存，增加商品销售记录
     *
     * @param goodsId 商品id
     * @return
     */
    boolean buyOneItem(Long goodsId);

    /**
     * 批量购买商品，如果一个商品只有购买量的库存，自动删除，多于购买量，则减少库存，增加商品销售记录
     *
     * @param id          商品id
     * @param batchNumber 购买数量
     * @return
     */
    boolean batchBuy(Long id, int batchNumber);
}
