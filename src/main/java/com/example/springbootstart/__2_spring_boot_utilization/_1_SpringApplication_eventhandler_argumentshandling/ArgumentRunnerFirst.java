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
 * Time: 오전 3:15
 */

/*

ApplicationRunner 를 상속 받음으로써,
application 내부에서 (완전히 가동된 후) args 인자를 사용할 수 있다.
- 물론 program arguments 에서 입력된 값만 사용 가능하다.

@Order 를 통해 순서 결정이 가능하다.

 */
@Component
@Order(1)
public class ArgumentRunnerFirst implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n=== 3. Arguments Runner [@Order(1)] ===");
        System.out.println("vmargs : " + args.containsOption("vmargs"));
        System.out.println("pargs : " + args.containsOption("pargs"));
    }
}
