package com.kanper.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_goods")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@DynamicInsert
public class GoodsBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 商品归属二级类目
     */
    @ManyToOne
    private SecondCategory secondCategory;
    /**
     * 商品进价
     */
    private double buyPrice;
    /**
     * 商品售价
     */
    private double soldPrice;
    /**
     * 商品进货数量
     */
    private int number;
    /**
     * 商品购买时间
     */
    private Timestamp buyDate = new Timestamp(System.currentTimeMillis());
    /**
     * 商品供应商
     */
    @ManyToOne
    private SupplierBean supplier;
}
