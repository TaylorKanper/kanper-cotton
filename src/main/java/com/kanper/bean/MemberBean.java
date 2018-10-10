package com.kanper.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_member")
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public class MemberBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 会员名
     */
    @Column(nullable = false)
    private String memberName;
    /**
     * 会员手机
     */
    @Column(nullable = false, unique = true)
    private String phone;
    /**
     * 会员积分
     */
    private Integer integral;
    /**
     * 会员生日
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM月dd日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date birthday;
    /**
     * 会员加入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(updatable = false)
    private Date createDate;
    /**
     * 会员购买的所有商品
     */
    @OneToMany(mappedBy = "memberBean")
    @JsonIgnore
    private List<SoldGoodBean> allPurchases;

    public MemberBean(Long memberId) {
        this.id = memberId;
    }
}
