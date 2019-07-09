package com.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: wch
 * @Description: demo用户信息实体dto
 * @Date: 2019-07-09 16:05
 */
@Data
@Getter
@Setter
@ToString
public class DemoUserDTO implements Serializable {
    private String id;
    private String userEmail;
    private String loginName;
    private String password;
    private int status;
}
