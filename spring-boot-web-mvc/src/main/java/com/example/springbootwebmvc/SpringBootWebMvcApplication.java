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

 */

@SpringBootApplication
public class SpringBootWebMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebMvcApplication.class, args);
    }
}
