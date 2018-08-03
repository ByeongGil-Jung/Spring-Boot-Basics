package com.example.springbootstart.__2_spring_boot_utilization._1_SpringApplication_eventhandler_argumentshandling;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오전 1:18
 */

/*

ApplicationListener< Type > 을 꼭 넣어줘야 한다.
또한 Class 위에 @Component 를 입력하여, bean 으로 등록을 해줘야 한다.
(Type 에는 event 발생 시점이 들어 간다.)

여기서는 ApplicationStartingEvent 인데,
이것은 Context 가 만들어지기 이전에 발생하는 event 이다.
즉, listener 가 동작을 하지 않는다.
-> 따라서 이런 경우에는 직접 등록을 해줘야 한다.

(또한, 그렇기 때문에 @Component 가 필요 없다. -> bean 으로 등록할 필요가 없기 때문.)

*/
public class SimpleStartingEvent implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("\n=== 2-1. Event Listener ===");
        System.out.println("[ ApplicationStartingEvent ]");
        System.out.println("Starting Application");
        System.out.println("(The context has not been created yet)\n");
    }
}
