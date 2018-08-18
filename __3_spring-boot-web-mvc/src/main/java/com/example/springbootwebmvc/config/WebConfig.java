package com.example.springbootwebmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-06
 * Time: 오후 6:17
 */
// Spring MVC 가 제공하는 환경 설정을 그대로 사용하면서, 추가로 더 설정할 수 있다.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/m/**")
                // 맨 마지막에 '/' 을 붙여줘야 정상 작동한다.
                .addResourceLocations("classpath:/m/")
                // static 자원을 cache 로 저장할 때, 얼마동안 저장할지의 시간 지정
                // caching 을 반드시 설정해줘야 한다.
                // -> static 아래의 resource 는 기본 spring 의 cache 로 사용되고 있다.
                .setCachePeriod(20);
    }

    // CORS 를 CorsController 에서 처럼 하나하나 등록해주기 번거롭다면,
    // 아래와 같이 WebConfig 에서 global 설정을 해줄 수 있다.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // "/hihi" 로 들어오는 request 들을 CORS 해준다.
        // CORS 를 허가하는 Origin 은 "http://localhost:18080" 이다.
        registry.addMapping("/hihi")
                .allowedOrigins("http://localhost:18080");

        /*
        -> 만약 모든 request 에 대해 CORS 하고 싶다면 아래와 같이 설정한다.

        현재 Origin 로 들어오는 모든 request 들을 CORS 해준다.
        CORS 를 허가하는 Origin 은 "http://localhost:18080" 이다.

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:18080");

        */
    }
}
