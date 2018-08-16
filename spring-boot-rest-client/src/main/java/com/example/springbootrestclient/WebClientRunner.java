package com.example.springbootrestclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-16
 * Time: 오후 3:03
 */

/*

 [ 2. WebClient ]

webflux 모듈을 의존성으로 넣으면 WebClient 을 쓸 수 있음

===============================================================================

RestTemplate 는 Non-Blocking I/O 기반이기에, 비동기로 진행된다.
즉,

 >> webClient.get().uri("http://localhost:8080/world")
                .retrieve()
                .bodyToMono(String.class);

로 받아온 Mono type 의 Stream 을 subscribe 하면
순차적으로 함수를 호출하지 않고, 비동기식으로 병렬처리 된다.

(Mono 로 받아오는 과정은 단순히 Stream 에 넣는 과정일 뿐이다. -> 이 과정은 비동기이다.
 subscribe 를 하는 과정에서 실제로 req 를 보내고 res 를 받아온다. -> 이 과정 역시 비동기이다.

 -> 즉, 모두 Non-Blocking 이기에 sleep 코드를 기다리지 않고 다음 코드로 바로 넘어간다 !

===============================================================================

이 예제에선,

app 동작시킴
(비동기)
 world 를 호출 -> 3초 기다림 -> world 를 return
 hello 를 호출 -> 5초 기다림 -> hello 를 return
-> StopWatch 는 대략 5초


===============================================================================

WebClient 를 사용하면, 위 말고도 여러가지 API 설정을 줄 수 있음.
 - A, B, C 다 req 한 뒤, A, B, C 모두 res 가 와야 그 다음 작업을 한다든지 ...
 - A, B 를 먼저 req 한 뒤, A, B 의 res 가 오면
   그것들의 res 를 조합해서 C 의 req 로 보낸다든지 ...

*/
@Component
public class WebClientRunner implements ApplicationRunner {

    // WebClient 역시 builder 를 주입해 줌
    @Autowired
    WebClient.Builder builder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebClient webClient = builder
                .build();

        System.out.println("\n=== [ 2. WebClient (Asynchronous) ] ===");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 여기서부턴 RestTemplate 과 다르다.
        // TODO /hello 호출

        /*
        - 이건 Steam 으로 담은 것이다. (Stream API)
        (아직 body 의 값을 string 으로 변환하는 작업은 안 일어남)
        uri 를 webClient 로 get 요청
        */
        Mono<String> helloMono = webClient.get().uri("http://localhost:8080/hello")
                // 결과값을 가져옴
                .retrieve()
                // Mono type 으로 변경
                .bodyToMono(String.class);

        // Steam 형태로 받은 Mono 를 subscribe
        helloMono.subscribe(s -> {
            System.out.println(s);

            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });

        // TODO /world 호출
        Mono<String> worldMono = webClient.get().uri("http://localhost:8080/world")
                .retrieve()
                .bodyToMono(String.class);

        worldMono.subscribe(s -> {
            System.out.println(s);

            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });
    }
}
