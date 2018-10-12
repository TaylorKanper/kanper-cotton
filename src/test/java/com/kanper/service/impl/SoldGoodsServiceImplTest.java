package com.kanper.service.impl;

import com.kanper.bean.SoldGoodBean;
import com.kanper.service.ISoldGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SoldGoodsServiceImplTest {
    @Autowired
    private ISoldGoodsService soldGoodsService;

    @Test
    public void querySoldGoodsByDate() {
        Date date = new Date();
        List<SoldGoodBean> result = soldGoodsService.querySoldGoodsByDate(date);
        System.out.println(result);
    }
}