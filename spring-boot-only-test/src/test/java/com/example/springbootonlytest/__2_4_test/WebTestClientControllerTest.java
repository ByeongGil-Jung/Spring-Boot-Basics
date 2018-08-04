package com.example.springbootonlytest.__2_4_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-05
 * Time: 오전 4:15
 */

/*

[ Servlet Test (WebTestClient) ]

- Spring 5 에서 새로 추가된, rest client 중에 하나다.
기존에 사용하던 rest client 동기식이라, 요청이 끝날 때 까지 기다려야 다음 요청을 보낼 수 있다.
하지만 WebClient 는 비동기식이라, 요청을 할 때마다 call back 을 보낸다.

(WebClient 를 사용하기 위해선, Spring webflux 의 의존성이 주입되어 있어야 한다.)

-> 즉, test 에서도 WebClient 와 동일한 api 를 사용할 수 있다.

(얘기로는,
WebTestClient > MockMvc > RestTemplate
순으로 편하다고 한다.

(RestTemplate 는 자동완성이 없기 때문))

 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTestClientControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    SampleService mockSampleService;

    @Test
    public void niHao() {
        when(mockSampleService.getName()).thenReturn("bbkk");

        webTestClient.get().uri("/nihao").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("nihao bbkk");
    }
}