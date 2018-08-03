package com.example.springbootstart.__2_spring_boot_utilization._2_external_settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-03
 * Time: 오후 2:28
 */

/*

(우선순위 15 - jar 안에 있는 application properties)

application.properties 에서 설정한 값을
>> @Value("${설정 값}")
을 이용하여 사용할 수 있다.

*/
@Component
public class ExtSimpleRunner implements ApplicationRunner {

    @Value("${bread.name}")
    private String name;

    @Value("${bread.price}")
    private String price;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n=== 2-2. External Settings (1) ===");
        System.out.println("(.properties) name : " + name);
        System.out.println("(.properties) price : " + price);
    }
}
