package com.example.springbootstart.__2_spring_boot_utilization._1_SpringApplication_eventhandler_argumentshandling;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오전 3:22
 */
@Component
@Order(2)
public class ArgumentRunnerSecond implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n=== 3. Arguments Runner [@Order(2)] ===");
        System.out.println("vmargs : " + args.containsOption("vmargs"));
        System.out.println("pargs : " + args.containsOption("pargs"));
    }
}
