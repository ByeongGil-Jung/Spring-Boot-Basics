package com.example.springbootwebmvc.htmlunit;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-07
 * Time: 오후 2:39
 */
/*

- HtmlUnit 을 의존성으로 넣었으면,
  MockMvc 가 아닌 WebClient 를 주입받을 수 있다.

>> 이렇게 하면 html 에 특화된 test 를 만들 수 있다.

 */
@RunWith(SpringRunner.class)
@WebMvcTest(HtmlUnitController.class)
public class HtmlUnitControllerTest {

    @Autowired
    WebClient webClient;

    @Test
    public void htmlunit() throws Exception {
        // HtmlPage 로 받아줘야 다양한 method 를 사용할 수 있다. (기본은 Page 객체이다.)
        HtmlPage page = webClient.getPage("/htmlunit");
        // 원래는 Object 로 받아오는데, 상황에 맞춰 다른 객체로 다운캐스팅 해서 사용하도록 하자.
        DomElement p = page.getElementById("price");
        // 값 확인용
        // assertThat(p.getTextContent()).isEqualToIgnoringCase("8000");

        // int 8000 으로 넣으면 안된다. -> getTextContent() 는 String 을 return 한다.
        assertThat(p.getTextContent()).isEqualTo("8000");
    }
}
