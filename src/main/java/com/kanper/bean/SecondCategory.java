package com.kanper.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_second_category")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SecondCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 二级类目名称
     */
    @Column(nullable=false ,unique=true)
    private String secondCategoryName;
    /**
     * 二级类目归属的一级类目
     */
    @ManyToOne
    private FirstCategoryBean firstCategory;
    /**
     * 二级类目的商品集合
     */
    @OneToMany(mappedBy = "secondCategory")
    @JsonIgnore
    private List<GoodsBean> goodsBeans;
    /**
     * 出售的商品集合
     */
    @OneToMany(mappedBy = "soldSecondCategory")
    @JsonIgnore
    private List<SoldGoodBean> soldGoodBeans;

    public SecondCategory(Long secondCategoryId) {
        this.id=secondCategoryId;
    }
}
