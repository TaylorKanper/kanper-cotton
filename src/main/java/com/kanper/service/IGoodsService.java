package com.kanper.service;

import com.kanper.bean.GoodsBean;

import java.util.List;

public interface IGoodsService {
    List<GoodsBean> addBatch(List<GoodsBean> goodsBeanList);
}
