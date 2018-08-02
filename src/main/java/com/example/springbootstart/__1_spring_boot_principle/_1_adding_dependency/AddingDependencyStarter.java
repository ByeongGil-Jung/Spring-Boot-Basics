package com.example.springbootstart.__1_spring_boot_principle._1_adding_dependency;

import com.example.BasePizza;
import com.example.springbootstart.ApplicationStarter;
import com.example.springbootstart.SpringBootStartApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project: spring-boot-start
 * ===========================================
 * User: ByeongGil Jung
 * Date: 2018-08-01
 * Time: 오후 5:36
 */
/*

[ Spring Boot 에서 Bean 을 주입받는 원리와, 구동 완리 ]

- @SpringBootApplication 을 통해 @ComponentScan 과 @EnableAutoConfiguration 이 순차적으로 실행된다.

즉, Spring Boot 를 실행하면,
1. @ComponentScan 으로 주입시킬 Bean 들을 찾는다. (@Component 등등 ... )
2. 찾은 Bean 을 대상으로, @EnableAutoConfiguration 을 통해 어떤 옵션이 자동 설정 되어있는지 확인한다.
(예를들어, 예제의 BasePizza 는 @ConditionalOnMissingBean 의 옵션이 있기에,
커스텀한 Bean 이 있으면 AutoConfiguration 을 생략한다.)

 */
@Component
public class AddingDependencyStarter implements ApplicationRunner, ApplicationStarter {

    // Bean 을 주입 받았기에, 그대로 사용 가능.
    @Autowired
    BasePizza basePizza;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n === 0. Adding Dependency ===");
        System.out.println(basePizza);
    }

    /*
    - 그렇다면, 미리 auto configuration 을 한 값이 아닌,
    사용자 정의의 새로운 값을 넣으려면 어떻게 해야하나 ?

    auto configuration 부분에서
    >> @ConditionalOnMissingBean
    을 설정해줘야 함.

    이를 통해, 사용자 정의의 값이 없으면 auto configuration 의 값을 따름.
    */


    /*
    - 아래와 같이 직접 property 를 설정할 수 있지만,

    'property 자동 완성' 기능을 사용해서 새로 bean 을 만들 필요 없이,
    application/properties 에서 property 를 설정할 수 있다.

    (이 때, 반드시 외부 jar 에서
    @EnableConfigurationProperties(BasePizzaProperties.class)
    @ConfigurationProperties("basepizza")
    와 같은 어노테이션이 있어야 한다.


    @Bean
    public BasePizza basePizza() {
        BasePizza basePizza = new BasePizza();
        basePizza.setName("New Normal Pizza");
        basePizza.setPrice(5000);

        return basePizza;
    }
    */

    @Override
    public void applicationStart(String[] args) {
        SpringApplication.run(SpringBootStartApplication.class, args);
    }
}
