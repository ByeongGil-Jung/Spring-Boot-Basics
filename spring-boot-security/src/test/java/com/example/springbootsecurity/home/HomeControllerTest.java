package com.example.springbootsecurity.home;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-15
 * Time: 오후 2:55
 */
/*

- 만약 spring-security 를 추가하면 일반적인 test 코드는 동작하지 않는다.
  -> Error message : Unauthorized
(spring-security 로 인해 인증을 필요로 하기 때문)

 */
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    // 302 Error 발생. (Redirection >> http://localhost/login 으로 보냄)
    // spring-security 가 기본적으로 만들어주는 login form 으로 이동함.
    @Test
    // @WithMockUser 를 붙이면, 만약 인증 정보가 있을 경우 Test 에 통과하게 된다.
    @WithMockUser
    public void board() throws Exception {
        mockMvc.perform(get("/board")
                    // 아래는 accept-header 가 TEXT_HTML 임을 명시할 경우.
                    .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("board"));
    }

    @Test
    @WithMockUser
    public void my() throws Exception {
        mockMvc.perform(get("/my"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("my"));
    }

    // 아래는 인증 정보가 없을 경우.
    // (@WithMockUser 가 없음)
    @Test
    public void myWithoutUser() throws Exception {
        mockMvc.perform(get("/my"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}