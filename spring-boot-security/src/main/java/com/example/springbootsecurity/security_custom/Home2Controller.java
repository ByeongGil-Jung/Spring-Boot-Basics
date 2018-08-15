package com.example.springbootsecurity.security_custom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-15
 * Time: 오후 9:17
 */
@Controller
public class Home2Controller {

    @GetMapping("/board2")
    public String board2() {
        return "board2";
    }

    @GetMapping("/my2")
    public String my2() {
        return "my2";
    }
}
