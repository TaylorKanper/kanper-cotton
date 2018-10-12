package com.kanper.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_goods")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
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
     * 商品进货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(updatable = false)
    private Date buyDate;
    /**
     * 商品供应商
     */
    @ManyToOne
    private SupplierBean supplier;
    /**
     * 该商品的销售记录
     */
    @OneToMany(mappedBy = "goodsBean")
    @JsonIgnore
    private List<SoldGoodBean> soldGoodBeans;
}
