package com.kanper.service.impl;

import com.kanper.bean.GoodsBean;
import com.kanper.bean.MemberBean;
import com.kanper.bean.SecondCategory;
import com.kanper.bean.SoldGoodBean;
import com.kanper.common.Response;
import com.kanper.dto.GoodsItem;
import com.kanper.dto.ShoppingCar;
import com.kanper.repository.IGoodsRepository;
import com.kanper.repository.IMemberRepository;
import com.kanper.repository.ISoldGoodsRepository;
import com.kanper.service.ISoldGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class SoldGoodsServiceImpl implements ISoldGoodsService {
    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private ISoldGoodsRepository soldGoodsRepository;
    @Autowired
    private IMemberRepository memberRepository;

    @Override
    public List<SoldGoodBean> queryTodaySoldGoods() {
        return soldGoodsRepository.queryAllToday();
    }

    @Override
    public boolean buyOneItem(Long goodsId) {
        try {
            GoodsBean goodsBean = goodsRepository.getOne(goodsId);
            SoldGoodBean soldGoodBean = new SoldGoodBean();
            soldGoodBean.setGoodsBean(goodsBean);
            soldGoodBean.setSoldPrice(goodsBean.getSoldPrice());
            soldGoodBean.setDiscount(1L);
            soldGoodBean.setSoldSecondCategory(goodsBean.getSecondCategory());
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
            soldGoodBean.setGoodsBean(goodsBean);
            soldGoodBean.setSoldPrice(goodsBean.getSoldPrice());
            soldGoodBean.setDiscount(1L);
            soldGoodBean.setSoldSecondCategory(goodsBean.getSecondCategory());
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response<String> buyShoppingCar(ShoppingCar shoppingCar) throws Exception {
        int total = 0;
        for (GoodsItem goodsItem : shoppingCar.getGoodsItemList()) {
            GoodsBean goodsBean = goodsRepository.getOne(goodsItem.getGoodsId());
            if (goodsBean.getNumber() < goodsItem.getBuyNumber()) {
                throw new Exception("购买失败，商品" + goodsBean.getSecondCategory().getSecondCategoryName() + "库存不足");
            }
            SoldGoodBean soldGoodBean = new SoldGoodBean();
            soldGoodBean.setSoldNumber(goodsItem.getBuyNumber());
            soldGoodBean.setSoldSecondCategory(new SecondCategory(goodsItem.getSecondCategoryId()));
            soldGoodBean.setDiscount(goodsItem.getDiscount());
            soldGoodBean.setSoldPrice(goodsItem.getSoldPrice());
            soldGoodBean.setGoodsBean(goodsBean);
            if (shoppingCar.getMemberId() != null) {
                soldGoodBean.setMemberBean(new MemberBean(shoppingCar.getMemberId()));
            }
            soldGoodsRepository.save(soldGoodBean);
            if (goodsBean.getNumber() == goodsItem.getBuyNumber()) {
                goodsRepository.delete(goodsItem.getGoodsId());
            } else {
                goodsBean.setNumber(goodsBean.getNumber() - goodsItem.getBuyNumber());
                goodsRepository.save(goodsBean);
            }
            total += goodsItem.getBuyNumber() * goodsItem.getSoldPrice();
        }
        if (shoppingCar.getMemberId() != null) {
            MemberBean memberBean = memberRepository.getOne(shoppingCar.getMemberId());
            Integer s = memberBean.getIntegral();
            if (s == null) {
                s = 0;
            }
            memberBean.setIntegral(s + total);
            memberRepository.save(memberBean);
        }
        return Response.ok("购物车商品购买成功");
    }

    @Override
    public List<SoldGoodBean> querySoldGoodsByDate(Date queryDate) {
        return null;
    }
}
