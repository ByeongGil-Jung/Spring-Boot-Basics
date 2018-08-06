package com.example.springbootwebmvc.config;

import org.springframework.context.annotation.Configuration;
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
}
