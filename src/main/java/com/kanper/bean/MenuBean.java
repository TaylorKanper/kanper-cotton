package com.kanper.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_menu")
@Data
public class MenuBean {
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
