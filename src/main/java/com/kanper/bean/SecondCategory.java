package com.kanper.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_second_category")
@Data
public class SecondCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 二级类目名称
     */
    private String secondCategoryName;
    /**
     * 二级类目归属的一级类目
     */
    @ManyToOne
    private FirstCategoryBean firstCategory;
    /**
     * 二级类目的商品集合
     */
    @OneToMany
    private List<GoodsBean> goodsBeans;
    /**
     * 出售的商品集合
     */
    @OneToMany
    private List<SoldGoodBean> soldGoodBeans;

}
