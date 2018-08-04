package com.example.springbootonlytest.__2_4_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-05
 * Time: 오전 3:04
 */

/*

[ MOCK Test ]

- 밑의 두 어노테이션이 가장 기본적인 형태의 테스트 코드이다.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
- MOCK : 내장 톰캣 구동 안 함. (mock servlet environment)
- RANDOM_PORT, DEFINED_PORT : 내장 톰캣 사용 함.
- NONE : 서블릿 환경 제공 안 함.

여기서, MOCK 을 사용하면, servlet 으로 요청을 보내는 것과 비슷한 동작을 하는데,
실제로 servlet 을 작동시키진 않는다.

이 떄,
작동시키게끔 interaction 할 수 있는 방법이 있는데,
-> MOCK MVC Client 를 사용해야 한다.
>> @AutoConfigureMockMvc 을 주입받는 것이 제일 편하다. (다른 방법도 있긴 하다.)

*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/world"))
                // 200 임을 확인
                .andExpect(status().isOk())
                // 어떤 값이 찍히는지 확인
                .andExpect(content().string("world JBK"))
                // 어떤 메소드를 썼는지, 어떤 controller, 어떤 method 를 썼는지를 확인
                .andDo(print());
    }
}