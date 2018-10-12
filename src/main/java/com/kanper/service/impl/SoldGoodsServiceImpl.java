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
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

            soldGoodBean.setSoldPrice(goodsBean.getSoldPrice());
            soldGoodBean.setDiscount(1L);
            soldGoodBean.setSoldSecondCategory(goodsBean.getSecondCategory());
            soldGoodBean.setSoldNumber(1);

            if (goodsBean.getNumber() == 1) {
                goodsBean.setNumber(0);
                goodsBean.setStatus(false);
            } else {
                goodsBean.setNumber(goodsBean.getNumber() - 1);
            }
            soldGoodBean.setGoodsBean(goodsBean);
            soldGoodsRepository.save(soldGoodBean);
            goodsRepository.save(goodsBean);
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
            soldGoodBean.setSoldPrice(goodsBean.getSoldPrice());
            soldGoodBean.setDiscount(1L);
            soldGoodBean.setSoldSecondCategory(goodsBean.getSecondCategory());
            soldGoodBean.setSoldNumber(batchNumber);
            if (goodsBean.getNumber() == batchNumber) {
                goodsBean.setNumber(0);
                goodsBean.setStatus(false);
            } else {
                goodsBean.setNumber(goodsBean.getNumber() - batchNumber);

            }
            soldGoodBean.setGoodsBean(goodsBean);
            soldGoodsRepository.save(soldGoodBean);
            goodsRepository.save(goodsBean);
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
            if (shoppingCar.getMemberId() != null) {
                soldGoodBean.setMemberBean(new MemberBean(shoppingCar.getMemberId()));
            }

            if (goodsBean.getNumber() == goodsItem.getBuyNumber()) {
                goodsBean.setNumber(0);
                goodsBean.setStatus(false);
            } else {
                goodsBean.setNumber(goodsBean.getNumber() - goodsItem.getBuyNumber());
            }
            soldGoodBean.setGoodsBean(goodsBean);
            soldGoodsRepository.save(soldGoodBean);
            goodsRepository.save(goodsBean);
            total += goodsItem.getBuyNumber() * goodsItem.getSoldPrice() * goodsItem.getDiscount();
        }
        BigDecimal bd = new BigDecimal(total).setScale(0, BigDecimal.ROUND_HALF_UP);
        if (shoppingCar.getMemberId() != null) {
            MemberBean memberBean = memberRepository.getOne(shoppingCar.getMemberId());
            Integer s = memberBean.getIntegral();
            if (s == null) {
                s = 0;
            }
            memberBean.setIntegral(s + bd.intValue());
            memberRepository.save(memberBean);
        }
        return Response.ok("购物车商品购买成功");
    }

    @Override
    public List<SoldGoodBean> querySoldGoodsByDate(Date queryDate) {
        DateTime dateTime = new DateTime(queryDate);
        Date s = dateTime.plusDays(1).toDate();
        return soldGoodsRepository.findAllByBuyDateBetween(queryDate, s);
    }

    @Override
    public Response<String> returnGoods(Long goodsId) {
        try {
            SoldGoodBean soldGoodBean = soldGoodsRepository.findOne(goodsId);
            // 获取关联的会员，扣除会员积分
            MemberBean m = soldGoodBean.getMemberBean();
            if (m != null) {// 进行会员积分扣减
                double reduceIntegral = soldGoodBean.getSoldNumber() * soldGoodBean.getDiscount() * soldGoodBean.getSoldPrice();
                BigDecimal bd = new BigDecimal(reduceIntegral).setScale(0, BigDecimal.ROUND_HALF_UP);
                m.setIntegral(m.getIntegral() - bd.intValue());
            }
            // 获取关联的商品，返回商品
            GoodsBean goodsBean = soldGoodBean.getGoodsBean();
            goodsBean.setNumber(goodsBean.getNumber() + soldGoodBean.getSoldNumber());
            if (!goodsBean.isStatus()) {
                goodsBean.setStatus(true);
            }
            goodsRepository.save(goodsBean);
            soldGoodsRepository.delete(goodsId);
            return Response.ok("商品" + soldGoodBean.getSoldSecondCategory().getSecondCategoryName() + "退货成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.fail("退货失败");
    }

    @Override
    public List<Map<String, Object>> findSoldNumberByCategory() {
        return null;
    }
}
