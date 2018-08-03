package com.example.springbootstart.__2_spring_boot_utilization._1_SpringApplication_eventhandler_argumentshandling;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오전 1:31
 */
@Component
public class SimpleStartedEvent implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("\n=== 2-1. Event Listener ===");
        System.out.println("[ ApplicationStartedEvent ]");
        System.out.println("The application has already run");
        System.out.println("(The context has already been made)");
    }
}
