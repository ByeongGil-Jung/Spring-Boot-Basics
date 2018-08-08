package com.example.springbootwebmvc.cors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-09
 * Time: 오전 12:25
 */
// REST API 를 제공하는 서버라고 가정한다.
@RestController
public class CorsController {

    // 밑의 origins 값에 CORS 를 지원할 Origin 을 적는다. (복수 입력 가능)
    @CrossOrigin(origins = {"http://localhost:18080"})
    @GetMapping("/cors")
    public String cors() {
        return "cors server side ~";
    }
}
