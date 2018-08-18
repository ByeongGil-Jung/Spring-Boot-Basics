package com.example.springbootwebmvc.thyme;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-07
 * Time: 오전 2:19
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ThymeController.class)
public class ThymeControllerTest {

    @Autowired
    MockMvc mockMvc;

    /*
    request : "/thyme"
    response
      - model name : Bread
      - view name : thyme
     */
    @Test
    public void thyme() throws Exception {
        mockMvc.perform(get("/thyme"))
                /*
                Thymeleaf 는 print() 를 통해 랜더링 된 결과를 볼 수 있다. (<html> ~ </html>)
                Thymeleaf 는 독자적으로 servlet 의 개입 없이 만들 수 있다.
                (JSP 는 불가능 함)
                */
                .andDo(print())

                .andExpect(status().isOk())
                // view 의 이름이 thyme 인가 ?
                .andExpect(view().name("thyme"))
                // model 의 data 중 {name: Bread} 가 있는가 ?
                // 밑의 is 는 org.hamcrest.Matchers.is 이다.
                .andExpect(model().attribute("name", is("Bread")))

                // <p th:text="${name}">Name</p> 으로 인해 Bread 가 있음을 알 수 있다.
                .andExpect(content().string(containsString("Bread")));
    }
}
