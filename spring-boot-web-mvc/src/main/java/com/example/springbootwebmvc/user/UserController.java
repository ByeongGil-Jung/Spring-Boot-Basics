package com.example.springbootwebmvc.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-06
 * Time: 오전 2:28
 */
@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
