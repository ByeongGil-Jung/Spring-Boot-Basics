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

[ TestRestTemplate 를 사용할 때 ]

- RANDOM_PORT, DEFINED_PORT 을 사용하여 실제 내장 tomcat 으로 확인 할 수 있음
(하지만 RANDOM_PORT 가 더 좋음)

 */
@RestController
public class TestRestTemplateController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/world")
    public String world() {
        return "world " + sampleService.getName();
    }
}
