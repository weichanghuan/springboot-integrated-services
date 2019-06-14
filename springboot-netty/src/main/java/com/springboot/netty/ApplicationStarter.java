package com.springboot.netty;

import com.springboot.netty.server.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.tools.jar.CommandLine;

/**
 * 启动类
 */
@SpringBootApplication(scanBasePackages = {"com.springboot.netty"})
public class ApplicationStarter implements CommandLineRunner {

    @Autowired
    private NettyServer nettyServer;


    private static final Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
        logger.info("ApplicationStarter is running");
    }


    @Override
    public void run(String... args) throws Exception {
    }
}
