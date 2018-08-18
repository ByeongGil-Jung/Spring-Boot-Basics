package com.example.springbootrestclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-16
 * Time: 오후 2:30
 */
@RestController
public class SampleController {

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        // res 를 보내기 전에 5초 동안 쉬게 함
        Thread.sleep(5000L);
        return "hello";
    }

    @GetMapping("/world")
    public String world() throws InterruptedException {
        // res 를 보내기 전에 3초 동안 쉬게 함
        Thread.sleep(3000L);
        return "world";
    }
}
