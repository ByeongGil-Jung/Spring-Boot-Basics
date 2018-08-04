package com.example.springbootonlytest.__2_4_test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-05
 * Time: 오전 5:21
 */

/*

[ OutputCapture 사용하기 ]

- Junit 에 있는 Rule 을 확장해서 만든 것으로,
  log 를 비록하여 console 에 찍히는 모든 것을 출력한다.

-

 */
@RunWith(SpringRunner.class)
@WebMvcTest(OutputCaptureController.class)
public class OutputCaptureControllerTest {

    @Rule
    // public 으로 선언해야 사용할 수 있다. (Spring 내부 객체가 아니라, JUnit 객체이다.0
    public OutputCapture outputCapture = new OutputCapture();

    @MockBean
    SampleService mockSampleService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void bongjour() throws Exception {
        when(mockSampleService.getName()).thenReturn("bbkk");

        mockMvc.perform(get("/bonjour"))
                .andExpect(content().string("bonjour bbkk"));

        assertThat(outputCapture.toString())
                .contains("bon bon bonjour ~")
                .contains("Please don't use logging using sout");
    }
}