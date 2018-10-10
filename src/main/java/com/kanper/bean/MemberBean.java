package com.kanper.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "t_member")
@Data
@NoArgsConstructor
public class MemberBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 会员名
     */
    private String memberName;
    /**
     * 会员手机
     */
    private String phone;
    /**
     * 会员积分
     */
    private Integer integral;
    /**
     * 会员生日
     */
    private Date birthday;
    /**
     * 会员购买的所有商品
     */
    @OneToMany
    private List<SoldGoodBean> allPurchases;

    public MemberBean(Long memberId) {
        this.id=memberId;
    }
}
