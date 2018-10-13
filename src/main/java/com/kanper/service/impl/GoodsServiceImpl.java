package com.kanper.service.impl;

import com.kanper.bean.GoodsBean;
import com.kanper.bean.SupplierBean;
import com.kanper.common.Response;
import com.kanper.repository.IGoodsRepository;
import com.kanper.repository.ISupplierRepository;
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
    @Autowired
    private ISupplierRepository supplierRepository;


    @Override
    public List<GoodsBean> addBatch(List<GoodsBean> goodsBeanList) {
        for (GoodsBean goodsBean : goodsBeanList) {
            SupplierBean supplierBean = supplierRepository.getOne(goodsBean.getSupplier().getId());
            supplierBean.setTransactionAmount(supplierBean.getTransactionAmount() + goodsBean.getNumber() * goodsBean.getBuyPrice());
            supplierRepository.save(supplierBean);
        }
        return goodsRepository.save(goodsBeanList);
    }

    @Override
    public List<GoodsBean> getAllGoods() {
        return goodsRepository.findAllByStatus(true);
    }

    @Override
    public GoodsBean updateGoods(GoodsBean goodsBean) {
        GoodsBean oldBean = goodsRepository.getOne(goodsBean.getId());
        double trans = (oldBean.getNumber() - goodsBean.getNumber()) * goodsBean.getBuyPrice();
        SupplierBean supplierBean = supplierRepository.getOne(goodsBean.getSupplier().getId());
        supplierBean.setTransactionAmount(supplierBean.getTransactionAmount() + trans);
        supplierRepository.save(supplierBean);
        return goodsRepository.save(goodsBean);
    }

    @Override
    public List<GoodsBean> getAllGoodsBySecondCategoryId(Long secondCategoryId) {
        return goodsRepository.findAllBySecondCategory_IdAndStatus(secondCategoryId, true);
    }

    @Override
    public List<GoodsBean> getAllGoodsByFirstCategoryId(Long firstCategoryId) {
        return goodsRepository.findAllBySecondCategory_FirstCategory_IdAndStatus(firstCategoryId, true);
    }

    @Override
    public Response<String> deleteGoods(Long goodsId) {
        try {
            GoodsBean goodsBean = goodsRepository.getOne(goodsId);
            SupplierBean supplierBean = supplierRepository.getOne(goodsBean.getSupplier().getId());
            supplierBean.setTransactionAmount(supplierBean.getTransactionAmount() - goodsBean.getNumber() * goodsBean.getBuyPrice());
            supplierRepository.save(supplierBean);
            goodsRepository.delete(goodsId);
            return Response.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.fail("删除失败", "发生未知错误不能删除");
    }

}
