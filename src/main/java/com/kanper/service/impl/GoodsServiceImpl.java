package com.kanper.service.impl;

import com.kanper.bean.GoodsBean;
import com.kanper.repository.IGoodsRepository;
import com.kanper.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private IGoodsRepository goodsRepository;


    @Override
    public List<GoodsBean> addBatch(List<GoodsBean> goodsBeanList) {
        return goodsRepository.save(goodsBeanList);
    }

    @Override
    public List<GoodsBean> getAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public GoodsBean updateGoods(GoodsBean goodsBean) {
        return goodsRepository.save(goodsBean);
    }

    @Override
    public List<GoodsBean> getAllGoodsBySecondCategoryId(Long secondCategoryId) {
        return goodsRepository.findAllBySecondCategory_Id(secondCategoryId);
    }

}
