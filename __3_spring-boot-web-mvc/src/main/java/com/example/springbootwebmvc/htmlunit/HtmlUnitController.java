package com.example.springbootwebmvc.htmlunit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-07
 * Time: 오후 2:41
 */
@Controller
public class HtmlUnitController {

    @GetMapping("/htmlunit")
    public String htmlU(Model model) {
        model.addAttribute("price", 8000);
        return "htmlunit";
    }
}
