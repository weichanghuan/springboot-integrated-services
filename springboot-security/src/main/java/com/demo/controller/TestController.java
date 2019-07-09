package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wch
 * @Description: 测试权限控制器
 * @Date: 2019-07-09 09:21
 */
@RestController
@RequestMapping("/demo")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
}
