package com.example.springbootonlytest.__2_4_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-05
 * Time: 오전 3:24
 */

/*

[ Servlet Test (TestRestTemplate) ]

- Test 용 RestTemplate (TestRestTemplate) 이나, test 용 client (WebTestClient) 를 써야 한다.

1. TestRestTemplate 사용
- 실제로 random 한 port 에 test 용 servlet 이 돌아간다.

여기서 test 가 너무 크다 생각할 수 있다.
-> 실제로 testRestTemplate 만 사용하면 service 단 까지 가게 된다.

- 그렇담 controller 단에서 끊으려면 어떻게 해야 할까 ?
>> @MockBean
>> ContainerController mockContainerController;

의 bean 등록을 하면,
실제로 test controller 는 원본이 아닌, MockSampleService 로 교체하여 사용하게 된다.
-> 즉, service 와 같이 부분적인 bean 들을 섬세하게 test 할 수 있다.


 */
@RunWith(SpringRunner.class)
// 내장 tomcat 을 구동함으로써 실제 servlet 을 동작시킨다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplateControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    SampleService mockSampleService;

    @Test
    public void world() throws Exception {
        // mockSampleService 를 사용할 때,
        // 이렇게 되면, SampleService.getName() 은 "JBK" 가 아닌, "bbkk" 를 return 한다.
        when(mockSampleService.getName()).thenReturn("bbkk");

        String result = testRestTemplate.getForObject("/world", String.class);
        // assertThat(result).isEqualTo("world JBK");
        assertThat(result).isEqualTo("world bbkk");
    }
}