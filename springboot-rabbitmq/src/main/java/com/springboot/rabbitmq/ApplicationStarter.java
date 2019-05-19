package com.springboot.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication(scanBasePackages = {"com.springboot.rabbitmq"})
public class ApplicationStarter {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
        logger.info("ApplicationStarter is running");
    }
}
