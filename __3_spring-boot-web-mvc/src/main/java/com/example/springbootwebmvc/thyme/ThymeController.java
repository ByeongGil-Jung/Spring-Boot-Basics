package com.example.springbootwebmvc.thyme;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-07
 * Time: 오전 2:20
 */
@Controller
public class ThymeController {

    // parameter 인 Model 은 데이터, 정보 등을 받을 받을 객체이다.
    // return 하는 String 값은 view 의 이름이다.
    // (@RestController 처럼 response 의 본문을 띄워주지 않는다.)
    @GetMapping("/thyme")
    public String thyme(Model model) {
        // Model 에 정보를 담아야 한다. -> Map 과 같이 사용한다.
        model.addAttribute("name", "Bread");
        return "thyme"; // view 의 이름
    }
}
