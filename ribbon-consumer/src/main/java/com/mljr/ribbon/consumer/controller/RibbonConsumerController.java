package com.mljr.ribbon.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.List;

/**
 * Author:daoyuanXiong
 * Description:
 * Date:Created in 上午10:12 2017/8/16
 * Copyright (c)  daoyuan.xiong@mljr.com All Rights Reserved.
 */
@RestController
public class RibbonConsumerController {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 项目说明
     * @return
     */
    @GetMapping({"/",""})
    public String index(){
        return "this is spring-cloud-ribbon-consumer";
    }

    /**
     * 远程服务ribbon rest
     * @return
     */
    @GetMapping( "/index")
    public Object ribbonIndex() {
        String str= restTemplate.getForEntity("http://spring-cloud-eureka-provider/index", String.class).getBody();
        System.out.println("<<<<<<<<<<<<<provider:"+str+">>>>>>>>>>>>>>>>>");
        return str;
    }
}
