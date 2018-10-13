package com.kanper.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 供应商
 */
@Entity
@Table(name = "t_supplier")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SupplierBean implements Serializable {
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
    @Column(nullable = false, unique = true)
    private String supplierName;
    /**
     * 供应商发生的交易额
     */
    private double transactionAmount = 0.0F;
    /**
     * 供应商提供的商品
     */
    @OneToMany(mappedBy = "supplier")
    @JsonIgnore
    private List<GoodsBean> goodsBeans;

}
