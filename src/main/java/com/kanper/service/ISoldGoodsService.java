package com.kanper.service;

import com.kanper.bean.SoldGoodBean;

import java.util.List;

public interface ISoldGoodsService {
    List<SoldGoodBean> queryTodaySoldGoods();
}
