package com.xhh.demo.spring.eureka.client.controller;

import com.xhh.demo.spring.eureka.client.api.CheckService;
import com.xhh.demo.spring.eureka.client.api.HealthCheckClient;
import com.xhh.demo.spring.eureka.client.api.HytrixHealthCheckClient;
import com.xhh.demo.spring.eureka.client.config.ApiUrls;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 7/26/16 4:13 PM
 */
@Api
@Log4j2
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthCheckController {

    @Autowired
    private HealthCheckClient healthCheckClient;

    @Autowired
    private HytrixHealthCheckClient hytrixHealthCheckClient;

    @Autowired
    private CheckService checkService;

    @RequestMapping("/info")
    public String hello() {
        return "success";
    }

    @RequestMapping("/")
    public String check1() {
        return checkService.check1();
    }

    @RequestMapping(value = ApiUrls.HEALTH_CHECK_V1, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "服务健康检查", tags = {ApiUrls.HEALTH_CHECK_V1})
    public Object check() {
        return "{\"status\":\"success\"}";
    }

    @RequestMapping(value = "/api/v1/getIpByHytrix", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取服务器 IP", tags = {"/api/v1/getIpByHytrix"})
    public Object getIpByHytrix() {
        return hytrixHealthCheckClient.getIp();
    }

    @RequestMapping(value = ApiUrls.APP_LOCAL_IP, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "获取服务器 IP", tags = {ApiUrls.APP_LOCAL_IP})
    public Object getIp() {
        return healthCheckClient.getIp();
    }
}
