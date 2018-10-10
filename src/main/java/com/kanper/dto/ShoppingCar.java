package com.kanper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCar implements Serializable {
    /**
     * 购物车内的商品集合
     */
    private List<GoodsItem> goodsItemList;
    /**
     * 会员ID
     */
    private Long memberId;
}
