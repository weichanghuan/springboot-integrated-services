package com.springboot.rabbitmq.controller;

import com.springboot.rabbitmq.sender.MsgBaseProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MsgBaseProducer msgBaseProducer;

    @GetMapping(value = {"/","/index"})
    public String init(){
        return "ok";
    }

    @GetMapping("/sendMes")
    public String sendMes(String id){
        logger.info("id={}",id);
        msgBaseProducer.sendMsg(id);
        return "ok";
    }
}
