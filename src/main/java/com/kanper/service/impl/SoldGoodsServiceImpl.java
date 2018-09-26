package com.kanper.service.impl;

import com.kanper.bean.SoldGoodBean;
import com.kanper.service.ISoldGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SoldGoodsServiceImpl implements ISoldGoodsService {
    @Override
    public List<SoldGoodBean> queryTodaySoldGoods() {
        return null;
    }
}
