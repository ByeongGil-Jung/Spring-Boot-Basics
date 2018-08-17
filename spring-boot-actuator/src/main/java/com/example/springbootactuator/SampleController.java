package com.example.springbootactuator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-17
 * Time: 오후 4:46
 */
@RestController
public class SampleController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }
}
