package com.kanper.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String passWord;
    private String phone;
    private boolean isAdmin;
    private String nickName;
}
