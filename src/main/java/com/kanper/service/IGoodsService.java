package com.kanper.service;

import com.kanper.bean.GoodsBean;
import com.kanper.common.Response;

import java.util.List;

public interface IGoodsService {
    /**
     * 批量添加商品记录
     *
     * @param goodsBeanList 商品记录集合
     * @return
     */
    List<GoodsBean> addBatch(List<GoodsBean> goodsBeanList);

    /**
     * 获取所有商品记录
     *
     * @return
     */
    List<GoodsBean> getAllGoods();

    /**
     * 修改某一个商品记录
     *
     * @param goodsBean 商品记录
     * @return
     */
    GoodsBean updateGoods(GoodsBean goodsBean);

    /**
     * 根据商品归属id查询所有商品
     *
     * @param secondCategoryId 商品ID
     * @return
     */
    List<GoodsBean> getAllGoodsBySecondCategoryId(Long secondCategoryId);

    /**
     * 根据商品类目ID查询所有商品
     *
     * @param firstCategoryId 商品类目ID
     * @return
     */
    List<GoodsBean> getAllGoodsByFirstCategoryId(Long firstCategoryId);

    /**
     * 删除商品
     * @param goodsId 商品ID
     * @return
     */
    Response<String> deleteGoods(Long goodsId);
}
