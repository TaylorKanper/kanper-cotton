package com.kanper.service.impl;

import com.kanper.bean.GoodsBean;
import com.kanper.bean.SoldGoodBean;
import com.kanper.repository.IGoodsRepository;
import com.kanper.repository.ISoldGoodsRepository;
import com.kanper.service.ISoldGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class SoldGoodsServiceImpl implements ISoldGoodsService {
    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private ISoldGoodsRepository soldGoodsRepository;

    @Override
    public List<SoldGoodBean> queryTodaySoldGoods() {
        return null;
    }

    @Override
    public boolean buyOneItem(Long goodsId) {
        try {
            GoodsBean goodsBean = goodsRepository.getOne(goodsId);
            SoldGoodBean soldGoodBean = new SoldGoodBean();
            soldGoodBean.setPrice(goodsBean.getBuyPrice());
            soldGoodBean.setSoldPrice(goodsBean.getSoldPrice());
            soldGoodBean.setDiscount(1L);
            soldGoodBean.setSecondCategory(goodsBean.getSecondCategory());
            soldGoodBean.setSoldNumber(1);
            soldGoodsRepository.save(soldGoodBean);
            if (goodsBean.getNumber() == 1) {
                goodsRepository.delete(goodsId);
            } else {
                goodsBean.setNumber(goodsBean.getNumber() - 1);
                goodsRepository.save(goodsBean);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean batchBuy(Long id, int batchNumber) {
        try {
            GoodsBean goodsBean = goodsRepository.getOne(id);
            SoldGoodBean soldGoodBean = new SoldGoodBean();
            soldGoodBean.setPrice(goodsBean.getBuyPrice());
            soldGoodBean.setSoldPrice(goodsBean.getSoldPrice());
            soldGoodBean.setDiscount(1L);
            soldGoodBean.setSecondCategory(goodsBean.getSecondCategory());
            soldGoodBean.setSoldNumber(batchNumber);
            soldGoodsRepository.save(soldGoodBean);
            if (goodsBean.getNumber() == batchNumber) {
                goodsRepository.delete(id);
            } else {
                goodsBean.setNumber(goodsBean.getNumber() - batchNumber);
                goodsRepository.save(goodsBean);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
