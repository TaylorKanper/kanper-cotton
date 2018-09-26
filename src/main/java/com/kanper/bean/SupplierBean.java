package com.kanper.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 供应商
 */
@Entity
@Table(name = "t_supplier")
@Data
public class SupplierBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 供应商电话
     */
    private String phone;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 供应商发生的交易额
     */
    private double transactionAmount = 0.0F;
    /**
     * 供应商提供的商品
     */
    @OneToMany
    private List<GoodsBean> goodsBeans;

}
