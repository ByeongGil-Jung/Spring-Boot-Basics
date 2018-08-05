package com.example.springbootwebmvc.user;

import org.springframework.web.bind.annotation.*;

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

    // HttpMessageConverter 사용
    @PostMapping("/users/create")
    /*
    public @ResponseBody User create(@RequestBody User user) {

    원래는 이 형식인데,
    @RestController 가 붙은 상황에서는,
    @ResponseBody 를 자동으로 인식해주기 때문에,

    생략해도 된다.
    */

    // 여기선 Json 을 받아서 JsonMessageConverter 를 사용한다고 가정할 것이다.
    public User create(@RequestBody User user) {
        return user;
    }
}
