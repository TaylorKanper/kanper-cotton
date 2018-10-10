package com.kanper.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_first_category")
@Data
public class FirstCategoryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 商品大类名称
     */
    @Column(nullable=false ,unique=true)
    private String firstCategoryName;
    /**
     * 商品小类（商品具体名称）
     */
    @OneToMany
    @JoinTable(
            name="t_first_category_second_category_list",
            joinColumns=@JoinColumn(name="first_category_bean_id"),
            inverseJoinColumns=@JoinColumn(name="second_category_list_id")
    )
    private List<SecondCategory> secondCategoryList;

}
