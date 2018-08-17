package com.example.springbootrestclient;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-17
 * Time: 오후 3:09
 */
@Configuration
public class RestClientConfiguration {

    // 아래는 WebClient 를 커스터마이징 하는 방법.
    /*

    >> RestTemplate restTemplate = restTemplateBuilder
           // 여러가지 설정을 줄 수 있다 ... (커스터마이징)
           .build();

    의 방법은 지역적으로 customizing 하는 방법.

    밑의 방법은 bean 으로 등록하여 전역적으로 customizing 하는 방법이다.

     */
    @Bean
    public WebClientCustomizer webClientCustomizer() {
        return webClientBuilder -> webClientBuilder.baseUrl("http://localhost:8080");
    }

    // 아래는 RestTemplate 를 커스터마이징 하는 방법.
    /*

    의존성으로 'org.apache.httpcomponents:httpclient' 가 필요하며,

    아래 것을 bean 으로 등록함으로써
    자바의 HttpConnection 을 사용하지 않고,
    Apache 의 HttpClient 를 사용하게 된다.

    즉 새롭게 customizing 이 가능하다.

     */
    @Bean
    public RestTemplateCustomizer restTemplateCustomizer() {
        return restTemplate -> restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }
}
