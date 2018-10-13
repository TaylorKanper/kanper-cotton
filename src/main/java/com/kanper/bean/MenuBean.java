package com.kanper.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_menu")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MenuBean implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 菜单跳转链接
     */
    private String href;
    /**
     * 菜单图标集
     */
    private String icons;
    /**
     * 是否管理员菜单
     */
    private boolean admin;
    /**
     * 菜单名称
     */
    private String menuName;
}
