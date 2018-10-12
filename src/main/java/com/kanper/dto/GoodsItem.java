package com.kanper.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsItem implements Serializable {
    /**
     * 商品购买数量
     */
    private Integer buyNumber;
    /**
     * 商品折扣
     */
    private double discount;
    /**
     * 商品分类ID
     */
    private Long firstCategoryId;
    /**
     * 商品名称ID
     */
    private Long secondCategoryId;
    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 商品最终出售价格
     */
    private double soldPrice;
}
