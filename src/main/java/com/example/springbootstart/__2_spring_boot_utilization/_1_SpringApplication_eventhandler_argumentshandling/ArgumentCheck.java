package com.example.springbootstart.__2_spring_boot_utilization._1_SpringApplication_eventhandler_argumentshandling;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오전 2:34
 */

/*


 */
@Component
public class ArgumentCheck {

    // 여기서 bean 안에 bean 을 인자로 받을 경우 자동 의존성주입이 된다.
    // 즉, 따로 외부에서 인자를 넣어줄 필요가 없다는 의미.
    public ArgumentCheck(ApplicationArguments args) {
        System.out.println("\n=== 3. Arguments Check ===");
        System.out.println("vmargs : " + args.containsOption("vmargs"));
        System.out.println("pargs : " + args.containsOption("pargs"));
    }
}
