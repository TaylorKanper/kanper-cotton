package com.kanper.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_first_category")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirstCategoryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 商品大类名称
     */
    @Column(nullable = false, unique = true)
    private String firstCategoryName;
    /**
     * 商品小类（商品具体名称）
     */
    @OneToMany(mappedBy = "firstCategory")
    @JsonIgnore
    private List<SecondCategory> secondCategoryList;

}
