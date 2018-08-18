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

이것은 굉장히 type-safe 하다.
(properties 에서의 값이 type-safe 한 것이 아니라,
method 를 통해서 호출하기에 type 에 대해 안전하다는 의미.)

-> 밑의 type-conversion 과 연결된다.

/////////////////////////////////////////////////////////////////////////////

- 또한, Type-Conversion 을 지원한다.
(application.properties 의 값들은 실제론 모두 문자열이다.
하지만 실제 bean 에서 값으로 등록될 땐 해당하는 type 으로 변환된다.)

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
