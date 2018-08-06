package com.example.springbootwebmvc.pizza;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * Pizza: ByeongGil Jung
 * Date: 2018-08-06
 * Time: 오전 2:23
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PizzaController.class)
public class PizzaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void createUser_JSON() throws Exception {
        // String userJson = "{'username':'Pizza', 'price':'5000'}";
        // 작은 따옴표는 에러남
        String userJson = "{\"name\":\"Pizza\", \"price\":5000}";
        mockMvc.perform(post("/pizzas/create")

                // [ request 를 만드는 단계 ]
                // request 의 contentType 는 어떤 형태인가 ?
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                // (주면 좋은 값) response 는 어떤 형태의 contentType 인가 ?
                .accept(MediaType.APPLICATION_JSON_UTF8)
                // response 의 body 에는 무엇을 넣을 것인가 ?
                .content(userJson))

                    // [ response 를 확인하는 단계 ]
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(equalTo("Pizza"))))
                    .andExpect(jsonPath("$.price", is(equalTo(5000))));
    }

    /*
    View Resolver 를 통해
    request : JSON, response : XML
    로 변환하는 Controller 를 만든다고 가정하면 ?

    ---------------------------------------------------------

    여기서, 위와 같은 방법으로 실행하면 406 error 가 발생한다.
    -> 기본 내장엔, XML message 로 converting 할 수 있는 MessageConverter 가 없기 때문이다.

    >> jackson-dataformat-xml 을 의존성으로 주입해야 한다.
    compile('com.fasterxml.jackson.dataformat:jackson-dataformat-xml')
     */
    @Test
    public void createUser_XML() throws Exception {
        String userJson = "{\"name\":\"Pizza\", \"price\":5000}";
        mockMvc.perform(post("/pizzas/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_XML)
                .content(userJson))
                    .andExpect(status().isOk())
                    .andExpect(xpath("/Pizza/name").string("Pizza"))
                    // 밑의 것도 된다. -> 즉, int 로 들어온 json 값도 String 으로 형변환 해준다는 얘기
                    // .andExpect(xpath("/Pizza/price").string("5000"));
                    .andExpect(xpath("/Pizza/price").number((double) 5000));
    }
}
