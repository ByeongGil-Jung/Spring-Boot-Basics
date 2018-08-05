package com.example.springbootwebmvc.user;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-06
 * Time: 오전 2:23
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

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
        String userJson = "{\"name\":\"Pizza\", \"price\":\"5000\"}";
        mockMvc.perform(post("/users/create")

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
}
