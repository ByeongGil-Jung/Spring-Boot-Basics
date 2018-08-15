package com.example.springbootsecurity.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-15
 * Time: 오후 2:30
 */
@Controller
public class HomeController {

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @GetMapping("/my")
    public String my() {
        return "my";
    }
}
