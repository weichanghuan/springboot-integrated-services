package com.demo.service;

import com.demo.dto.DemoUserDTO;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * @Author: wch
 * @Description: 简单登录服务
 * @Date: 2019-07-09 16:08
 */
@Service
public class LoginService {

   public DemoUserDTO getUserByEmail(String username){
       if (Objects.equals("admin",username)){
           DemoUserDTO demoUserDTO=new DemoUserDTO();
           demoUserDTO.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
           demoUserDTO.setLoginName("admin");
           demoUserDTO.setPassword("111111");
           demoUserDTO.setUserEmail("527622102@q.com");
           //非禁用状态
           demoUserDTO.setStatus(0);
           return demoUserDTO;
       }
        return null;
    };
}
