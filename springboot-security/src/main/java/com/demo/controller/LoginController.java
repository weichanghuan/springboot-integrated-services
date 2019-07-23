package com.demo.controller;

import com.demo.response.APIResponse;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wch
 * @Description: 登录控制器
 * @Date: 2019-07-09 09:21
 */
@RestController
@RequestMapping("/demo")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation("login")
    @PostMapping("/login")
    public <K, V> ResponseEntity<APIResponse<Map<K, V>>> doLogin(@RequestBody Map<K, V> request) {

        // /api/v1/login，本处作为swagger api的展示
        // 具体处理会由前置filter处理，实际并不会进入此方法
        Map map = new HashMap();
        map.put("reslut", "OK");
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.okay(map));
    }


}
