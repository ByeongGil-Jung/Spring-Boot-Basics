package com.example.springbootwebmvc.hate_oas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-08
 * Time: 오후 5:03
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HateoasController.class)
public class HateoasControllerTest {

    @Autowired
    MockMvc mockMvc;

    // ObjectMapper 사용 (이미 등록 돼있기 때문에, 그냥 주입시키면 된다.)
    @Autowired
    ObjectMapper objectMapper;
    /*
    만약 커스터마이징 하고 싶다면,

    > application.properties 에서
    > spring.jackson.* 의 메소드를 사용하면 된다.
     */

    @Test
    public void hateoas() throws Exception {
        mockMvc.perform(get("/hateoas"))
                .andDo(print())
                .andExpect(status().isOk())
                // links 가 존재하는지 확인 (-> 즉, HATEOAS 인지 확인)
                .andExpect(jsonPath("$._links.self").exists());
    }
}
