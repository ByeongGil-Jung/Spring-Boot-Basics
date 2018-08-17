package com.example.springbootrestclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-16
 * Time: 오후 2:32
 */

/*

 [ 1. RestTemplate ]

REST-API 의 client side 라고 생각
spring-web 모듈이 있기 때문에 RestTemplate 을 쓸 수 있음

===============================================================================

RestTemplate 는 Blocking I/O 기반이기에, 동기화가 이루어져야 한다.
즉,

 >> restTemplate.getForObject("http://localhost:8080/hello", String.class);

의 호출이 다 끝나기 전까진, 다음 라인으로 넘어가지 않는다.
(즉, 모든 동작이 순차적으로 이루어진다.)

===============================================================================

이 예제에선,

app 동작시킴
-> hello 를 호출 -> 5초 기다림 -> hello 를 return
-> world 를 호출 -> 3초 기다림 -> world 를 return
-> StopWatch 는 대략 8초를 출력할 것

*/
@Component
public class RestTemplateRunner implements ApplicationRunner {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RestTemplate restTemplate = restTemplateBuilder
                // 여러가지 설정을 줄 수 있다 ... (커스터마이징)
                .build();

        // StopWatch 를 만들어서 시간을 재보자
        System.out.println("\n=== [ 1. RestTemplate (Synchronous) ] ===");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // TODO /hello 호출
        String helloResult = restTemplate.getForObject("http://localhost:8080/hello", String.class);
        System.out.println(helloResult);

        // TODO /world 호출
        String worldResult = restTemplate.getForObject("http://localhost:8080/world", String.class);
        System.out.println(worldResult);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
