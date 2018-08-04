package com.example.springbootonlytest.__2_4_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-05
 * Time: 오전 2:52
 */

/*

- MOCK 을 사용하여 가상으로 servlet 과 interaction 할 수 있음 (내장 tomcat 구동 x)
(실제로 container 에서 구동되는 것이 아님)

 */
@RestController
public class MockController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/hello")
    public String hello() {
        return "world " + sampleService.getName();
    }
}
