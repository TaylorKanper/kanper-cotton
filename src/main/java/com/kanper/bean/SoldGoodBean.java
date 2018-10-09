package com.kanper.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_sold_good")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
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
     * 出售数量
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(updatable=false)
    private Date buyDate;

}
