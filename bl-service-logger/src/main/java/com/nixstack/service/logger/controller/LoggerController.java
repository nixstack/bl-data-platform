package com.nixstack.service.logger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {
    private  final static Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @PostMapping("/log")
    public void receiveStartupLog(@RequestParam("log") String log) {
        JSONObject jsonObject  = JSON.parseObject(log);

        if ("startup".equals(jsonObject.get("eventType"))) {
            logger.info(jsonObject.toJSONString());
        } else {
            //时间
            long millis = System.currentTimeMillis();
            //控制台打印
            logger.info(millis + "|" + jsonObject.toJSONString());
        }

    }
}
