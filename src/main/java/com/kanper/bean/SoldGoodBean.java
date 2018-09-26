package com.kanper.bean;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_sold_good")
@Data
public class SoldGoodBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 商品分类(大类)
     */
    @ManyToOne
    private SecondCategory secondCategory;
    /**
     *
     */
    private Integer soldNumber;
    /**
     * 商品价格
     */
    private double price;
    /**
     * 商品出售价格
     */
    private double soldPrice;
    /**
     * 商品折扣
     */
    private double discount;
    /**
     * 购买商品的会员
     */
    @ManyToOne
    private MemberBean memberBean;
    /**
     * 商品购买时间
     */
    private Date buyDate;

}
