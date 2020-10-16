package com.liuzhihang.demo.controller;

import com.liuzhihang.demo.annotion.CheckToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhihang
 * @date 2020/10/15 14:40
 */
@Slf4j
@RestController
@RequestMapping("/header")
public class HeaderDemoController {


    @CheckToken
    @PostMapping("/checkToken")
    public String checkToken() {

        return "success";
    }

    @PostMapping("/getAuthorizationByKey")
    public String getAuthorizationByKey(@RequestHeader("Authorization") String authorization) {

        log.info("获取 Authorization --->{}", authorization);

        return authorization;
    }

    @PostMapping("/getAuthorizationByMap")
    public String getAuthorizationByMap(@RequestHeader Map<String, String> map) {


        String authorization = map.get("authorization");

        log.info("获取 Authorization --->{}", authorization);

        return authorization;
    }


    @PostMapping("/getAuthorizationByMultiValueMap")
    public String getAuthorizationByMultiValueMap(@RequestHeader MultiValueMap<String, String> map) {

        List<String> authorization = map.get("Authorization");

        log.info("获取 Authorization --->{}", authorization);

        return "SUCCESS";
    }


    @PostMapping("/getAuthorizationByHeaders")
    public String getAuthorizationByHeaders(@RequestHeader HttpHeaders headers) {

        List<String> authorization = headers.get("Authorization");

        log.info("获取 Authorization --->{}", authorization);

        return "SUCCESS";
    }


    @PostMapping("/getAuthorizationByServlet")
    public String getAuthorizationByServlet(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");

        log.info("获取 Authorization --->{}", authorization);

        return authorization;
    }

}
