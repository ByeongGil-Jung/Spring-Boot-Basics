package com.example.springbootwebmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

[ 1. Introduce ]

 - Spring MVC 확장 ?
WebMvcConfigurer 를 상속받는 Config 클래스를 만들고,
@Configuration 어노테이션을 붙이면,

해당 클래스에서 mvc 설정을 추가할 수 있다.

++ 절대 @EnableWebMvc 를 붙이면 안된다 !
> 붙일 경우, Spring boot 가 제공하는 mvc 설정들이 모두 사라지고, 사용자가 직접 다시 세팅해줘야 한다.


==============================================================================================

[ 2. HttpMessageConverters ]

 - Spring 에서 제공하는 Interface 이다.
 - Spring MVC 의 일부분이다.
 - http body 로 들어오는 요청을 객체로 변환하거나,
   혹은 http body 로 보낼 response 객체로 변환할 때 사용한다.

 HttpMessageConverter 는 여러가지가 있는데,
 어떤 request 를 받았는지, 어떤 response 를 보내야 하는지에 따라 사용 객체가 달라진다.

 ex)
 request : JSON (content-type: JSON, body 내용 역시 JSON)
 response : html

 -> JsonMessageConverter 사용

 (만약 return 형태가 String, int 등등... 이면 StringMessageConverter 을 사용한다. -> 모두 String 으로 타입 변환)

 */

@SpringBootApplication
public class SpringBootWebMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebMvcApplication.class, args);
    }
}
