package com.example.springbootstart.__2_spring_boot_utilization._1_SpringApplication_eventhandler_argumentshandling;

import com.example.springbootstart.ApplicationStarter;
import com.example.springbootstart.SpringBootStartApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오전 12:10
 */

/*

[ SpringApplication 클래스의 활용 ]

>> SpringApplication.run(SpringBootStartApplication.class, args);
을 사용하여 spring 을 구동하면 SpringApplication 의 다양한 옵션을 활용하지 못 한다.

1. 설정 - Environments - VM options 에 "-Ddebug" 라 설정하면 debug 모드로 실행된다.
- 어떤 자동 설정이 적용됐는지, 안됐다면 왜 안됐는지 알 수 있다.)

2. FailureAnalyzer 를 통해 Error 메시지를 좀 더 이쁘게 볼 수 있다.

3. 배너 (구동 시 보이는 큰 Spring 텍스트 ... )
- 사용자가 직접 배너를 만들 수 있다.
- 버전 등 다양한 옵션을 넣을 수 있다.

!! 여기서부터 중요
4. Application 이 시작 될 때, context 를 만들 때, refresh 됐을 때, 잘 구동 됐을 때, 실패 했을 때, ...
    등등 모든 event 에 대해 listener 를 만들 수 있음

그렇다면 event 의 기준은 무엇인가 ?
-> context 가 생성되기 이전의 listener 는 bean 으로 등록해 주어도 발생하지 않는다.
-> 즉, 아래의 app.addListeners(new ListenerClass); 와 같이 명시를 해주어야 한다.

5. arguments 를 handling 할 수 있다.
-> 이 때, console 로 들어오는 arguments 는 두 종류이다.

- VM options : -D'valude'
:: jvm 옵션과 관련된 args 이다.
- Program arguments : --'value'
:: 실제 application 으로 들어오는 args 인자이다.

이 때, args 의 인자로는 오로지 Program arguments 만 들어온다. (console 에서 입력한 값)

////
또한, ApplicationRunner 를 상속 받음으로써 args 의 인자를 application 내부에서 활용할 수 있다. (@Order 로 순서 결정 가능)
(직접 작동 순서를 보면 알겠지만, application 이 완전히 실행되기 전에 함수가 작동한다.)

 */
public class SpringApplicationStarter implements ApplicationStarter {

    @Override
    public void applicationStart(String[] args) {
        System.out.println("  Select :");
        System.out.println("  - 1: Ordinary run (default)");
        System.out.println("  - 2: Builder Pattern run");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();

        switch (i) {
            case 1:
                // 기존 SpringApplication.run(SpringBootStartApplication.class) 과 동일하다.
                // 이 때, 기본적인 log level 은 INFO 이다.
                SpringApplication app = new SpringApplication(SpringBootStartApplication.class);

                // 3. 배너 사용자 지정 (코딩으로 했을 경우)
                app.setBanner((environment, sourceClass, out) -> {
                    out.println("=========================");
                    out.println("Banner Test");
                    out.println("=========================");
                });
                // 배너 끄기
                // app.setBannerMode(Banner.Mode.OFF);

                // 4. context 생성 이전의 listener 추가하기
                app.addListeners(new SimpleStartingEvent());

                // app run
                app.run(args);
                break;
            case 2:
                // Builder pattern 으로 app 실행하기
                new SpringApplicationBuilder()
                        .sources(SpringBootStartApplication.class)
                        .run(args);
                break;
        }
    }
}
